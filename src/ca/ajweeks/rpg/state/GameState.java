package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;
import ca.ajweeks.rpg.entity.Player;
import ca.ajweeks.rpg.level.Level;

public class GameState extends BasicState {
	
	private Level level;
	public static Player player;
	
	public GameState() {
		level = new Level();
		player = new Player();
		level.addEntity(player);
	}
	
	public void update() {
		if (RPG.input.esc) {
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		level.update();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		level.render(g);
	}
	
}
