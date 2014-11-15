package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;

public class Chunk {
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	public Tile[][] tiles;
	
	public int x, y;
	
	public Chunk(int x, int y, Tile[][] tiles) {
		this.x = x;
		this.y = y;
		this.tiles = tiles;
	}
	
	public void update(double delta) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].update();
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < Chunk.WIDTH; i++) {
			for (int j = 0; j < Chunk.WIDTH; j++) {
				tiles[i][j].render(g);
			}
		}
	}
}
