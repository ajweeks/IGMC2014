package ca.ajweeks.igmc2014.level;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.entity.Player;

public class Level {
	
	public Player player;
	public Chunk[][] chunks;
	
	public Level(Player player, Game game, Chunk[][] chunks) { //LATER game arg is unused
		this.player = player;
		this.chunks = chunks;
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
