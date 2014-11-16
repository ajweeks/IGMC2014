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
	
	public int width, height;
	
	public Level(Player player, Chunk[][] chunks) { //LATER game arg is unused
		this.player = player;
		this.chunks = chunks;
		
		width = chunks[0].length;
		height = chunks.length;
	}
	
	public void update(double delta) {
		for (int i = 0; i < chunks.length; i++) { //rows (y)
			for (int j = 0; j < chunks[i].length; j++) { //columns (x)
				chunks[i][j].update(delta);
			}
		}
		player.update(this, delta);
	}
	
	public void render(Graphics g) {
		//background
		for (int i = 0; i < chunks.length; i++) {
			for (int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].render(g);
			}
		}
		
		if (Game.renderDebug) {
			int mouseX = Game.mouse.getX();
			int mouseY = Game.mouse.getY();
			
			int absMouseX = (int) (mouseX - GameState.camera.x);
			int absMouseY = (int) (mouseY - GameState.camera.y);
			
			int x = (absMouseX % (Chunk.WIDTH * Tile.PIXEL_WIDTH)) / Tile.PIXEL_WIDTH;
			int y = (absMouseY % (Chunk.HEIGHT * Tile.PIXEL_WIDTH)) / Tile.PIXEL_WIDTH;
			
			g.setColor(Color.RED);
			g.drawRect((int) (absMouseX - absMouseX % Tile.PIXEL_WIDTH + GameState.camera.x), (int) (absMouseY - absMouseY
					% Tile.PIXEL_WIDTH + GameState.camera.y), Tile.PIXEL_WIDTH, Tile.PIXEL_WIDTH);
			
			g.setColor(Color.white);
			g.drawString("mx: " + mouseX, Game.SIZE.width - 54, 20);
			g.drawString("(abs) mx: " + absMouseX, Game.SIZE.width - 86, 35);
			g.drawString("my: " + mouseY, Game.SIZE.width - 54, 50);
			g.drawString("(abs) my: " + absMouseY, Game.SIZE.width - 86, 65);
			
			int i = absMouseY / (Chunk.HEIGHT * Tile.PIXEL_WIDTH);
			int j = absMouseX / (Chunk.WIDTH * Tile.PIXEL_WIDTH);
			
			if (i >= 0 && i < chunks.length && j >= 0 && j < chunks[i].length && y >= 0 && y < chunks[i][j].tiles.length
					&& x >= 0 && x < chunks[i][j].tiles[y].length) {
				Tile t = chunks[i][j].tiles[y][x];
				g.drawString("" + t.getType(), mouseX, mouseY);
				g.drawString("" + t.getX(), mouseX + 15, mouseY + 10);
				g.drawString("" + t.getY(), mouseX + 15, mouseY + 20);
			}
		}
		
		//LATER add foreground objects (& parallax effect)
		
		player.render(g);
		
		//coins
		g.setColor(Color.yellow);
		g.setFont(Game.font24);
		String coins = "x " + String.valueOf(player.getCoins());
		g.drawString(coins, Game.SIZE.width - (14 * coins.length()) - 2, Game.SIZE.height - 25);
		g.setColor(Color.white);
		g.drawImage(Coin.getFlatCoinImage(), Game.SIZE.width - (14 * (coins.length())) - 32, Game.SIZE.height - 28, null);
	}
}
