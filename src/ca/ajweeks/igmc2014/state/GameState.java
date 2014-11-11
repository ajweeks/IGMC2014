package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.graphics.Camera;
import ca.ajweeks.igmc2014.graphics.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.level.Level;

public class GameState extends BasicState {
	
	public static Camera camera;
	
	public Level level;
	public Player player;
	
	private Game game;
	private Level[] levels;
	
	public void enterLevel(int level) {
		if (level > levels.length - 1 || level < 1) {
			System.err.println("Invalid level!" + level);
			return;
		}
		this.level = levels[level];
	}
	
	@Override
	public void init(Game game) {
		this.game = game;
		
		player = new Player(game, this);
		
		levels = new Level[] { new Level(player, game, 1) };
		level = levels[0];
		
		camera = new Camera(player, level);
	}
	
	@Override
	public void update(double delta) {
		Input input = game.getInput();
		
		//TODO add mouse hovering debugging
		
		if (input.esc.clicked) game.enterState(StateManager.MAINMENU_STATE_ID);
		if (input.F3.clicked) Game.renderDebug = !Game.renderDebug;
		
		camera.update();
		level.update(delta);
	}
	
	@Override
	public void render(Graphics g) {
		level.render(g);
		
		if (Game.renderDebug) RenderDebugOverlay.render(g);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public int getID() {
		return StateManager.GAME_STATE_ID;
	}
	
}
