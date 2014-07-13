package ca.ajweeks.igmc2014.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.ArrowButton;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.input.Keyboard;
import ca.ajweeks.igmc2014.sound.Sound;

public class HelpState extends BasicGameState {
	
	private static final int MAX_PAGES = 3;
	private int page = 0;
	private int xoff = 0;
	private int dir = 0;
	private int scrollSpeed = 50;
	
	private Button back;
	private ArrowButton left;
	private ArrowButton right;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back");
		back.setSelected(true);
		
		left = new ArrowButton(20, 260, 0, 0, "", null, null, null, ArrowButton.LEFT);
		right = new ArrowButton(Game.SIZE.width - 20 - 55, 260, 0, 0, "", null, null, null, ArrowButton.RIGHT);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, org.newdawn.slick.Graphics g) throws SlickException {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setFont(Game.font24);
		g.setColor(Color.white);
		
		String[] message = new String[] { "Controls:", "", "-WASD or arrow keys to move.", "-Space to jump",
				"-Esc to exit to main menu", "-Shift to sprint" };
		int p = 0;
		drawMessage(message, g, p);
		
		message = new String[] { "2" };
		p = 1;
		drawMessage(message, g, p);
		
		message = new String[] { "3" };
		p = 2;
		drawMessage(message, g, p);
		
		for (int i = 0; i < MAX_PAGES; i++) {
			if (i == page) g.setColor(Color.lightGray);
			else g.setColor(Color.darkGray);
			g.fillOval(i * 36 + Game.SIZE.width / 2 - MAX_PAGES * 14, Game.SIZE.height - 35, 25, 25);
		}
		
		back.render(g);
		left.render(g);
		right.render(g);
	}
	
	private void drawMessage(String[] message, Graphics g, int p) {
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (p * Game.SIZE.width) + (Game.SIZE.width / 2) - (g.getFont().getWidth(message[i]) / 2)
					- xoff, Game.SIZE.height / 2 - 125 + i * (g.getFont().getLineHeight()));
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (Keyboard.isRightPressed(game)) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		} else if (Keyboard.isLeftPressed(game)) {
			if (page > 0) {
				Sound.SELECT.play();
				changePage(-1);
			}
		}
		
		int dest = page * Game.SIZE.width;
		xoff += dir * scrollSpeed + ((xoff - dest) / 10) * -1;
		
		if (dir == 1) {
			if (xoff >= page * Game.SIZE.width) {
				xoff = page * Game.SIZE.width;
				dir = 0;
			}
		} else if (dir == -1) {
			if (xoff <= page * Game.SIZE.width) {
				xoff = page * Game.SIZE.width;
				dir = 0;
			}
		}
		
		if (back.isDown(input) || input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_SPACE)
				|| input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(Game.MAINMENU_STATE);
		}
		
		if (right.isDown(input)) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		}
		
		if (left.isDown(input)) {
			if (page > 0) {
				Sound.SELECT.play();
				changePage(-1);
			}
		}
		
		if (page > 0) left.setEnabled(true);
		else left.setEnabled(false);
		
		if (page < MAX_PAGES - 1) right.setEnabled(true);
		else right.setEnabled(false);
	}
	
	private void changePage(int dir) {
		page += dir;
		if (page < 0) page = 0;
		else if (page > MAX_PAGES - 1) page = MAX_PAGES - 1;
		else this.dir = dir;
	}
	
	@Override
	public int getID() {
		return Game.HELP_STATE;
	}
	
}
