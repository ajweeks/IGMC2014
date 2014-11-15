package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.input.Keyboard.Key;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends BoundingBox {
	public static final float JUMP_VELOCITY = 0.65F;
	public static final float GRAVITY = -0.05F;
	
	public static final float SPRINT_VELOCITY = 0.70F;
	public static final float WALK_VELOCITY = 0.40F;
	
	public static final float SPAWN_X = 2.0f, SPAWN_Y = 5.0f;
	
	public static final float WIDTH = 1.0f;
	public static final float HEIGHT = 2.0f;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	public float maxHorizontalVelocity = WALK_VELOCITY;
	public float maxVerticalVelocity = -18.0F; //terminal vertical velocity
	private float horizontalPositiveAcceleration = 0.013f; //how quickly the player speeds up
	private float horizontalNegativeAcceleration = 0.035f;  //how quickly the player slows down (once all controls have been released)
	
	private static Image sprite;
	private Game game;
	private GameState gs;
	
	private float xv, yv;
	//private float xa, ya; //LATER use acceleration properly
	private int coins = 0;
	
	public Player(Game game, GameState gs) {
		super(SPAWN_X, SPAWN_Y, WIDTH, HEIGHT);
		
		this.game = game;
		this.gs = gs;
		
		sprite = new ImageIcon("res/player.png").getImage();
	}
	
	public void update(double delta) {
		if (Key.R.clicked) { //LATER add a respawn nagger when player is stuck
			respawn();
		}
		
		if (Key.SHIFT.clicked) maxHorizontalVelocity = SPRINT_VELOCITY; //LATER add speed ramping to make slowing down smoother
		else maxHorizontalVelocity = WALK_VELOCITY;
		
		if (Key.RIGHT.clicked) {
			xv += horizontalPositiveAcceleration;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (Key.LEFT.clicked) {
			xv -= horizontalPositiveAcceleration;
			if (xv < -maxHorizontalVelocity) xv = -maxHorizontalVelocity;
		}
		
		if (!Key.LEFT.clicked && !Key.RIGHT.clicked && xv != 0) { //decelerate horizontal motion by friction
			if (xv < 0) {
				if ((xv -= horizontalNegativeAcceleration) < 0) xv = 0;
			} else if (xv > 0) {
				if ((xv += horizontalNegativeAcceleration) > 0) xv = 0;
			}
		}
		
		if (Key.SPACE.clicked || Key.UP.clicked) {
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
		if (yv < maxVerticalVelocity) yv = maxVerticalVelocity;
		
		float bx = getX(), by = getY(); //store original values
		
		setX(getX() + xv);
		setY(getY() + yv);
		
		if (getX() < 0) { //left side of level
			setX(0);
			xv = 0;
		}
		
		if (getY() < getHeight()) { //bottom of level
			setY(getHeight());
			yv = 0;
			onGround = true;
		}
		
		float levelWidth = gs.level.chunks[0].length * Chunk.WIDTH * Tile.PIXEL_WIDTH - (Player.WIDTH * Tile.PIXEL_WIDTH);
		if (getX() > levelWidth / Tile.PIXEL_WIDTH - getWidth() / 2) {
			setX(levelWidth / Tile.PIXEL_WIDTH - getWidth() / 2);
			xv = 0;
		}
		
		float levelHeight = gs.level.chunks.length * Chunk.WIDTH * Tile.PIXEL_WIDTH - (Player.HEIGHT * Tile.PIXEL_WIDTH);
		if (getY() > levelHeight / Tile.PIXEL_WIDTH) {
			setY(levelHeight / Tile.PIXEL_WIDTH);
			yv = 0;
		}
		
		if (xv != 0 || yv != 0) {
			for (int i = 0; i < gs.level.chunks.length; i++) {
				for (int j = 0; j < gs.level.chunks[i].length; j++) {
					for (int k = 0; k < gs.level.chunks[i][j].tiles.length; k++) {
						for (int l = 0; l < gs.level.chunks[i][j].tiles[k].length; l++) {
							Tile t = gs.level.chunks[i][j].tiles[k][l];
							if (t.intersects(this)) { //FIXME col detection will never work as intended because player's x & y pos are not rendered the same way tile's x & y 
								if (t.getType() == Tile.Type.COIN) {
									if (!((Coin) t).isRemoved()) {
										coins++;
										((Coin) gs.level.chunks[i][j].tiles[k][l]).remove();
									}
								} else if (t.getType().isSolid()) {
									setX(bx);
									setY(by);
									
									xv = 0;
									onGround = true;
								}
							}
						}
					}
				}
			}
		}
		
		if (onGround) {
			hasDoubleJumped = false;
			yv = 0;
		}
	}
	
	public void respawn() {
		setX(SPAWN_X);
		setY(SPAWN_Y);
		coins = 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int) (getX() * Tile.PIXEL_WIDTH + GameState.camera.x),
				(int) (Game.SIZE.height - getY() * Tile.PIXEL_WIDTH + GameState.camera.y), (int) getWidth() * Tile.PIXEL_WIDTH,
				(int) getHeight() * Tile.PIXEL_WIDTH);
		g.setColor(Color.white);
		g.drawImage(sprite, (int) (getX() * getWidth() + GameState.camera.x),
				(int) (Game.SIZE.height - getY() * getHeight() + GameState.camera.y), null);
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
