package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;

public class Tile {
	
	public static final int TILE_WIDTH = 64;
	
	public static final int BLANK = 0;
	public static final int DIRT = 1;
	public static final int GRASS = 2;
	public static final int GRASS_LEFT = 3;
	public static final int GRASS_RIGHT = 4;
	
	private Image blank = new ImageIcon("res/tiles/BLANK.png").getImage();
	private Image dirt = new ImageIcon("res/tiles/DIRT.png").getImage();
	private Image grass = new ImageIcon("res/tiles/GRASS.png").getImage();
	private Image grassLeft = new ImageIcon("res/tiles/GRASS_LEFT.png").getImage();
	private Image grassRight = new ImageIcon("res/tiles/GRASS_RIGHT.png").getImage();
	
	private Rectangle r;
	private int x, y;
	private int type;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
	
	public Rectangle getR() {
		return r;
	}
	
	public Tile(int x, int y, int type) {
		this.x = x;
		this.y = y;
		r = new Rectangle(x, y, TILE_WIDTH, TILE_WIDTH);
		this.type = type;
	}
	
	public void render(int x, int y, Graphics g) {
		if (x > Game.SIZE.width + TILE_WIDTH || y > Game.SIZE.height + TILE_WIDTH || x < -TILE_WIDTH || y < -TILE_WIDTH) return; //no need rendering off screen
		switch (type) {
		case BLANK:
			g.drawImage(blank, x, y, null);
			break;
		case DIRT:
			g.drawImage(dirt, x, y, null);
			break;
		case GRASS:
			g.drawImage(grass, x, y, null);
			break;
		case GRASS_RIGHT:
			g.drawImage(grassRight, x, y, null);
			break;
		case GRASS_LEFT:
			g.drawImage(grassLeft, x, y, null);
			break;
		}
	}
	
}
