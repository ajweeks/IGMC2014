package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.gfx.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.sound.Sound;

public class MainMenuState extends BasicState {
	
	private Image louder;
	private Image quieter;
	
	private static final int PLAY = 0;
	private static final int HELP = 1;
	private static final int ABOUT = 2;
	private static final int OPTIONS = 3;
	private static final int ACHIEVEMENTS = 4;
	private static final int QUIT = 5;
	private static final int QUIETER = 6;
	private static final int LOUDER = 7;
	
	private Button[] buttons;
	
	private int selectedButton = 0;
	
	public MainMenuState() {
		louder = new ImageIcon("res/louder.png").getImage();
		quieter = new ImageIcon("res/quieter.png").getImage();
		
		//TODO make all buttons same width, but have centered text
		buttons = new Button[] {
				new Button(Game.SIZE.width / 2 - 105 / 2, 75, 105, 75, "Play!", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(Game.SIZE.width / 2 - 92 / 2, 175, 92, 75, "Help", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(Game.SIZE.width / 2 - 120 / 2, 275, 120, 75, "About", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(Game.SIZE.width / 2 - 157 / 2, 375, 157, 75, "Options", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(Game.SIZE.width / 2 - 275 / 2, 475, 275, 75, "Achievements", Colour.button, Colour.hButton,
						Colour.offWhite),
				new Button(Game.SIZE.width / 2 - 86 / 2, 575, 86, 75, "Quit", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(Game.SIZE.width - 130, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, quieter),
				new Button(Game.SIZE.width - 70, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, louder) };
		
		updateSelectedButton();
	}
	
	public void update(double delta) {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].hover && buttons[i].enabled && !Input.mouseIsStill) selectedButton = i;
		}
		
		if (Game.input.up.clicked) {
			do {
				previousButton(); //previous enabled button
			} while (!buttons[selectedButton].enabled);
		}
		
		if (Game.input.tab.clicked || Game.input.down.clicked) {
			do {
				nextButton(); //previous enabled button
			} while (!buttons[selectedButton].enabled);
		}
		
		if (Game.input.enter.clicked || Game.input.space.clicked) {
			switch (selectedButton) {
			case PLAY:
				if (buttons[PLAY].enabled) enterState(StateManager.GAME_STATE);
				break;
			case HELP:
				if (buttons[HELP].enabled) enterState(StateManager.HELP);
				break;
			case ABOUT:
				if (buttons[ABOUT].enabled) enterState(StateManager.ABOUT);
				break;
			case OPTIONS:
				if (buttons[OPTIONS].enabled) enterState(StateManager.OPTIONS);
				break;
			case ACHIEVEMENTS:
				if (buttons[ACHIEVEMENTS].enabled) enterState(StateManager.ACHIEVEMENTS);
				break;
			case QUIT:
				if (buttons[QUIT].enabled) Game.stop();
				break;
			case QUIETER:
				if (buttons[QUIETER].enabled) quieter();
				break;
			case LOUDER:
				if (buttons[LOUDER].enabled) louder();
				break;
			}
		}
		
		if (buttons[PLAY].isDown()) enterState(StateManager.GAME_STATE);
		if (buttons[HELP].isDown()) enterState(StateManager.HELP);
		if (buttons[ABOUT].isDown()) enterState(StateManager.ABOUT);
		if (buttons[OPTIONS].isDown()) enterState(StateManager.OPTIONS);
		if (buttons[ACHIEVEMENTS].isDown()) enterState(StateManager.ACHIEVEMENTS);
		if (buttons[QUIT].isDown()) Game.stop();
		if (buttons[LOUDER].isDown()) louder();
		if (buttons[QUIETER].isDown()) quieter();
		
		updateSelectedButton();
	}
	
	private void updateSelectedButton() {
		for (int i = 0; i < buttons.length; i++) {
			if (!buttons[i].enabled) {
				buttons[i].setDeselected();
				continue;
			}
			if (i == selectedButton) buttons[i].setSelected();
			else buttons[i].setDeselected();
		}
	}
	
	private void nextButton() {
		if (selectedButton + 1 > buttons.length - 1) selectedButton = 0;
		else selectedButton += 1;
	}
	
	private void previousButton() {
		if (selectedButton - 1 < 0) selectedButton = buttons.length - 1;
		else selectedButton -= 1;
	}
	
	private void enterState(int state) {
		Sound.SELECT.play();
		Game.sm.enterState(state);
	}
	
	private void quieter() {
		Sound.volume = Sound.quieter(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MIN_VOLUME) {
			buttons[QUIETER].enabled = false;
			selectedButton = LOUDER;
		}
		if (Sound.volume < Sound.MAX_VOLUME) buttons[LOUDER].enabled = true;
	}
	
	private void louder() {
		Sound.volume = Sound.louder(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MAX_VOLUME) {
			buttons[LOUDER].enabled = false;
			selectedButton = QUIETER;
		}
		if (Sound.volume > Sound.MIN_VOLUME) buttons[QUIETER].enabled = true;
	}
	
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		for (Button b : buttons) {
			b.render(g);
		}
		
		g.setFont(Game.font34.deriveFont(20f));
		g.setColor(Colour.offWhite);
		int vol = (int) (((Sound.volume + 24) / 3) * 10);
		g.drawString("Volume: " + vol + "%", 1065, 22);
	}
	
}
