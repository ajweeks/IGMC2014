package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.gfx.Camera;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.sound.Sound;

public class GameState extends BasicState {
	
	public Level level;
	public static Player player;
	public static Camera camera;
	
	public GameState() {
		player = new Player(this);
		level = new Level(player);
		camera = new Camera(player);
	}
	
	public void update(double delta) {
		if (Game.input.esc.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		camera.update(delta);
		level.update(delta);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		level.render(g);
	}
	
}
