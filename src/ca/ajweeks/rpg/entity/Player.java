package ca.ajweeks.rpg.entity;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.level.Level;

public class Player extends Mob {
	
	private Level level;
	
	public static int speed = 5;
	
	private int width = 100;
	private int height = 220;
	
	public Player(Level level) {
		this.level = level;
		x = RPG.SIZE.width / 2 - width / 2;
		y = RPG.SIZE.height / 2 - height / 2;
	}
	
	@Override
	public void update() {
		x += xa * speed;
		y += ya * speed;
		if (x + width > RPG.SIZE.width) x = RPG.SIZE.width - width;
		if (x < 0) x = 0;
		if (y + height > RPG.SIZE.height) y = RPG.SIZE.height - height;
		if (y < 0) y = 0;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.GREEN);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
	}
	
}
