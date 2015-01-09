package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.BoundingBox;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.state.GameState;

public class Tile extends BoundingBox {
	public static enum Type {
		BLANK(false), DIRT(true), GRASS(true), COIN(false), NULL(false);
		
		private boolean solid;
		
		Type(boolean solid) {
			this.solid = solid;
		}
		
		public boolean isSolid() {
			return solid;
		}
		
		public static Type intToType(int i) {
			switch (i) {
			case 0:
				return BLANK;
			case 1:
				return DIRT;
			case 2:
				return GRASS;
			case 3:
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
	
	protected static Image blank, dirt, dirtTRr, dirtTLr, dirtBRr, dirtBLr, grassTL, grassTLr, grassTR, grassTRr, grassBL,
			grassBLr, grassBR, grassBRr, error;
	
	{
		blank = new ImageIcon("res/tiles/blank.png").getImage();
		dirt = new ImageIcon("res/tiles/dirt.png").getImage(); //square dirt (used for all four corners)
		dirtTRr = new ImageIcon("res/tiles/dirt_TR_round.png").getImage();
		dirtTLr = new ImageIcon("res/tiles/dirt_TL_round.png").getImage();
		dirtBRr = new ImageIcon("res/tiles/dirt_BR_round.png").getImage();
		dirtBLr = new ImageIcon("res/tiles/dirt_BL_round.png").getImage();
		grassTL = new ImageIcon("res/tiles/grass_TL.png").getImage();
		grassTLr = new ImageIcon("res/tiles/grass_TL_round.png").getImage();
		grassTR = new ImageIcon("res/tiles/grass_TR.png").getImage();
		grassTRr = new ImageIcon("res/tiles/grass_TR_round.png").getImage();
		grassBL = new ImageIcon("res/tiles/grass_BL.png").getImage();
		grassBLr = new ImageIcon("res/tiles/grass_BL_round.png").getImage();
		grassBR = new ImageIcon("res/tiles/grass_BR.png").getImage();
		grassBRr = new ImageIcon("res/tiles/grass_BR_round.png").getImage();
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
				result[i][j] = new Tile(x * Chunk.WIDTH + j, y * Chunk.HEIGHT + i, t[i][j]); //convert Type[][] to Tile[][]
				if (result[i][j].getType().equals(Type.COIN)) {
					result[i][j] = new Coin(x * Chunk.WIDTH + j, y * Chunk.HEIGHT + i);
				}
			}
		}
		return result;
	}
	
	//LATER make all tiles auto-connect to each other
	
	public void render(Level level, Graphics g) {
		int x = (int) (getX() * PIXEL_WIDTH + GameState.camera.x);
		int y = (int) (getY() * PIXEL_WIDTH + GameState.camera.y);
		
		if (x > Game.SIZE.width + PIXEL_WIDTH || y > Game.SIZE.height + PIXEL_WIDTH || x < -PIXEL_WIDTH || y < -PIXEL_WIDTH)
			return; //no need rendering off screen
			
		if (type == Type.BLANK) {
			g.drawImage(blank, x, y, null);
			return;
		} else if (type == Type.NULL) {
			g.drawImage(error, x, y, null);
			return;
		} else { //auto-connecting tiles
			Type l = level.tileAt((int) getX() - 1, (int) getY()).getType();
			Type r = level.tileAt((int) getX() + 1, (int) getY()).getType();
			Type b = level.tileAt((int) getX(), (int) getY() + 1).getType();
			Type t = level.tileAt((int) getX(), (int) getY() - 1).getType();
			boolean connectedL = l.equals(Type.GRASS) || l.equals(Type.DIRT) || l.equals(Type.NULL); //null for the edge tiles
			boolean connectedR = r.equals(Type.GRASS) || r.equals(Type.DIRT) || r.equals(Type.NULL);
			boolean connectedB = b.equals(Type.GRASS) || b.equals(Type.DIRT) || b.equals(Type.NULL);
			boolean connectedT = t.equals(Type.GRASS) || t.equals(Type.DIRT) || t.equals(Type.NULL);
			
			int offset = (int) PIXEL_WIDTH / 2;
			
			if (type == Type.DIRT) {
				if (connectedT || connectedL) g.drawImage(dirt, x, y, null);
				else g.drawImage(dirtTLr, x, y, null);
				
				if (connectedL || connectedB) g.drawImage(dirt, x, y + offset, null);
				else g.drawImage(dirtBLr, x, y + offset, null);
				
				if (connectedB || connectedR) g.drawImage(dirt, x + offset, y + offset, null);
				else g.drawImage(dirtBRr, x + offset, y + offset, null);
				
				if (connectedR || connectedT) g.drawImage(dirt, x + offset, y, null);
				else g.drawImage(dirtTRr, x + offset, y, null);
			} else if (type == Type.GRASS) {
				if (connectedL) g.drawImage(grassTL, x, y, null);
				else g.drawImage(grassTLr, x, y, null);
				
				if (connectedL || connectedB) g.drawImage(grassBL, x, y + offset, null);
				else g.drawImage(grassBLr, x, y + offset, null);
				
				if (connectedB || connectedR) g.drawImage(grassBR, x + offset, y + offset, null);
				else g.drawImage(grassBRr, x + offset, y + offset, null);
				
				if (connectedR) g.drawImage(grassTR, x + offset, y, null);
				else g.drawImage(grassTRr, x + offset, y, null);
			}
		}
	}
	
	public void update() {}
	
	public Type getType() {
		return type;
	}
	
	public boolean isSolid() {
		return this.type.solid;
	}
}
