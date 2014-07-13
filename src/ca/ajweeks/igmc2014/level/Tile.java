package ca.ajweeks.igmc2014.level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ca.ajweeks.igmc2014.Game;

@SuppressWarnings("serial")
public class Tile extends Rectangle {
	
	public enum Type {
		//@formatter:off
		BLANK("res/tiles/BLANK.png", false), 
		DIRT("res/tiles/DIRT.png", true), 
		GRASS("res/tiles/GRASS.png", true), 
		GRASS_LEFT("res/tiles/GRASS_LEFT.png", true), 
		GRASS_RIGHT("res/tiles/GRASS_RIGHT.png", true), 
		COIN("res/tiles/BLANK.png", false), 
		NULL("res/tiles/BLANK.png", false);
		//@formatter:on
		
		private Image image;
		private boolean solid;
		
		Type(String image, boolean solid) {
			try {
				this.image = new Image(image);
			} catch (SlickException e) {
				e.printStackTrace();
			}
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
	
	public Tile(Type type, int x, int y, int height, int width) {
		super(x, y, width, height);
		this.type = type;
	}
	
	public void render(int x, int y, Graphics g) {
		if (x > Game.SIZE.width + WIDTH || y > Game.SIZE.height + WIDTH || x < -WIDTH || y < -WIDTH) return; //no need rendering off screen
		g.drawImage(type.image, x, y);
	}
	
}
