package ca.ajweeks.igmc2014.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.BoundingBox;
import ca.ajweeks.igmc2014.state.GameState;

public class Tile extends BoundingBox {
	public static enum Type {
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
		
		public static Type[][] intarrayToTypearray(int[][] n) {
			Type[][] result = new Type[n.length][n[0].length];
			for (int i = 0; i < n.length; i++) {
				for (int j = 0; j < n[i].length; j++) {
					result[i][j] = intToType(n[i][j]);
				}
			}
			return result;
		}
	}
	
	protected static Image blank, dirt, grass, grassLeft, grassRight, error;
	
	{
		blank = new ImageIcon("res/tiles/BLANK.png").getImage();
		dirt = new ImageIcon("res/tiles/DIRT.png").getImage();
		grass = new ImageIcon("res/tiles/GRASS.png").getImage();
		grassLeft = new ImageIcon("res/tiles/GRASS_LEFT.png").getImage();
		grassRight = new ImageIcon("res/tiles/GRASS_RIGHT.png").getImage();
		error = new ImageIcon("res/tiles/ERROR.png").getImage();
	}
	
	public static final int PIXEL_WIDTH = 64;
	
	private Type type;
	
	public Tile(int x, int y, Type type) {
		super(x, y, 1, 1);
		this.type = type;
	}
	
	public static Tile[][] IntArrayToTileArray(int x, int y, int[][] n) {
		if (n.length == 0 || n[0].length == 0) return new Tile[][] { {} };
		Type[][] t = Type.intarrayToTypearray(n); //convert int[][] to Type[][]
		Tile[][] result = new Tile[t.length][t[0].length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = new Tile(x + j, x + i, t[i][j]); //convert Type[][] to Tile[][]
			}
		}
		return result;
	}
	
	//LATER make all tiles auto-connect to each other
	
	public void render(Graphics g) {
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
		
		g.setColor(Color.white);
		
		int x = (int) (getX() * Tile.PIXEL_WIDTH + GameState.camera.x);
		int y = (int) (Game.SIZE.height - getY() * Tile.PIXEL_WIDTH + GameState.camera.y);
		
		if (x > Game.SIZE.width + PIXEL_WIDTH || y > Game.SIZE.height + PIXEL_WIDTH || x < -PIXEL_WIDTH || y < -PIXEL_WIDTH)
			return; //no need rendering off screen
			
		g.drawImage(image, x, y, null);
	}
	
	public void update() {}
	
	public Type getType() {
		return type;
	}
}
