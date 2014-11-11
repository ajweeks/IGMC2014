package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;

public abstract class BasicState {
	
	public abstract void init(Game game);
	
	/** @param delta - the amount of time in ns that have passed since last update */
	public abstract void update(double delta);
	
	public abstract void render(Graphics g);
	
	public abstract int getID();
	
}
