package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.Sound;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.level.Level;

public class GameState extends BasicState {
	
	private Level level;
	public static Player player;
	
	public GameState() {
		level = new Level();
		player = new Player();
		level.addEntity(player);
	}
	
	public void update() {
		if (Game.input.esc.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		level.update();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		level.render(g);
	}
	
}
