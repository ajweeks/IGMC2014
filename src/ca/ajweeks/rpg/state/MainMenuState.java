package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class MainMenuState extends BasicState {
	
	private Image up;
	private Image down;
	
	private static final int START_GAME = 0;
	private static final int HELP = 1;
	private static final int QUIT = 2;
	private static final int QUIETER = 3;
	private static final int LOUDER = 4;
	
	private Button[] buttons;
	
	private int selectedButton = 0;
	
	public MainMenuState() {
		up = new ImageIcon("res/up.png").getImage();
		down = new ImageIcon("res/down.png").getImage();
		
		buttons = new Button[] { new Button(RPG.SIZE.width / 2 - 160 / 2, 225, 160, 75, "Play!", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width / 2 - 160 / 2, 325, 160, 75, "Help", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width / 2 - 160 / 2, 425, 160, 75, "Quit", Colour.button, Colour.hButton, Colour.offWhite),
				new Button(RPG.SIZE.width - 130, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, down),
				new Button(RPG.SIZE.width - 70, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, up) };
		
		for (int i = 0; i < buttons.length; i++) {
			if (i == selectedButton) buttons[i].setSelected();
			else buttons[i].setDeselected();
		}
	}
	
	public void update() {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].hover) selectedButton = i;
			for (int j = 0; j < buttons.length; j++) {
				if (j == selectedButton) buttons[j].setSelected();
				else buttons[j].setDeselected();
			}
		}
		
		if (RPG.input.tab) {
			RPG.input.tab = false;
			selectedButton++;
			if (selectedButton > buttons.length - 1) selectedButton = 0;
			for (int i = 0; i < buttons.length; i++) {
				if (i == selectedButton) buttons[i].setSelected();
				else buttons[i].setDeselected();
			}
		}
		
		if (RPG.input.enter) {
			RPG.input.enter = false;
			switch (selectedButton) {
			case START_GAME:
				if (buttons[START_GAME].enabled) startGame();
				break;
			case HELP:
				if (buttons[HELP].enabled) help();
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
		
		if (buttons[START_GAME].isDown(RPG.input)) startGame();
		if (buttons[HELP].isDown(RPG.input)) help();
		if (buttons[QUIT].isDown(RPG.input)) RPG.stop();
		if (buttons[LOUDER].isDown(RPG.input)) louder();
		if (buttons[QUIETER].isDown(RPG.input)) quieter();
	}
	
	private void startGame() {
		Sound.SELECT.play();
		RPG.sm.enterState(StateManager.GAME_STATE);
	}
	
	private void help() {
		
	}
	
	private void quieter() {
		Sound.volume = Sound.quieter(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MIN_VOLUME) buttons[QUIETER].enabled = false;
		if (Sound.volume < Sound.MAX_VOLUME) buttons[LOUDER].enabled = true;
	}
	
	private void louder() {
		Sound.volume = Sound.louder(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MAX_VOLUME) buttons[LOUDER].enabled = false;
		if (Sound.volume > Sound.MIN_VOLUME) buttons[QUIETER].enabled = true;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(15, 15, 15));
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		for (Button b : buttons) {
			b.render(g);
		}
	}
	
}
