package ca.ajweeks.igmc2014;

import java.awt.Dimension;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.state.GameState;
import ca.ajweeks.igmc2014.state.AboutState;
import ca.ajweeks.igmc2014.state.HelpState;
import ca.ajweeks.igmc2014.state.LoadingState;
import ca.ajweeks.igmc2014.state.MainMenuState;

public class Game extends StateBasedGame {
	
	public static final int LOADING_STATE_ID = 0;
	public static final int MAINMENU_STATE_ID = 1;
	public static final int GAME_STATE_ID = 2;
	public static final int HELP_STATE_ID = 3;
	public static final int ABOUT_STATE_ID = 4;
	
	public static final String GAME_TITLE = "IGMC 2014";
	public static final Dimension SIZE = new Dimension(1140, 640);
	
	public static UnicodeFont font24;
	public static UnicodeFont font34;
	public static UnicodeFont fontDebug;
	
	public static boolean renderDebug = true; //TODO MAKE FALSE FOR RELEASES
	
	public Game() {
		super(GAME_TITLE);
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
	
	public void stopGame() {
		getContainer().exit();
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer agc = new AppGameContainer(new Game());
		agc.setDisplayMode(SIZE.width, SIZE.height, false);
		agc.setTargetFrameRate(60);
		agc.setAlwaysRender(true);
		agc.setVerbose(false);
		agc.setShowFPS(false);
		agc.start();
		
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new LoadingState());
		addState(new MainMenuState());
		addState(new GameState());
		addState(new HelpState());
		addState(new AboutState());
	}
}
