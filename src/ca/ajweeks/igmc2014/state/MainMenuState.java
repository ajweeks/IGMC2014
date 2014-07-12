package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.button.ButtonManager;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class MainMenuState extends BasicState {
	
	private Image louder;
	private Image quieter;
	
	private static final int PLAY = 0;
	private static final int HELP = 1;
	private static final int ABOUT = 2;
	private static final int QUIT = 3;
	private static final int QUIETER = 4;
	private static final int LOUDER = 5;
	
	private ButtonManager buttons;
	
	public MainMenuState() {
		louder = new ImageIcon("res/louder.png").getImage();
		quieter = new ImageIcon("res/quieter.png").getImage();
		
		int spacing = 100;
		int yoff = 25;
		buttons = new ButtonManager();
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing, 250, 75, "Play!"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 2, 250, 75, "Help"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 3, 250, 75, "About"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 4, 250, 75, "Quit"));
		buttons.addButton(new Button(Game.SIZE.width - 130, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite,
				quieter));
		buttons.addButton(new Button(Game.SIZE.width - 70, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, louder));
	}
	
	public void update(double delta) {
		buttons.update();
		
		if (Game.input.up.clicked || Game.input.left.clicked) {
			do {
				buttons.previousButton(); //set selected buttons to previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (Game.input.tab.clicked || Game.input.down.clicked || Game.input.right.clicked) {
			do {
				buttons.nextButton(); //previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (Game.input.enter.clicked || Game.input.space.clicked) {
			switch (buttons.getSelectedButton()) {
			case PLAY:
				if (buttons.getButton(PLAY).isEnabled()) enterState(StateManager.GAME_STATE);
				break;
			case HELP:
				if (buttons.getButton(HELP).isEnabled()) enterState(StateManager.HELP);
				break;
			case ABOUT:
				if (buttons.getButton(ABOUT).isEnabled()) enterState(StateManager.ABOUT);
				break;
			case QUIT:
				if (buttons.getButton(QUIT).isEnabled()) Game.stop();
				break;
			case QUIETER:
				if (buttons.getButton(QUIETER).isEnabled()) quieter();
				break;
			case LOUDER:
				if (buttons.getButton(LOUDER).isEnabled()) louder();
				break;
			}
		}
		
		if (buttons.getButton(PLAY).isDown()) enterState(StateManager.GAME_STATE);
		if (buttons.getButton(HELP).isDown()) enterState(StateManager.HELP);
		if (buttons.getButton(ABOUT).isDown()) enterState(StateManager.ABOUT);
		if (buttons.getButton(QUIT).isDown()) Game.stop();
		if (buttons.getButton(LOUDER).isDown()) louder();
		if (buttons.getButton(QUIETER).isDown()) quieter();
		
		buttons.updateSelectedButton();
	}
	
	private void enterState(int state) {
		Game.sm.enterState(state);
	}
	
	private void quieter() {
		Sound.volume = Sound.quieter(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MIN_VOLUME) {
			buttons.getButton(QUIETER).setEnabled(false);
			buttons.setSelectedButton(LOUDER);
		}
		if (Sound.volume < Sound.MAX_VOLUME) buttons.getButton(LOUDER).setEnabled(true);
	}
	
	private void louder() {
		Sound.volume = Sound.louder(Sound.volume);
		Sound.SELECT.play();
		if (Sound.volume == Sound.MAX_VOLUME) {
			buttons.getButton(LOUDER).setEnabled(false);
			buttons.setSelectedButton(QUIETER);
		}
		if (Sound.volume > Sound.MIN_VOLUME) buttons.getButton(QUIETER).setEnabled(true);
	}
	
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		buttons.render(g);
		
		g.setFont(Game.font34.deriveFont(20f));
		g.setColor(Colour.offWhite);
		int vol = (int) (((Sound.volume + 24) / 3) * 10);
		g.drawString("Volume: " + vol + "%", 1005, 22);
	}
}
