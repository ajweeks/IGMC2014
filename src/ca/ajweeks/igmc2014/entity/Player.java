package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.sound.Sound;

public class Player extends Mob {
	
	public final static int JUMP_SPEED = -175;
	
	public final static int SPRINT_SPEED = 16;
	public final static int WALK_SPEED = 10;
	public static int speed = WALK_SPEED;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	private Image image = new ImageIcon("res/player.png").getImage();
	
	//public int jumps = 0; //TODO can't remember why this is here...... to allow for triple jumps maybe?
	
	public Player() {
		super(110, 200);
		x = Game.SIZE.width / 2 - width / 2;
		y = Game.SIZE.height / 2 - height / 2;
	}
	
	public boolean hasJumped = false;
	
	@Override
	public void update(double delta) {
		if (Game.input.right.down && xv == 0) xv = 1;
		if (Game.input.left.down && xv == 0) xv = -1;
		
		if (xv == 1 && !Game.input.right.down) xv = 0;
		if (xv == -1 && !Game.input.left.down) xv = 0;
		
		if (!Game.input.right.down && !Game.input.left.down) xv = 0;
		
		x += xv * speed * delta;
		
		if (x + width > Game.SIZE.width) x = Game.SIZE.width - width;
		if (x < 0) x = 0;
		if (y > Game.SIZE.height - height - 1) {
			onGround = true;
			yv = 0;
			y = Game.SIZE.height - height;
		} else onGround = false;
		
		if (Game.input.space.clicked) {
			if(!hasJumped) hasJumped = true;
			if (onGround) {
				Sound.JUMP.play();
				hasDoubleJumped = false;
				onGround = false;
				yv = JUMP_SPEED;
			} else { //not on ground
				if (!hasDoubleJumped) {
					Sound.JUMP.play();
					hasDoubleJumped = true;
					onGround = false;
					yv = JUMP_SPEED;
				}
			}
		}
		
		if (onGround) hasDoubleJumped = false;
		else {
			yv += GRAVITY;
			y += (yv * delta) / 6;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public static void setSpeed(int speed) {
		Player.speed = speed;
	}
}
