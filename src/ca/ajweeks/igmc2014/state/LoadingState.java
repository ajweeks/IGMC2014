package ca.ajweeks.igmc2014.state;

import java.awt.Font;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Coin;
import ca.ajweeks.igmc2014.graphics.Colour;

public class LoadingState extends BasicState {
	//TODO make things actually load while displaying anim
	
	private final int DELAY = 10; //# of ticks to show splash screen for
	private int ticks = 0; //# of ticks that have gone by
	
	//LATER ADD loading images: private SpriteSheet loadingImages;
	
	public LoadingState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		new Colour(); //initialize colours
		new Coin(0, 0);
		
		Game.font24 = new Font("Consolas", Font.BOLD, 24);
		Game.font34 = new Font("Consolas", Font.BOLD, 34);
		Game.fontDebug = new Font("Consolas", Font.BOLD, 18);
	}
	
	@Override
	public void update(double delta) {
		if (ticks++ >= DELAY) game.enterState(StateManager.MAINMENU_STATE_ID);
		game.enterState(StateManager.MAINMENU_STATE_ID);
	}
	
	@Override
	public void render(Graphics g) {
		//		int ticksPerFrame = DELAY / (9);
		//		int index = Math.max(0, Math.min(9, ticks / ticksPerFrame));
	}
	
	@Override
	public int getID() {
		return StateManager.LOADING_STATE_ID;
	}
	
}
