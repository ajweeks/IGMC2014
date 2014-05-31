package ca.ajweeks.rpg.state;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.rpg.input.Input;

public class StateManager {
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	
	private ArrayList<BasicState> states;
	private BasicState currentState;
	
	public StateManager() {
		states = new ArrayList<>();
		states.add(new MainMenuState());
		states.add(new GameState());
		
		currentState = states.get(MAIN_MENU_STATE);
	}
	
	public void enterState(int stateIndex) {
		if (stateIndex <= states.size()) {
			currentState = states.get(stateIndex);
		} else {
			new Exception("Invalid state index: " + stateIndex + "!").printStackTrace();
		}
	}
	
	public void update(Input input, StateManager sm) {
		currentState.update();
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
	
}
