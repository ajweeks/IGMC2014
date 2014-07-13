package ca.ajweeks.igmc2014;

import java.awt.Dimension;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.state.AboutState;
import ca.ajweeks.igmc2014.state.GameState;
import ca.ajweeks.igmc2014.state.HelpState;
import ca.ajweeks.igmc2014.state.MainMenuState;

public class Game extends StateBasedGame {
	
	public static final int LOADING_STATE = 0;
	public static final int MAINMENU_STATE = 1;
	public static final int GAME_STATE = 2;
	public static final int HELP_STATE = 3;
	public static final int ABOUT_STATE = 4;
	
	public static final String GAME_TITLE = "IGMC 2014";
	public static final Dimension SIZE = new Dimension(1140, 640);
	
	public static UnicodeFont font24;
	public static UnicodeFont font34;
	public static UnicodeFont fontDebug;
	
	public Game() {
		super(GAME_TITLE);
	}
	
	public void stopGame() {
		getContainer().exit();
	}
	
	public String getCurrentStateSimpleName() {
		switch (getCurrentStateID()) {
		case MAINMENU_STATE:
			return "Main Menu State";
		case ABOUT_STATE:
			return "About State";
		case GAME_STATE:
			return "Game State";
		case HELP_STATE:
			return "Help State";
		default:
			return "Unknown State Name! " + getCurrentStateID();
		}
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer agc = new AppGameContainer(new Game());
		agc.setDisplayMode(SIZE.width, SIZE.height, false);
		agc.setTargetFrameRate(60);
		agc.setAlwaysRender(true);
		agc.setShowFPS(true);
		agc.start();
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new LoadingState(this));
		addState(new MainMenuState());
		addState(new GameState());
		addState(new HelpState());
		addState(new AboutState());
	}
}
