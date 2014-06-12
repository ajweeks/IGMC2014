package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

public abstract class BasicState {
	
	public abstract void update(double delta);
	
	public abstract void render(Graphics g);
	
}
