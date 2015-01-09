package ca.ajweeks.igmc2014.level;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;

public class Level {
	
	public Player player;
	public Chunk[][] chunks;
	
	public int width, height; //number of chunks wide, tall
			
	public Level(Player player, Chunk[][] chunks) {
		this.player = player;
		this.chunks = chunks;
		
		width = chunks[0].length;
		height = chunks.length;
	}
	
	public void update(double delta) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				chunks[y][x].update(delta);
			}
		}
		player.update(this, delta);
	}
	
	/** @return the type of the tile to the tile at x,y OR Type.NULL if this tile is off the map */
	public Tile tileAt(int x, int y) {
		if (x < 0 || x >= Chunk.WIDTH * this.width || y < 0 || y >= Chunk.HEIGHT * this.height) return new Tile(0, 0,
				Tile.Type.NULL);
		else return chunks[y / Chunk.HEIGHT][x / Chunk.WIDTH].tiles[y % Chunk.HEIGHT][x % Chunk.WIDTH];
	}
	
	public void render(Graphics g) {
		//background
		for (int i = 0; i < chunks.length; i++) {
			for (int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].render(this, g);
			}
		}
		
		if (Game.renderDebug) {
			// Where the mouse is on the screen
			int mouseX = Game.mouse.getX();
			int mouseY = Game.mouse.getY();
			
			// Where the mouse is in the game world
			int absMouseX = (int) (mouseX - GameState.camera.x);
			int absMouseY = (int) (mouseY - GameState.camera.y);
			
			// The coordinates of the tile the mouse is hovering over
			int x = (absMouseX % (Chunk.WIDTH * Tile.PIXEL_WIDTH)) / Tile.PIXEL_WIDTH;
			int y = (absMouseY % (Chunk.HEIGHT * Tile.PIXEL_WIDTH)) / Tile.PIXEL_WIDTH;
			
			// Outline the tile which is being hovered over
			g.setColor(Color.RED);
			g.drawRect((int) (absMouseX - absMouseX % Tile.PIXEL_WIDTH + GameState.camera.x), (int) (absMouseY - absMouseY
					% Tile.PIXEL_WIDTH + GameState.camera.y), Tile.PIXEL_WIDTH, Tile.PIXEL_WIDTH);
			
			int i = absMouseY / (Chunk.HEIGHT * Tile.PIXEL_WIDTH);
			int j = absMouseX / (Chunk.WIDTH * Tile.PIXEL_WIDTH);
			
			if (i >= 0 && i < chunks.length && j >= 0 && j < chunks[i].length && y >= 0 && y < chunks[i][j].tiles.length
					&& x >= 0 && x < chunks[i][j].tiles[y].length) {
				Tile t = chunks[i][j].tiles[y][x];
				int xo = (mouseX > Game.SIZE.width / 2) ? -50 : 16;
				int yo = (mouseY > Game.SIZE.height / 2) ? -10 : 20;
				g.setColor(Color.WHITE);
				g.setFont(Game.fontDebug.deriveFont(14.0f));
				g.drawString("" + t.getType(), mouseX + xo, mouseY + yo);
				g.drawString("x: " + (int) t.getX(), mouseX + xo, mouseY + yo + 10);
				g.drawString("y: " + (int) t.getY(), mouseX + xo, mouseY + yo + 10 * 2);
			}
		}
		
		//LATER add foreground objects (& parallax effect)
		
		player.render(g);
		
		//coins
		g.setColor(Color.yellow);
		g.setFont(Game.font24);
		String coins = "x " + String.valueOf(player.getCoins());
		g.drawString(coins, Game.SIZE.width - (g.getFontMetrics().stringWidth(coins)) - 2, Game.SIZE.height - 6);
		g.setColor(Color.white);
		g.drawImage(Coin.getFlatCoinImage(), Game.SIZE.width - (g.getFontMetrics().stringWidth(coins)) - 33,
				Game.SIZE.height - 28, null);
	}
}
