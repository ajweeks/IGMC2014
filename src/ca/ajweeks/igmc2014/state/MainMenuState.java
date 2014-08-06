package ca.ajweeks.igmc2014.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.button.ButtonManager;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.gfx.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Keyboard;
import ca.ajweeks.igmc2014.sound.Sound;

public class MainMenuState extends BasicGameState {
	
	private static final int PLAY = 0;
	private static final int HELP = 1;
	private static final int ABOUT = 2;
	private static final int QUIT = 3;
	private static final int QUIETER = 4;
	private static final int LOUDER = 5;
	
	private Image louder;
	private Image quieter;
	private ButtonManager buttons;
	
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
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		try {
			louder = new Image("res/louder.png");
			quieter = new Image("res/quieter.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
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
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		Input input = gc.getInput();
		
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		buttons.render(g);
		
		g.setFont(Game.font24);
		g.setColor(Colour.offWhite);
		int vol = (int) (((Sound.volume + 24) / 3) * 10);
		g.drawString("Volume: " + vol + "%", Game.SIZE.width - 164, 4);
		
		if (input.isKeyPressed(Input.KEY_F3)) Game.renderDebug = !Game.renderDebug;
		if (Game.renderDebug) RenderDebugOverlay.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		
		buttons.update();
		
		if (Keyboard.isPreviousPressed(game)) {
			do {
				buttons.previousButton(); //set selected buttons to previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (Keyboard.isNextPressed(game)) {
			do {
				buttons.nextButton(); //previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).isEnabled());
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE)) {
			switch (buttons.getSelectedButton()) {
			case PLAY:
				if (buttons.getButton(PLAY).isEnabled()) enterState(Game.GAME_STATE_ID, game);
				break;
			case HELP:
				if (buttons.getButton(HELP).isEnabled()) enterState(Game.HELP_STATE_ID, game);
				break;
			case ABOUT:
				if (buttons.getButton(ABOUT).isEnabled()) enterState(Game.ABOUT_STATE_ID, game);
				break;
			case QUIT:
				if (buttons.getButton(QUIT).isEnabled()) ((Game) game).stopGame();
				break;
			case QUIETER:
				if (buttons.getButton(QUIETER).isEnabled()) quieter();
				break;
			case LOUDER:
				if (buttons.getButton(LOUDER).isEnabled()) louder();
				break;
			}
		}
		
		if (buttons.getButton(PLAY).isDown(input)) enterState(Game.GAME_STATE_ID, game);
		if (buttons.getButton(HELP).isDown(input)) enterState(Game.HELP_STATE_ID, game);
		if (buttons.getButton(ABOUT).isDown(input)) enterState(Game.ABOUT_STATE_ID, game);
		if (buttons.getButton(QUIT).isDown(input)) ((Game) game).stopGame();
		if (buttons.getButton(LOUDER).isDown(input)) louder();
		if (buttons.getButton(QUIETER).isDown(input)) quieter();
		
		buttons.updateSelectedButton();
	}
	
	private void enterState(int ID, StateBasedGame game) {
		Sound.SELECT.play();
		game.enterState(ID);
	}
	
	@Override
	public int getID() {
		return Game.MAINMENU_STATE_ID;
	}
}
