package ca.ajweeks.igmc2014.level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ca.ajweeks.igmc2014.Game;

public class Tile extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public enum Type {
		BLANK(false), DIRT(true), GRASS(true), GRASS_LEFT(true), GRASS_RIGHT(true), COIN(false), NULL(false);
		
		private boolean solid;
		
		Type(boolean solid) {
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
	
	protected static Image blank, dirt, grass, grassLeft, grassRight, error;
	
	{
		try {
			blank = new Image("res/tiles/BLANK.png");
			dirt = new Image("res/tiles/DIRT.png");
			grass = new Image("res/tiles/GRASS.png");
			grassLeft = new Image("res/tiles/GRASS_LEFT.png");
			grassRight = new Image("res/tiles/GRASS_RIGHT.png");
			error = new Image("res/tiles/ERROR.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static final int WIDTH = 64;
	
	private Type type;
	
	public Tile(Type type, int x, int y, int height, int width) {
		super(x, y, width, height);
		this.type = type;
	}
	
	public void render(int x, int y, Graphics g) {
		if (x > Game.SIZE.width + WIDTH || y > Game.SIZE.height + WIDTH || x < -WIDTH || y < -WIDTH) return; //no need rendering off screen
		Image image;
		switch (type) {
		case BLANK:
			image = blank;
			break;
		case COIN:
			image = error;
			break;
		case DIRT:
			image = dirt;
			break;
		case GRASS:
			image = grass;
			break;
		case GRASS_LEFT:
			image = grassLeft;
			break;
		case GRASS_RIGHT:
			image = grassRight;
			break;
		case NULL:
		default:
			image = error;
			break;
		}
		g.drawImage(image, x, y);
	}
	
	public void update() {}
	
	public Type getType() {
		return type;
	}
}
