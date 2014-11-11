package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.sound.Sound;

public class StateManager {
	
	public static final int LOADING_STATE_ID = 0;
	public static final int MAINMENU_STATE_ID = 1;
	public static final int GAME_STATE_ID = 2;
	public static final int HELP_STATE_ID = 3;
	public static final int ABOUT_STATE_ID = 4;
	
	private ArrayList<BasicState> states;
	private BasicState currentState;
	
	public StateManager(Game game) {
		states = new ArrayList<>();
		states.add(new LoadingState());
		states.add(new MainMenuState());
		states.add(new GameState());
		states.add(new HelpState());
		states.add(new AboutState());
		
		currentState = states.get(LOADING_STATE_ID);
		init(game);
	}
	
	public void init(Game game) {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).init(game);
		}
	}
	
	public void update(double delta) {
		currentState.update(delta);
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
	
	public BasicState getCurrentState() {
		return currentState;
	}
	
	public String getCurrentStateSimpleName() {
		switch (getCurrentStateID()) {
		case MAINMENU_STATE_ID:
			return "Main Menu State";
		case ABOUT_STATE_ID:
			return "About State";
		case GAME_STATE_ID:
			return "Game State";
		case HELP_STATE_ID:
			return "Help State";
		default:
			return "Unknown state name! " + getCurrentStateID();
		}
	}
	
	public int getCurrentStateID() {
		return currentState.getID();
	}
	
	public void addState(BasicState state) {
		if (states.contains(state)) new IllegalArgumentException("State " + state + " has already been added!").printStackTrace();
		else states.add(state);
	}
	
	public void enterState(int ID, Game game) {
		Sound.SELECT.play();
		currentState = states.get(ID);
	}
	
}
