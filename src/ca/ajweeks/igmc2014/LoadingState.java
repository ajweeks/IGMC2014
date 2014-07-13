package ca.ajweeks.igmc2014;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.gfx.RenderDebugOverlay;
import ca.ajweeks.igmc2014.gfx.SimpleFont;

public class LoadingState extends BasicGameState {
	
	Game game;
	
	public LoadingState(Game game) {
		this.game = game;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		new RenderDebugOverlay(this.game);
		new Colour();
		
		SimpleFont font24 = new SimpleFont(new Font("Consolas", Font.BOLD, 24));
		Game.font24 = font24.get();
		
		SimpleFont font34 = new SimpleFont(new Font("Consolas", Font.BOLD, 34));
		Game.font34 = font34.get();
		
		SimpleFont fontDebug = new SimpleFont(new Font("Consolas", Font.BOLD, 18));
		Game.fontDebug = fontDebug.get();
		
		try {
			game.getContainer().setIcons(
					new String[] { "res/icon512.png", "res/icon256.png", "res/icon128.png", "res/icon32.png" });
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		game.enterState(Game.MAINMENU_STATE);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("LOADING!", Game.SIZE.width / 2 - g.getFont().getWidth("LOADING!") / 2, 300);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	@Override
	public int getID() {
		return Game.LOADING_STATE;
	}
	
}
