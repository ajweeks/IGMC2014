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
	
	public static final float JUMP_VELOCITY = 1.2F;
	public static final float GRAVITY = -0.12F;
	
	public static final float SPRINT_VELOCITY = 0.7F;
	public static final float WALK_VELOCITY = 0.4F;
	
	public static float maxHorizontalVelocity = WALK_VELOCITY; //The fastest velocity the player can move horizontally
	public static float terminalVelocity = -18.0F; //The fastest velocity the player can fall
	public static float friction = 0.02F; //How quickly the player accelerates
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private static Image image;
	private GameState gs;
	
	private float xv, yv;
	private int coins = 0;
	
	private static final int SPAWN_X = 3, SPAWN_Y = 5;
	private static final int WIDTH = 32, HEIGHT = 64;
	
	public Player(GameState gs) {
		super(SPAWN_X, SPAWN_Y, WIDTH, HEIGHT);
		
		this.gs = gs;
		
		try {
			Player.image = new Image("res/player.png");
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
		
		if (gc.getInput().isKeyPressed(Input.KEY_R)) {
			x = SPAWN_X;
			y = SPAWN_Y;
		}
		
		if (Keyboard.isRightDown(game)) {
			xv += friction;
			if (xv > maxHorizontalVelocity) xv = maxHorizontalVelocity;
		}
		
		if (Keyboard.isLeftDown(game)) {
			xv -= friction;
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
		y += yv; //TODO * delta
		
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
			if (gs.level.collides(x, y).isSolid()) {
				x = bx;
			}
		}
		
		//vertical
		if (yv != 0) {
			if (gs.level.collides(x, yv <= 0 ? y + 1 : y).isSolid()) { //TODO check feet when falling (rather than head)
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
