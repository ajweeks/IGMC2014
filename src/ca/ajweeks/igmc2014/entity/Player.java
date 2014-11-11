package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends BoundingBox {
	public static final float JUMP_VELOCITY = 0.65F;
	public static final float GRAVITY = -0.05F;
	
	public static final float SPRINT_VELOCITY = 0.7F;
	public static final float WALK_VELOCITY = 0.4F;
	
	public static final float SPAWN_X = 2.0f, SPAWN_Y = 5.0f;
	
	public static final int WIDTH = 1;
	public static final int HEIGHT = 2;
	
	public float maxHorizontalVelocity = WALK_VELOCITY;
	public float maxVerticalVelocity = -18.0F; //terminal vertical velocity
	private float horizontalPositiveAcceleration = 0.013f; //how quickly the player speeds up
	private float horizontalNegativeAcceleration = 0.035f;  //how quickly the player slows down (once all controls have been released)
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private static Image sprite;
	private Game game;
	
	private GameState gs;
	
	private float xv, yv;
	private int coins = 0;
	
	public Player(Game game, GameState gs) {
		super(SPAWN_X, SPAWN_Y, WIDTH, HEIGHT, Tile.PIXEL_WIDTH);
		
		this.game = game;
		this.gs = gs;
		
		sprite = new ImageIcon("res/player.png").getImage();
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
	
	public void update(double delta) {
		Input input = game.getInput();
		
		if (input.r.clicked) { //respawn
			setX(SPAWN_X);
			setY(SPAWN_Y);
		}
		
		if (input.shift.clicked) maxHorizontalVelocity = SPRINT_VELOCITY;
		else maxHorizontalVelocity = WALK_VELOCITY;
		
		if (input.right.clicked) {
			xv += horizontalPositiveAcceleration;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (input.left.clicked) {
			xv -= horizontalPositiveAcceleration;
			if (xv < -maxHorizontalVelocity) xv = -maxHorizontalVelocity;
		}
		
		if (!input.left.clicked && !input.right.clicked && xv != 0) { //decelerate horizontal motion by friction
			if (xv < 0) {
				if ((xv -= horizontalNegativeAcceleration) < 0) xv = 0;
			} else if (xv > 0) {
				if ((xv += horizontalNegativeAcceleration) > 0) xv = 0;
			}
		}
		
		if (input.space.clicked || input.up.clicked) {
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
							if (t.getBounds().intersects(this.getBounds())) { //FIXME col detection will never work as intended because player's x & y pos are not rendered the same way tile's x & y 
								if (t.getType() == Tile.Type.COIN) {
									if (!((Coin) t).isRemoved()) {
										coins++;
										((Coin) gs.level.chunks[i][j].tiles[k][l]).remove();
									}
								} else if (t.getType().isSolid()) {
									//x = bx;
									//y = by;
									
									//xv = 0;
									//onGround = true;
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
	
	public void render(Graphics g) {
		//		g.setColor(Color.white);
		//		g.drawImage(sprite, (float) ((float) x * Tile.WIDTH + gs.camera.x), (float) (Game.SIZE.height - y * Tile.WIDTH + gs.camera.y), null);
		g.setColor(Color.white);
		g.fillRect((int) (getX() * Tile.PIXEL_WIDTH + GameState.camera.x),
				(int) (Game.SIZE.height - getY() * Tile.PIXEL_WIDTH + GameState.camera.y), getWidth() * Tile.PIXEL_WIDTH,
				getHeight() * Tile.PIXEL_WIDTH);
	}
}
