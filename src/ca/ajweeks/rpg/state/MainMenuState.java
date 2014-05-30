package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.input.Input;

public class MainMenuState extends BasicState {
	
	private Colour colour;
	private Button startGame;
	
	public MainMenuState() {
		colour = new Colour();
		startGame = new Button(RPG.SIZE.width / 2 - 160 / 2, 225, 160, 75, "Play!", colour.button, colour.hButton, Color.WHITE);
	}
	
	public void update(Input input, StateManager sm) {
		if (startGame.update(input)) sm.enterState(StateManager.GAME_STATE);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(15, 15, 15));
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		startGame.render(g);
	}
	
}
