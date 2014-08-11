package ca.ajweeks.igmc2014.state;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.gfx.SimpleFont;

public class LoadingState extends BasicGameState {
	//TODO make things actually load while displaying anim
	
	private final int DELAY = 10; //# of ticks to show splash screen for
	private int ticks; //# of ticks that have gone by
	
	private SpriteSheet loadingImages;
	public Client bugsnag;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
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
		
		loadingImages = new SpriteSheet(new Image("res/tri_load2.png"), 256, 256);
		
		bugsnag = new Client("48e3de13919177cfc5163bda66823970");
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int ticksPerFrame = DELAY / (9);
		int index = Math.max(0, Math.min(9, ticks / ticksPerFrame));
		
		int index_x = index % loadingImages.getHorizontalCount();
		int index_y = index / loadingImages.getVerticalCount();
		
		loadingImages.getSprite(index_x, index_y).draw(Game.SIZE.width / 2 - loadingImages.getSprite(0, 0).getWidth() / 2,
				Game.SIZE.height / 2 - loadingImages.getSprite(0, 0).getHeight() / 2);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		bugsnag.notify(new RuntimeException("Non-fatal"));
		if (ticks++ >= DELAY) game.enterState(Game.MAINMENU_STATE_ID);
	}
	
	@Override
	public int getID() {
		return Game.LOADING_STATE_ID;
	}
	
}
