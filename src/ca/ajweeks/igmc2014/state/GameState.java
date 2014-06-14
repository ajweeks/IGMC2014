package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.entity.SmokeParticle;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.sound.Sound;

public class GameState extends BasicState {
	
	private Level level;
	public static Player player;
	
	Rectangle one = new Rectangle(750, 500, 205, 100);
	ArrayList<SmokeParticle[]> s; //all smoke particles
	
	public GameState() {
		level = new Level();
		player = new Player();
		level.addEntity(player);
		s = new ArrayList<>();
	}
	
	public void update(double delta) {
		if (Game.input.esc.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (Game.input.lM.down) {
			Game.input.lM.down = false;
			s.add(SmokeParticle.randomParticles(50));
		}
		
		for (int i = 0; i < s.size(); i++) {
			boolean removed = true;
			
			for (int j = 0; j < s.get(i).length; j++) {
				s.get(i)[j].update(delta);
				if (!s.get(i)[j].removed) removed = false; //if theres at least one which is still alive, we don't remove the whole thing 
			}
			
			if (removed) {
				s.remove(s.get(i));
				if (i > 0) i--;
			}
		}
		
		level.update(delta);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setColor(Color.RED);
		g.fillRect(one.x, one.y, one.width, one.height);
		
		for (int i = 0; i < s.size(); i++) {
			for (int j = 0; j < s.get(i).length; j++) {
				if (!s.get(i)[j].removed) s.get(i)[j].render(g);
			}
		}
		
		level.render(g);
	}
	
}
