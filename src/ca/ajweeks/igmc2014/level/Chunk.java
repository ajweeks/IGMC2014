package ca.ajweeks.igmc2014.level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.Graphics;

import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.state.GameState;

public class Chunk {
	
	public static final int WIDTH = 10;
	public Tile[] tiles;
	
	private int x, y;
	
	public Chunk(String fileName, int y, int x) {
		this.x = x;
		this.y = y;
		
		tiles = readFile(fileName);
		
		if (tiles.length == 0) { //file is empty
			System.err.println("Empty file!! fileName: " + fileName);
			return;
		}
	}
	
	/** @return an array of tile objects which correspond to numbers typed in specified file at fileName OR an array of length 0 if the file is empty*/
	private Tile[] readFile(String fileName) {
		Tile[] result = new Tile[WIDTH * WIDTH];
		try (FileReader reader = new FileReader(new File(fileName))) {
			char[] c = new char[208]; //really bad..
			
			if (reader.read(c) == -1) return new Tile[0];
			
			int a = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ',') continue;
				else if (String.valueOf(c[i]).matches("[0-9]+")) {
					Tile t = new Tile(Tile.Type.intToType(Character.getNumericValue(c[i])), x * WIDTH + a % WIDTH, y * WIDTH + a
							/ WIDTH, 1, 1);
					if (t.getType() == Tile.Type.COIN) {
						result[a] = new Coin(x * WIDTH + a % WIDTH, y * WIDTH + a / WIDTH, Tile.WIDTH, Tile.WIDTH);
					} else {
						result[a] = t;
					}
					a++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[(y * WIDTH) + x];
	}
	
	public void update(double delta) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].update();
		}
	}
	
	public void render(Graphics g, int i, int j) {
		for (int k = 0; k < Chunk.WIDTH; k++) {
			for (int l = 0; l < Chunk.WIDTH; l++) {
				int x = (int) (l * Tile.WIDTH + j * Chunk.WIDTH * Tile.WIDTH + GameState.camera.x);
				int y = (int) (Tile.WIDTH * Chunk.WIDTH * -i + k * Tile.WIDTH + GameState.camera.y);
				tiles[k * Chunk.WIDTH + l].render(x, y, g);
			}
		}
	}
}
