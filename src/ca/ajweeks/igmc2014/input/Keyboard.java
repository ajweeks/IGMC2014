package ca.ajweeks.igmc2014.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Keyboard {
	
	/** @return true if tab, s, down, d, or right are pressed */
	public static boolean isPreviousPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if ((input.isKeyPressed(Input.KEY_TAB) && (input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))))
			return true;
		if (Keyboard.isUpPressed(game)) return true;
		if (Keyboard.isLeftPressed(game)) return true;
		return false;
	}
	
	/** @return true if tab, s, down, d, or right are pressed */
	public static boolean isNextPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyPressed(Input.KEY_TAB)) return true;
		if (Keyboard.isDownPressed(game)) return true;
		if (Keyboard.isRightPressed(game)) return true;
		return false;
	}
	
	public static boolean isRightPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyPressed(Input.KEY_D)) return true;
		if (input.isKeyPressed(Input.KEY_RIGHT)) return true;
		return false;
	}
	
	public static boolean isLeftPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyPressed(Input.KEY_A)) return true;
		if (input.isKeyPressed(Input.KEY_LEFT)) return true;
		return false;
	}
	
	public static boolean isUpPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyPressed(Input.KEY_W)) return true;
		if (input.isKeyPressed(Input.KEY_UP)) return true;
		return false;
	}
	
	public static boolean isDownPressed(StateBasedGame game) {
		Input input = game.getContainer().getInput();;
		if (input.isKeyPressed(Input.KEY_S)) return true;
		if (input.isKeyPressed(Input.KEY_DOWN)) return true;
		return false;
	}
	
	public static boolean isRightDown(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyDown(Input.KEY_D)) return true;
		if (input.isKeyDown(Input.KEY_RIGHT)) return true;
		return false;
	}
	
	public static boolean isLeftDown(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyDown(Input.KEY_A)) return true;
		if (input.isKeyDown(Input.KEY_LEFT)) return true;
		return false;
	}
	
	public static boolean isUpDown(StateBasedGame game) {
		Input input = game.getContainer().getInput();
		if (input.isKeyDown(Input.KEY_W)) return true;
		if (input.isKeyDown(Input.KEY_UP)) return true;
		return false;
	}
	
	public static boolean isDownDown(StateBasedGame game) {
		Input input = game.getContainer().getInput();;
		if (input.isKeyDown(Input.KEY_S)) return true;
		if (input.isKeyDown(Input.KEY_DOWN)) return true;
		return false;
	}
}
