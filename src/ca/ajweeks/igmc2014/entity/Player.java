package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.input.Keyboard.Key;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends MoveableBoundingBox {
	public static final float JUMP_VELOCITY = -0.30F;
	public static final float GRAVITY = 0.01F; //ya
	
	public static final float SPRINT_VELOCITY = 0.40F;
	public static final float WALK_VELOCITY = 0.22F;
	
	public static final float SPAWN_X = 2.0f, SPAWN_Y = 10.0f;
	
	public static final float WIDTH = 1.0f;
	public static final float HEIGHT = 2.0f;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	public float maxHorizontalVelocity = WALK_VELOCITY;
	public float maxVerticalVelocity = 5.0F; //terminal vertical velocity
	private float horizontalPositiveAcceleration = 0.013f; //how quickly the player speeds up
	private float horizontalNegativeAcceleration = 0.055f;  //how quickly the player slows down (once all controls have been released)
	
	private static Image sprite;
	
	private float xv = 0, yv = 0;
	private int coins = 0;
	
	public Player() {
		super(SPAWN_X, SPAWN_Y, WIDTH, HEIGHT);
		
		sprite = new ImageIcon("res/player.png").getImage();
	}
	
	public void update(Level level, double delta) {
		if (Key.R.clicked) {
			respawn();
		}
		
		if (Key.SHIFT.down > -1) maxHorizontalVelocity = SPRINT_VELOCITY; //LATER add speed ramping to make slowing down smoother
		else maxHorizontalVelocity = WALK_VELOCITY;
		
		if (Key.RIGHT_ARROW.down > -1) {
			xv += horizontalPositiveAcceleration;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (Key.LEFT_ARROW.down > -1) {
			xv -= horizontalPositiveAcceleration;
			if (xv < -maxHorizontalVelocity) xv = -maxHorizontalVelocity;
		}
		
		if (Key.LEFT_ARROW.down == -1 && Key.RIGHT_ARROW.down == -1 && xv != 0) { //decelerate horizontal motion by friction
			if (xv < 0) {
				if ((xv -= horizontalNegativeAcceleration) < 0) xv = 0;
			} else if (xv > 0) {
				if ((xv += horizontalNegativeAcceleration) > 0) xv = 0;
			}
		}
		
		if (Key.SPACE.clicked || Key.UP_ARROW.clicked) {
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
		if (yv > maxVerticalVelocity) yv = maxVerticalVelocity;
		
		x = getX() + xv;
		y = getY() + yv;
		
		if (getX() < 0) { //left side of level
			x = 0.0f;
			xv = 0.0f;
		}
		
		if (getY() < 0) { //top of level
			y = 0.0f;
			yv = 0.0f;
		}
		
		float levelWidth = level.chunks[0].length * Chunk.WIDTH - Player.WIDTH; //right side of level
		if (getX() > levelWidth) {
			x = levelWidth;
			xv = 0.0f;
		}
		
		float levelHeight = level.height * Chunk.WIDTH - Player.HEIGHT; //bottom of level
		if (getY() > levelHeight) {
			y = levelHeight;
			onGround = true;
		}
		
		if (xv != 0 || yv != 0) {
			Tile[] tiles = new Tile[level.width * level.height * Chunk.WIDTH * Chunk.HEIGHT];
			int k = 0;
			for (int i = 0; i < level.chunks.length; i++) {
				for (int j = 0; j < level.chunks[i].length; j++) {
					for (int y = 0; y < level.chunks[i][j].tiles.length; y++) {
						for (int x = 0; x < level.chunks[i][j].tiles[y].length; x++) {
							tiles[k] = level.chunks[i][j].tiles[y][x];
							k++;
							//LATER check for coins
						}
					}
				}
			}
			
			attemptToMove(delta, tiles);
		}
		
		if (onGround) {
			hasDoubleJumped = false;
			yv = 0.0f;
		}
	}
	
	public void respawn() {
		x = SPAWN_X;
		y = SPAWN_Y;
		coins = 0;
		onGround = false;
		yv = 0.0f;
		xv = 0.0f;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white); //LATER check if this is necessary
		g.drawImage(sprite, (int) (getX() * Tile.PIXEL_WIDTH + GameState.camera.x),
				(int) (getY() * Tile.PIXEL_WIDTH + GameState.camera.y), null);
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void removeCoins(int coins) {
		this.coins -= coins;
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
}
