package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;

public class OptionsState extends BasicState {
	
	/**
	 * 
	 *  different types of options:
	 *  ** KEY MAPPING **
	 *  VOLUME? (Sounds / LATER MUSIC)
	 *  RESET LEVEL DATA?
	 * 
	 */
	
	
	public OptionsState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void update(double delta) {
		
	}
	
	@Override
	public void render(Graphics g) {
		
	}
	
	@Override
	public int getID() {
		return StateManager.OPTOINS_STATE_ID;
	}
	
}
