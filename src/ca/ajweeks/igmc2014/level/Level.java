package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;

public class Level {
	
	public static final int CHUNK_WIDTH = 10;
	public Chunk[][] chunks = new Chunk[2][3]; //3 wide, 2 tall
	public Player player;
	
	private ArrayList<Coin> coins = new ArrayList<>();
	
	public Level(Player player) {
		this.player = player;
		
		chunks[0][0] = new Chunk(CHUNK_WIDTH, "levels/0_0.txt", 0, 0);
		chunks[0][1] = new Chunk(CHUNK_WIDTH, "levels/0_1.txt", 0, 1);
		chunks[0][2] = new Chunk(CHUNK_WIDTH, "levels/0_2.txt", 0, 2);
		chunks[1][0] = new Chunk(CHUNK_WIDTH, "levels/1_0.txt", 1, 0);
		chunks[1][1] = new Chunk(CHUNK_WIDTH, "levels/1_1.txt", 1, 1);
		chunks[1][2] = new Chunk(CHUNK_WIDTH, "levels/1_2.txt", 1, 2);
		
		//TODO fix coin initialization
		coins.add(new Coin(3, 4));
		coins.add(new Coin(4, 4));
		coins.add(new Coin(5, 4));
		coins.add(new Coin(6, 4));
		coins.add(new Coin(3, 5));
		coins.add(new Coin(4, 5));
		coins.add(new Coin(5, 5));
		coins.add(new Coin(6, 5));
	}
	
	public Rectangle getBlock(int xpos, int ypos) {
		int chunkY = ypos / (CHUNK_WIDTH * Tile.TILE_WIDTH);
		int chunkX = xpos / (CHUNK_WIDTH * Tile.TILE_WIDTH);
		
		int y = (ypos % (CHUNK_WIDTH * Tile.TILE_WIDTH)) / Tile.TILE_WIDTH;
		int x = (xpos % (CHUNK_WIDTH * Tile.TILE_WIDTH)) / Tile.TILE_WIDTH;
		
		
		
 		return chunks[chunkY][chunkX].tiles[0].getR(); //everything but blanks are taken
	}
	
	public void update(double delta) {
		player.update(delta);
		
		for (Coin c : coins) {
			c.update();
			if (c.removed) coins.remove(c);
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
						int x = l * Tile.TILE_WIDTH + j * CHUNK_WIDTH * Tile.TILE_WIDTH + GameState.camera.x;
						int y = Tile.TILE_WIDTH * CHUNK_WIDTH * -i + k * Tile.TILE_WIDTH + GameState.camera.y;
						chunks[i][j].tiles[k * CHUNK_WIDTH + l].render(x, y, g);
					}
				}
			}
		}
		
		for (Coin c : coins) {
			c.render(g);
		}
		
		player.render(g);
	}
}
