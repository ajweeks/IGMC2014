package ca.ajweeks.rpg.state;

import java.awt.Graphics;
import java.util.ArrayList;

public class StateManager {
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int HELP = 1;
	public static final int GAME_STATE = 2;
	public static final int CREDITS = 3;
	
	private ArrayList<BasicState> states;
	private BasicState currentState;
	
	public StateManager() {
		states = new ArrayList<>();
		states.add(new MainMenuState());
		states.add(new HelpState(MAIN_MENU_STATE));
		states.add(new GameState());
		states.add(new CreditState());
		
		currentState = states.get(MAIN_MENU_STATE);
	}
	
	public void enterState(int stateIndex) {
		if (stateIndex <= states.size()) {
			if (stateIndex == HELP) states.set(HELP, new HelpState(states.indexOf(currentState)));
			currentState = states.get(stateIndex);
		} else {
			new Exception("Invalid state index: " + stateIndex + "!").printStackTrace();
		}
	}
	
	public String getCurrentState() {
		return currentState.getClass().getSimpleName();
	}
	
	public void update() {
		currentState.update();
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
	
}
