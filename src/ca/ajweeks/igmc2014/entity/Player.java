package ca.ajweeks.igmc2014.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.input.Keyboard;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public static final float JUMP_VELOCITY = 0.65F;
	public static final float GRAVITY = -0.05F;
	
	public static final float SPRINT_VELOCITY = 0.7F;
	public static final float WALK_VELOCITY = 0.4F;
	
	public static final int SPAWN_X = 2, SPAWN_Y = 5;
	public static final int WIDTH = 32, HEIGHT = 64;
	
	public static float maxHorizontalVelocity = WALK_VELOCITY;
	public static float terminalVelocity = -18.0F;
	public static float horizontalAcceleration = 0.02f;
	public static float friction = 0.035F; //How quickly the player accelerates
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private static Image player;
	private GameState gs;
	
	private float xv, yv;
	private int coins = 0;
	
	public Player(GameState gs) {
		super(SPAWN_X, SPAWN_Y, 1, 2);
		
		this.gs = gs;
		
		try {
			player = new Image("res/player.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
	
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		Input input = game.getContainer().getInput();
		
		if (gc.getInput().isKeyPressed(Input.KEY_R)) { //respawn
			x = SPAWN_X;
			y = SPAWN_Y;
		}
		
		if (Keyboard.isShiftDown(game)) maxHorizontalVelocity = SPRINT_VELOCITY;
		else maxHorizontalVelocity = WALK_VELOCITY;
		
		if (Keyboard.isRightDown(game)) {
			xv += horizontalAcceleration;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (Keyboard.isLeftDown(game)) {
			xv -= horizontalAcceleration;
			if (xv < -maxHorizontalVelocity) xv = -maxHorizontalVelocity;
		}
		
		if (!Keyboard.isLeftDown(game) && !Keyboard.isRightDown(game) && xv != 0) { //decelerate horizontal motion
			float bxv = xv;
			float axv = Math.abs(xv) - friction;
			if (axv <= 0) {
				xv = 0;
			} else {
				if (bxv < 0) xv = -axv;
				else if (bxv > 0) xv = axv;
				else xv = 0;
			}
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE) || Keyboard.isUpPressed(game)) {
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
		
		float bx = x, by = y; //store original values
		
		x += xv;
		y += yv;
		
		if (x < 0) {
			x = 0; //left side of level
			xv = 0;
		}
		
		if (y < 1) {
			y = 1; //bottom of level
			yv = 0;
			onGround = true;
		}
		
		float levelWidth = gs.level.chunks[0].length * Chunk.WIDTH * Tile.WIDTH - WIDTH;
		if (x > levelWidth / Tile.WIDTH) {
			x = levelWidth / Tile.WIDTH;
			xv = 0;
		}
		
		float levelHeight = gs.level.chunks.length * Chunk.WIDTH * Tile.WIDTH - HEIGHT;
		if (y > levelHeight / Tile.WIDTH) {
			y = levelHeight / Tile.WIDTH;
			yv = 0;
		}
		
		if (xv != 0 || yv != 0) {
			for (int i = 0; i < gs.level.chunks.length; i++) {
				for (int j = 0; j < gs.level.chunks[i].length; j++) {
					for (int k = 0; k < Chunk.WIDTH; k++) {
						for (int l = 0; l < Chunk.WIDTH; l++) {
							Tile t = gs.level.chunks[i][j].getTile(l, k);
							if (t.intersects(this)) {
								if (t.getType() == Tile.Type.COIN) {
									if(!((Coin) t).removed) {
										coins++;
										((Coin) gs.level.chunks[i][j].getTile(l, k)).removed = true;
									}
								} else if (t.getType().isSolid()) {
									System.out.println(t.getX());
									System.out.println(t.getY());
									System.out.println(t.getType().toString());
									System.out.println(getX());
									System.out.println(getY());
									System.out.println();
									x = bx;
									y = by;
									
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
	
	public void render(Graphics g) {
		g.drawImage(player, (int) (x * Tile.WIDTH + GameState.camera.x),
				(int) (Game.SIZE.height - y * Tile.WIDTH + GameState.camera.y), null);
	}
}
