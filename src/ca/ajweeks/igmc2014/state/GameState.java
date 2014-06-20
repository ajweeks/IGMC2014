package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.sound.Sound;

public class GameState extends BasicState {
	
	private Level level;
	public static Player player;
	
	Rectangle one = new Rectangle(750, 500, 205, 100);
	
	public GameState() {
		level = new Level();
		player = new Player();
		level.addEntity(player);
	}
	
	public void update(double delta) {
		if (Game.input.esc.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		level.update(delta);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setColor(Color.RED);
		g.fillRect(one.x, one.y, one.width, one.height);
		
		level.render(g);
	}
	
}
