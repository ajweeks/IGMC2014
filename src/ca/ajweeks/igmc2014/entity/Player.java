package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player {
	
	public final static int JUMP_SPEED = -175;
	public final static float GRAVITY = 9.8f;
	
	public final static int SPRINT_SPEED = 16;
	public final static int WALK_SPEED = 10;
	public static int speed = WALK_SPEED;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private int xv, yv;
	
	private Rectangle bounds;
	private Image image = new ImageIcon("res/player.png").getImage();
	private GameState gs;
	
	//public int jumps = 0; //TODO can't remember why this is here...... to allow for triple jumps maybe?
	
	public Player(GameState gs) {
		bounds = new Rectangle(150, 275, 32, 64);
		
		this.gs = gs;
	}
	
	public int getX() {
		return bounds.x;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	public void update(double delta) {
		if (Game.input.right.down && xv == 0) xv = 1;
		if (Game.input.left.down && xv == 0) xv = -1;
		
		if (xv == 1 && !Game.input.right.down) xv = 0;
		if (xv == -1 && !Game.input.left.down) xv = 0;
		
		if (!Game.input.right.down && !Game.input.left.down) xv = 0;
		
		if (Game.input.space.clicked || Game.input.up.clicked) {
			if (onGround) {
				Sound.JUMP.play();
				hasDoubleJumped = false;
				onGround = false;
				yv = JUMP_SPEED;
			} else { //not on ground
				if (!hasDoubleJumped) {
					Sound.JUMP.play();
					hasDoubleJumped = true;
					yv = JUMP_SPEED;
				}
			}
			return;
		}
		
		yv += GRAVITY;
		
		if (onGround) {
			yv = 0;
			hasDoubleJumped = false;
		}
		
		if (bounds.x < 0) bounds.x = 0; //left wall
		if (bounds.y > 576) bounds.y = 576;
		
		int bx = bounds.x, by = bounds.y;
		
		bounds.x += xv * speed * delta;
		bounds.y += (yv * delta) / 6;
		
		if (bounds.x < 0) bounds.x = 0; //left wall
			
		int levelWidth = gs.level.chunks[0].length * Level.CHUNK_WIDTH * Tile.TILE_WIDTH - 32;
		if (bounds.x > levelWidth) bounds.x = levelWidth;
		
		if (bounds.y < 0) bounds.y = 0;
		
		if (bounds.y > 576) {
			onGround = true;
			yv = 0;
			bounds.y = 576;
		}
		
		if (bounds.intersects(gs.level.getBlock(bounds.x, bounds.y))) {
			System.out.println("true");
			bounds.x = bx;
			bounds.y = by;
			
			yv = 0;
			onGround = true;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(image, bounds.x + GameState.camera.x, bounds.y + GameState.camera.y, null);
	}
	
	public static void setSpeed(int speed) {
		Player.speed = speed;
	}
}
