package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.gfx.Camera;
import ca.ajweeks.igmc2014.level.Level;

public class GameState extends BasicState {
	
	public Level level;
	public static Player player;
	public static Camera camera;
	
	public GameState() {
		player = new Player(this);
		level = new Level(player);
		camera = new Camera(player, level);
	}
	
	public void update(double delta) {
		if (Game.input.esc.clicked) Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		
		camera.update(delta);
		level.update(delta);
	}
	
	public void render(Graphics g) {
		level.render(g);
	}
	
}
