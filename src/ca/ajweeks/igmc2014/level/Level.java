package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Entity;

public class Level {
	
	private static final int CHUNK_WIDTH = 10;
	private static final int TILE_WIDTH = 64;
	
	private static final int BLANK = 0;
	private static final int DIRT = 1;
	private static final int GRASS = 2;
	private static final int GRASS_LEFT = 3;
	private static final int GRASS_RIGHT = 4;
	
	private Image blank = new ImageIcon("res/tiles/BLANK.png").getImage();
	private Image dirt = new ImageIcon("res/tiles/DIRT.png").getImage();
	private Image grass = new ImageIcon("res/tiles/GRASS.png").getImage();
	private Image grassLeft = new ImageIcon("res/tiles/GRASS_LEFT.png").getImage();
	private Image grassRight = new ImageIcon("res/tiles/GRASS_RIGHT.png").getImage();
	
	private ArrayList<Entity> entites;
	private int[][][] chunks = new int[1][2][CHUNK_WIDTH * CHUNK_WIDTH]; //2 wide, 1 tall
	
	public Level() {
		entites = new ArrayList<>();
		
		chunks[0][0] = new Chunk(CHUNK_WIDTH, "levels/level_0_0.txt").getChunk();
		chunks[0][1] = new Chunk(CHUNK_WIDTH, "levels/level_0_1.txt").getChunk();
	}
	
	public void addEntity(Entity entity) {
		entites.add(entity);
	}
	
	public void update(double delta) {
		for (int i = 0; i < entites.size(); i++) {
			entites.get(i).update(delta);
		}
	}
	
	public void render(Graphics g) {
		//chunks
		for (int i = 0; i < chunks.length; i++) { //rows (y)
			for (int j = 0; j < chunks[i].length; j++) { //columns (x)
				//for each chunk..
				for (int k = 0; k < CHUNK_WIDTH; k++) {
					for (int l = 0; l < CHUNK_WIDTH; l++) {
						//for each square..
						int x = l * TILE_WIDTH + j * CHUNK_WIDTH * TILE_WIDTH;
						int y = Game.SIZE.height - TILE_WIDTH * CHUNK_WIDTH + k * TILE_WIDTH;
						switch (chunks[i][j][k * CHUNK_WIDTH + l]) {
						case BLANK:
							g.drawImage(blank, x, y, null);
							break;
						case DIRT:
							g.drawImage(dirt, x, y, null);
							break;
						case GRASS:
							g.drawImage(grass, x, y, null);
							break;
						case GRASS_RIGHT:
							g.drawImage(grassRight, x, y, null);
							break;
						case GRASS_LEFT:
							g.drawImage(grassLeft, x, y, null);
							break;
						}
					}
				}
			}
		}
		
		//entities
		for (Entity e : entites) {
			e.render(g);
		}
	}
	
}
