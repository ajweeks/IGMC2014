package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.button.ButtonManager;
import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.graphics.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Keyboard.Key;
import ca.ajweeks.igmc2014.sound.Sound;

public class MainMenuState extends BasicState {
	
	private static final int PLAY = 0;
	private static final int HELP = 1;
	private static final int ABOUT = 2;
	private static final int QUIT = 3;
	private static final int QUIETER = 4;
	private static final int LOUDER = 5;
	
	private Image louder;
	private Image quieter;
	private ButtonManager buttons;
	
	public MainMenuState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		new RenderDebugOverlay(game);
		
		louder = new ImageIcon("res/louder.png").getImage();
		quieter = new ImageIcon("res/quieter.png").getImage();
		
		int spacing = 100;
		int yoff = 25;
		buttons = new ButtonManager();
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing, 250, 75, "Play!"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 2, 250, 75, "Help"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 3, 250, 75, "About"));
		buttons.addButton(new Button(Game.SIZE.width / 2 - 250 / 2, yoff + spacing * 4, 250, 75, "Quit"));
		buttons.addButton(new Button(Game.SIZE.width - 145, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite,
				quieter));
		buttons.addButton(new Button(Game.SIZE.width - 85, 30, 50, 50, "", Colour.button, Colour.hButton, Colour.offWhite, louder));
	}
	
	@Override
	public void update(double delta) {
		buttons.update();
		
		if (Key.LEFT_ARROW.clicked || Key.UP_ARROW.clicked) {
			do {
				buttons.previousButton(); //set selected buttons to previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (Key.RIGHT_ARROW.clicked || Key.DOWN_ARROW.clicked) {
			do {
				buttons.nextButton(); //previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (Key.ENTER.clicked || Key.SPACE.clicked) {
			switch (buttons.getSelectedButton()) {
			case PLAY:
				if (buttons.getButton(PLAY).isEnabled()) game.enterState(StateManager.GAME_STATE_ID);
				break;
			case HELP:
				if (buttons.getButton(HELP).isEnabled()) game.enterState(StateManager.HELP_STATE_ID);
				break;
			case ABOUT:
				if (buttons.getButton(ABOUT).isEnabled()) game.enterState(StateManager.ABOUT_STATE_ID);
				break;
			case QUIT:
				if (buttons.getButton(QUIT).isEnabled()) ((Game) game).stop();
				break;
			case QUIETER:
				if (buttons.getButton(QUIETER).isEnabled()) quieter();
				break;
			case LOUDER:
				if (buttons.getButton(LOUDER).isEnabled()) louder();
				break;
			}
		}
		
		if (buttons.getButton(PLAY).isClicked()) game.enterState(StateManager.GAME_STATE_ID);
		if (buttons.getButton(HELP).isClicked()) game.enterState(StateManager.HELP_STATE_ID);
		if (buttons.getButton(ABOUT).isClicked()) game.enterState(StateManager.ABOUT_STATE_ID);
		if (buttons.getButton(QUIT).isClicked()) game.stop();
		if (buttons.getButton(LOUDER).isClicked()) louder();
		if (buttons.getButton(QUIETER).isClicked()) quieter();
		
		buttons.updateSelectedButton();
	}
	
	@Override
	public void render(Graphics g) {
		buttons.render(g);
		
		g.setFont(Game.font24);
		g.setColor(Colour.offWhite);
		int vol = (int) (((Sound.volume + 24) / 3) * 10);
		g.drawString("Volume: " + vol + "%", Game.SIZE.width - 165, 22);
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
	
	@Override
	public int getID() {
		return StateManager.MAINMENU_STATE_ID;
	}
}
