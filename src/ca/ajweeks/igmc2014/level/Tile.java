package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;

public class Tile {
	public enum Type {
		//@formatter:off
		BLANK(new ImageIcon("res/tiles/BLANK.png").getImage(), false), 
		DIRT(new ImageIcon("res/tiles/DIRT.png").getImage(), true), 
		GRASS(new ImageIcon("res/tiles/GRASS.png").getImage(), true), 
		GRASS_LEFT(new ImageIcon("res/tiles/GRASS_LEFT.png").getImage(), true), 
		GRASS_RIGHT(new ImageIcon("res/tiles/GRASS_RIGHT.png").getImage(), true), 
		COIN(new ImageIcon("res/tiles/BLANK.png").getImage(), false), 
		NULL(new ImageIcon("res/tiles/BLANK.png").getImage(), false);
		//@formatter:on
		
		private Image image;
		private boolean solid;
		
		Type(Image image, boolean solid) {
			this.image = image;
			this.solid = solid;
		}
		
		public boolean isSolid() {
			return solid;
		}
		
		//TODO make grass tile auto-connect to other grass tiles (then remove grass_left & grass_right)
		
		public static Type intToType(int i) {
			switch (i) {
			case 0:
				return BLANK;
			case 1:
				return DIRT;
			case 2:
				return GRASS;
			case 3:
				return GRASS_LEFT;
			case 4:
				return GRASS_RIGHT;
			case 5:
				return COIN;
			default:
				return NULL;
			}
		}
	}
	
	public static final int WIDTH = 64;
	
	private Type type;
	
	public Type getType() {
		return type;
	}
	
	public Tile(Type type) {
		this.type = type;
	}
	
	public void render(int x, int y, Graphics g) {
		if (x > Game.SIZE.width + WIDTH || y > Game.SIZE.height + WIDTH || x < -WIDTH || y < -WIDTH) return; //no need rendering off screen
		g.drawImage(type.image, x, y, null);
	}
	
}
