package ca.ajweeks.igmc2014.level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Chunk {
	
	public final int x, y; //top left corner of this chunk
	public Tile[] tiles;
	
	private final int CHUNK_WIDTH;
	
	public Chunk(int chunkWidth, String fileName, int x, int y) {
		this.CHUNK_WIDTH = chunkWidth;
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
		Tile[] result = new Tile[CHUNK_WIDTH * CHUNK_WIDTH];
		try (FileReader reader = new FileReader(new File(fileName))) {
			char[] c = new char[208];
			
			if (reader.read(c) == -1) return new Tile[0];
			
			int a = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ',') continue;
				else if (String.valueOf(c[i]).matches("[0-9]+"))
					result[a++] = new Tile(i % CHUNK_WIDTH, i / CHUNK_WIDTH, Character.getNumericValue(c[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
