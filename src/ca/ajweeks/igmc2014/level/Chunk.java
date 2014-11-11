package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ca.ajweeks.igmc2014.entity.Coin;

public class Chunk {
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	public Tile[][] tiles;
	
	public int x, y;
	
	public Chunk(String fileName, int y, int x) {
		this.x = x;
		this.y = y;
		
		tiles = readFile(fileName);
		
		if (tiles.length == 0) { //file is empty
			System.err.println("Empty file!! File name: " + fileName);
			return;
		}
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
	
	/** @return An array of tile objects which correspond to numbers typed in specified file at fileName OR an array of length 0 if the file is empty */
	private Tile[][] readFile(String fileName) {
		Tile[][] result = new Tile[WIDTH][WIDTH];
		try (FileReader reader = new FileReader(new File(fileName))) {
			char[] c = new char[208]; //TODO improve level file reading
			
			if (reader.read(c) == -1) return new Tile[0][0];
			
			int x = 0, y = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ',') continue;
				else if (String.valueOf(c[i]).matches("[0-9]+")) {
					Tile t = new Tile(Tile.Type.intToType(Character.getNumericValue(c[i])), this.x * WIDTH + x, this.y * WIDTH
							+ y);
					if (t.getType() == Tile.Type.COIN) {
						result[y][x] = new Coin(this.x * WIDTH + x, this.y * WIDTH + y);
					} else {
						result[y][x] = t;
					}
					
					if (x++ > WIDTH - 1) {
						x = 0;
						y++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
