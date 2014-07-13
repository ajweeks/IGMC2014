package ca.ajweeks.igmc2014.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.gfx.Camera;
import ca.ajweeks.igmc2014.gfx.RenderDebugOverlay;
import ca.ajweeks.igmc2014.level.Level;

public class GameState extends BasicGameState {
	
	public Level level;
	public static Player player;
	public static Camera camera;
	
	private boolean renderDebug = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		//TODO add game field
		player = new Player(this);
		level = new Level(player);
		camera = new Camera(player, level);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, org.newdawn.slick.Graphics g) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_F3)) renderDebug = !renderDebug;
		if (renderDebug) RenderDebugOverlay.render(g);
		
		level.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)) game.enterState(Game.MAINMENU_STATE);
		
		camera.update();
		level.update(gc, game, delta);
	}
	
	@Override
	public int getID() {
		return Game.GAME_STATE;
	}
	
}
