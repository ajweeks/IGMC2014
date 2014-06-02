package ca.ajweeks.rpg.state;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;
import ca.ajweeks.rpg.input.Input;

public class MainMenuState extends BasicState {
	
	private Image louder;
	private Image quieter;
	
	private static final int PLAY = 0;
	private static final int HELP = 1;
	private static final int CREDITS = 2;
	private static final int QUIT = 3;
	private static final int QUIETER = 4;
	private static final int LOUDER = 5;
	
	private Button[] buttons;
	
	private int selectedButton = 0;
	
	public MainMenuState() {
		louder = new ImageIcon("res/louder.png").getImage();
		quieter = new ImageIcon("res/quieter.png").getImage();
		
		//TODO make all buttons same width, but have centered text
		buttons = new Button[] { new Button(RPG.SIZE.width / 2 - 115 / 2, 125, 115, 75, "Play!", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width / 2 - 100 / 2, 225, 100, 75, "Help", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width / 2 - 155 / 2, 325, 155, 75, "Credits", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width / 2 - 100 / 2, 425, 100, 75, "Quit", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width - 130, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, quieter),
				new Button(RPG.SIZE.width - 70, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, louder) };
		
		updateSelectedButton();
	}
	
	public void update() {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].hover && buttons[i].enabled && !Input.mouseIsStill) selectedButton = i;
		}
		
		if (RPG.input.up.clicked) {
			do {
				previousButton(); //previous enabled button
			} while (!buttons[selectedButton].enabled);
		}
		
		if (RPG.input.tab.clicked || RPG.input.down.clicked) {
			
			do {
				nextButton(); //previous enabled button
			} while (!buttons[selectedButton].enabled);
		}
		
		if (RPG.input.enter.clicked) {
			switch (selectedButton) {
			case PLAY:
				if (buttons[PLAY].enabled) startGame();
				break;
			case HELP:
				if (buttons[HELP].enabled) help();
				break;
			case CREDITS:
				if (buttons[CREDITS].enabled) credits();
				break;
			case QUIT:
				if (buttons[QUIT].enabled) RPG.stop();
				break;
			case QUIETER:
				if (buttons[QUIETER].enabled) quieter();
				break;
			case LOUDER:
				if (buttons[LOUDER].enabled) louder();
				break;
			}
		}
		
		if (buttons[PLAY].isDown()) startGame();
		if (buttons[HELP].isDown()) help();
		if (buttons[CREDITS].isDown()) {
			credits();
		}
		if (buttons[QUIT].isDown()) RPG.stop();
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
	
	private void startGame() {
		Sound.SELECT.play();
		RPG.sm.enterState(StateManager.GAME_STATE);
	}
	
	private void help() {
		Sound.SELECT.play();
		RPG.sm.enterState(StateManager.HELP);
	}
	
	private void credits() {
		Sound.SELECT.play();
		RPG.sm.enterState(StateManager.CREDITS);
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
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		for (Button b : buttons) {
			b.render(g);
		}
		
		g.setFont(RPG.font.deriveFont(20f));
		g.setColor(Colour.offWhite);
		g.drawString("Volume: " + (((Sound.volume + 24) / 3) * 10), 1065, 22);
	}
	
}
