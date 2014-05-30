package ca.ajweeks.rpg.state;

import java.awt.Graphics;

import ca.ajweeks.rpg.input.Input;

public abstract class BasicState {
	
	public abstract void update(Input input, StateManager sm);
	
	public abstract void render(Graphics g);
	
}
