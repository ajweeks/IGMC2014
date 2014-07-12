package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.sound.Sound;

public class StateManager {
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int HELP = 1;
	public static final int GAME_STATE = 2;
	public static final int ABOUT = 3;
	
	private ArrayList<BasicState> states;
	private BasicState currentState;
	
	public StateManager() {
		states = new ArrayList<>();
		states.add(new MainMenuState());
		states.add(new HelpState());
		states.add(new GameState());
		states.add(new AboutState());
		
		currentState = states.get(MAIN_MENU_STATE);
	}
	
	public void enterState(BasicState state) {
		if (states.contains(state)) {
			currentState = state;
		} else {
			new Exception("Invalid state: " + state + "!").printStackTrace();
		}
	}
	
	public void enterState(int stateIndex) {
		if (stateIndex <= states.size()) {
			Sound.SELECT.play();
			currentState = states.get(stateIndex);
		} else {
			new Exception("Invalid state index: " + stateIndex + "!").printStackTrace();
		}
	}
	
	public int getCurrentStateIndex() {
		return states.indexOf(currentState);
	}
	
	public String getCurrentStateSimpleName() {
		return currentState.getClass().getSimpleName();
	}
	
	public void update(double delta) {
		currentState.update(delta);
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
	
}
