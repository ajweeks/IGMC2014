package ca.ajweeks.rpg.entity;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.RPG;

public class Player extends Mob {
	
	public static int speed = 5;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround;
	
	private int width = 100;
	private int height = 220;
	
	public Player() {
		x = RPG.SIZE.width / 2 - width / 2;
		y = RPG.SIZE.height / 2 - height / 2;
	}
	
	@Override
	public void update() {
		if (RPG.input.right.down && xa == 0) xa = 1;
		if (RPG.input.left.down && xa == 0) xa = -1;
		
		if (xa == 1 && !RPG.input.right.down) xa = 0;
		if (xa == -1 && !RPG.input.left.down) xa = 0;
		
		if (!RPG.input.right.down && !RPG.input.left.down) xa = 0;
		
		x += xa * speed;
		if (x + width > RPG.SIZE.width) x = RPG.SIZE.width - width;
		if (x < 0) x = 0;
		if (y > RPG.SIZE.height - height) {
			onGround = true;
			ya = 0;
			y = RPG.SIZE.height - height + 1;
		} else onGround = false;
		
		if (RPG.input.space.clicked && !onGround) {
			if (!hasDoubleJumped) {
				hasDoubleJumped = true;
				onGround = false;
				ya = -20;
			}
		} else if (RPG.input.space.clicked && onGround) {
			hasDoubleJumped = false;
			onGround = false;
			ya = -20;
		}
		
		//TODO decrease gravity
		ya++;
		y += ya;
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
	}
}
