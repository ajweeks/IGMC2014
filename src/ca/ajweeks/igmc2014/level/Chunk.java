package ca.ajweeks.igmc2014.level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Chunk {
	
	private int[] chunk;
	private int size;
	
	public int[] getChunk() {
		return chunk;
	}
	
	public Chunk(int size, String fileName) {
		this.size = size;
		chunk = readFile(fileName);
	}
	
	private int[] readFile(String fileName) {
		int[] result = new int[size * size];
		try (FileReader reader = new FileReader(new File(fileName))) {
			char[] c = new char[208];
			reader.read(c);
			
			int a = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ',') continue;
				else if (String.valueOf(c[i]).matches("[0-9]+")) result[a++] = Character.getNumericValue(c[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
