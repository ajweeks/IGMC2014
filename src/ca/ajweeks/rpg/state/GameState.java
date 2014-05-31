package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class GameState extends BasicState {
	
	Colour colour;
	
	public GameState() {
		colour = new Colour();
	}
	
	public void update() {
		if (RPG.input.esc.down) {
			RPG.input.esc.down = false;
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
	}
	
}
