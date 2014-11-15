package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.graphics.Camera;
import ca.ajweeks.igmc2014.graphics.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.level.Levels;

public class GameState extends BasicState {
	
	public static Camera camera;
	
	public Level level;
	public Player player;
	
	private Level currentLevel;
	
	public GameState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		player = new Player(game, this);
		
		currentLevel = new Level(player, game, Levels.lvlOneChunks);
		
		camera = new Camera(player, currentLevel);
	}
	
	@Override
	public void update(double delta) {
		Input input = game.getInput();
		
		//TODO add mouse hovering debugging
		
		if (input.esc.clicked) game.enterState(StateManager.MAINMENU_STATE_ID);
		
		camera.update();
		currentLevel.update(delta);
	}
	
	@Override
	public void render(Graphics g) {
		currentLevel.render(g);
		
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
