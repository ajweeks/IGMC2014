package ca.ajweeks.igmc2014.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.entity.Player;

public class Level {
	
	public Chunk[][] chunks = new Chunk[2][3]; //3 wide, 2 tall
	public Player player;
	
	public Level(Player player) {
		this.player = player;
		
		chunks[0][0] = new Chunk("levels/1/0_0.txt", 0, 0);
		chunks[0][1] = new Chunk("levels/1/0_1.txt", 0, 1);
		chunks[0][2] = new Chunk("levels/1/0_2.txt", 0, 2);
		chunks[1][0] = new Chunk("levels/1/1_0.txt", 1, 0);
		chunks[1][1] = new Chunk("levels/1/1_1.txt", 1, 1);
		chunks[1][2] = new Chunk("levels/1/1_2.txt", 1, 2);
	}
	
	public Tile.Type collides(double xpos, double ypos) {
		return chunks[(int) ypos / Chunk.WIDTH][(int) xpos / Chunk.WIDTH].collides(xpos, ypos);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		for (int i = 0; i < chunks.length; i++) { //rows (y)
			for (int j = 0; j < chunks[i].length; j++) { //columns (x)
				chunks[i][j].update(delta);
			}
		}
		
		player.update(container, game, delta);
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < chunks.length; i++) {
			for (int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].render(g, i, j);
			}
		}
		
		player.render(g);
		
		g.setColor(Color.yellow);
		g.setFont(Game.font24);
		String s = "x " + String.valueOf(player.getCoins());
		g.drawString(s, Game.SIZE.width - (14 * s.length()) - 2, Game.SIZE.height - 5);
		g.drawImage(Coin.getFlatCoinImage(), Game.SIZE.width - (14 * (s.length())) - 32, Game.SIZE.height - 28, null);
	}
}
