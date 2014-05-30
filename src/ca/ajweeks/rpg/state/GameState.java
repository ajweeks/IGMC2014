package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.input.Input;

public class GameState extends BasicState {
	
	Colour colour;
	
	public GameState() {
		colour = new Colour();
	}
	
	public void update(Input input, StateManager sm) {
		if (input.esc.down) sm.enterState(StateManager.MAIN_MENU_STATE);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
	}
	
}
