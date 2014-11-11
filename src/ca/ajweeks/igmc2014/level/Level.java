package ca.ajweeks.igmc2014.level;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.entity.Player;

public class Level {
	
	public Chunk[][] chunks;
	public Player player;
	
	private Game game;
	
	public Level(Player player, Game game, int level) {
		this.game = game;
		this.player = player;
		
		chunks = new Chunk[][] {
				{ new Chunk(// 
						"levels/" + //
								String.valueOf(level) + //
								"/0_0.txt", 0, 0), new Chunk("levels/" + String.valueOf(level) + "/0_1.txt", 0, 1),
						new Chunk("levels/" + String.valueOf(level) + "/0_2.txt", 0, 2),
						new Chunk("levels/" + String.valueOf(level) + "/0_3.txt", 0, 3) },
				{
				
				new Chunk("levels/" + String.valueOf(level) + "/1_0.txt", 1, 0),
						new Chunk("levels/" + String.valueOf(level) + "/1_1.txt", 1, 1),
						new Chunk("levels/" + String.valueOf(level) + "/1_2.txt", 1, 2),
						new Chunk("levels/" + String.valueOf(level) + "/1_3.txt", 1, 3) } };
	}
	
	public void update(double delta) {
		for (int i = 0; i < chunks.length; i++) { //rows (y)
			for (int j = 0; j < chunks[i].length; j++) { //columns (x)
				chunks[i][j].update(delta);
			}
		}
		player.update(delta);
	}
	
	public void render(Graphics g) {
		//background
		for (int i = 0; i < chunks.length; i++) {
			for (int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].render(g);
			}
		}
		
		//LATER add foreground objects (& parallax effect)
		
		//player
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
