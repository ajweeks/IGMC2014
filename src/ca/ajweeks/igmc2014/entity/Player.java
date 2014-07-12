package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player {
	
	public static final float JUMP_VELOCITY = 1.2F;
	public static final float GRAVITY = -0.12F;
	
	public static final float SPRINT_VELOCITY = 0.7F;
	public static final float WALK_VELOCITY = 0.4F;
	
	public static float maxHorizontalVelocity = WALK_VELOCITY; //The fastest velocity the player can move horizontally
	public static float terminalVelocity = -18.0F; //The fastest velocity the player can fall
	public static float friction = 0.02F; //How quickly the player accelerates
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private Image image = new ImageIcon("res/player.png").getImage();
	private GameState gs;
	
	private double x, y;
	private double xv, yv;
	private int coins = 0;
	
	public Player(GameState gs) {
		x = 3.0;
		y = 5.0;
		
		this.gs = gs;
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public double getXv() {
		return xv;
	}
	
	public double getYv() {
		return yv;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void update(double delta) {
		if (Game.input.right.down) {
			xv += friction;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (Game.input.left.down) {
			xv -= friction;
			if (xv < -maxHorizontalVelocity) xv = -maxHorizontalVelocity;
		}
		
		if (!Game.input.left.down && !Game.input.right.down && xv != 0) { //decelerate horizontal motion
			double bxv = xv;
			double axv = Math.abs(xv) - friction;
			if (axv <= 0) {
				xv = 0;
			} else {
				if (bxv < 0) xv = -axv;
				else if (bxv > 0) xv = axv;
				else xv = 0;
			}
		}
		
		if (Game.input.space.clicked || Game.input.up.clicked) {
			if (onGround) {
				Sound.JUMP.play();
				hasDoubleJumped = false;
				onGround = false;
				yv = JUMP_VELOCITY;
			} else { //not on ground
				if (!hasDoubleJumped) {
					Sound.JUMP.play();
					hasDoubleJumped = true;
					yv = JUMP_VELOCITY;
				}
			}
		}
		
		yv += GRAVITY;
		if (yv < terminalVelocity) yv = terminalVelocity;
		
		double bx = x, by = y; //store original values
		
		x += xv * delta;
		y += yv * delta;
		
		if (x < 0) {
			x = 0; //left side of level
			xv = 0;
		}
		
		if (y < 1) {
			y = 1; //bottom of level
			yv = 0;
		}
		
		float levelWidth = gs.level.chunks[0].length * Chunk.WIDTH * Tile.WIDTH - Tile.WIDTH / 2;
		if (x > levelWidth / Tile.WIDTH) {
			x = levelWidth / Tile.WIDTH; //right side of level
			xv = 0;
		}
		
		float levelHeight = gs.level.chunks.length * Chunk.WIDTH * Tile.WIDTH - Tile.WIDTH;
		if (y > levelHeight / Tile.WIDTH) { //top of level
			y = levelHeight / Tile.WIDTH;
			yv = 0;
		}
		
		//horizontal
		if (xv != 0) {
			if (gs.level.getBlock(x, y).isSolid()) {
				x = bx;
			}
		}
		
		//vertical
		if (yv != 0) {
			if (gs.level.getBlock(x, yv <= 0 ? y + 1 : y).isSolid()) { //TODO check feet when falling (rather than head)
				y = by;
				
				if (yv <= 0) {
					onGround = true;
					hasDoubleJumped = false;
				}
				yv = 0;
			}
		}
		
		hasDoubleJumped = false; //TODO remove
	}
	
	public void render(Graphics g) {
		g.drawImage(image, (int) (x * Tile.WIDTH + GameState.camera.x),
				(int) (Game.SIZE.height - y * Tile.WIDTH + GameState.camera.y), null);
	}
}
