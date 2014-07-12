package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.state.GameState;

public class Chunk {
	
	public static final int WIDTH = 10;
	public Tile[] tiles;
	
	private ArrayList<Coin> coins = new ArrayList<>();
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
					result[a] = new Tile(Tile.Type.intToType(Character.getNumericValue(c[i])));
					if (result[a].getType() == Tile.Type.COIN) {
						coins.add(new Coin(x * WIDTH + a % WIDTH, y * WIDTH + a / WIDTH));
					}
					a++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(double delta) {
		for (Coin c : coins) {
			c.update();
			if (c.removed) coins.remove(c);
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
		
		for (Coin c : coins) {
			c.render(g, x, y);
		}
	}
	
}
