package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.achievements.Achievement;
import ca.ajweeks.igmc2014.entity.AchievementParticle;
import ca.ajweeks.igmc2014.entity.Particle;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.sound.Sound;

public class GameState extends BasicState {
	
	private Level level;
	public static Player player;
	
	Rectangle one = new Rectangle(750, 500, 205, 100);
	ArrayList<Particle[]> p; //all particles
	
	public GameState() {
		level = new Level();
		player = new Player();
		level.addEntity(player);
		p = new ArrayList<>();
	}
	
	boolean firstJump = false;
	
	public void update(double delta) {
		if (Game.input.esc.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (player.hasJumped && !firstJump) {
			firstJump = true;
			p.add(new AchievementParticle[] { new AchievementParticle(Game.SIZE.width - 245, 35, 220, 65, 150, Achievement.JUMPER
					.getMessage()) });
		}
		
		for (int i = 0; i < p.size(); i++) {
			boolean removed = true;
			
			for (int j = 0; j < p.get(i).length; j++) {
				p.get(i)[j].update(delta);
				if (!p.get(i)[j].removed) removed = false; //if there's at least one which is still alive, we don't remove the whole thing 
			}
			
			if (removed) {
				p.remove(p.get(i));
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
		
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.get(i).length; j++) {
				if (!p.get(i)[j].removed) p.get(i)[j].render(g);
			}
		}
		
		level.render(g);
	}
	
}
