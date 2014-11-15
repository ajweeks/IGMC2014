package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.ArrowButton;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.graphics.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Keyboard.Key;
import ca.ajweeks.igmc2014.sound.Sound;

public class HelpState extends BasicState {
	
	private static final int MAX_PAGES = 3;
	
	private int page = 0;
	private int xoff = 0;
	private int dir = 0;
	private int scrollSpeed = 50;
	
	private Button back;
	private ArrowButton left;
	private ArrowButton right;
	
	public HelpState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back");
		back.setSelected(true);
		
		left = new ArrowButton(20, 260, 0, 0, "", null, null, null, ArrowButton.LEFT);
		right = new ArrowButton(Game.SIZE.width - 20 - 55, 260, 0, 0, "", null, null, null, ArrowButton.RIGHT);
	}
	
	@Override
	public void update(double delta) {
		if (Key.RIGHT.clicked) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		} else if (Key.LEFT.clicked) {
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
		
		if (back.isClicked() || Key.ESC.clicked || Key.SPACE.clicked || Key.ENTER.clicked) {
			game.enterState(StateManager.MAINMENU_STATE_ID);
		}
		
		if (right.isClicked()) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		}
		
		if (left.isClicked()) {
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
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setFont(Game.font24);
		g.setColor(Color.white);
		
		String[][] messages = new String[][] {
				{ "Controls:", "", "-WASD or arrow keys to move.", "-Space to jump", "-Esc to exit to main menu",
						"-Shift to sprint" }, { "2" }, { "3" } };
		
		for (int p = 0; p < messages.length; p++) {
			for (int i = 0; i < messages[p].length; i++) {
				Rectangle2D b = g.getFontMetrics().getStringBounds(messages[p][i], g);
				g.drawString(messages[p][i], (int) ((p * Game.SIZE.width) + (Game.SIZE.width / 2) - (b.getWidth() / 2) - xoff),
						(int) (Game.SIZE.height / 2 - 125 + i * (b.getHeight())));
			}
		}
		
		for (int i = 0; i < MAX_PAGES; i++) {
			if (i == page) g.setColor(Color.lightGray);
			else g.setColor(Color.darkGray);
			g.fillOval(i * 36 + Game.SIZE.width / 2 - MAX_PAGES * 14, Game.SIZE.height - 35, 25, 25);
		}
		
		back.render(g);
		left.render(g);
		right.render(g);
		
		if (Game.renderDebug) RenderDebugOverlay.render(g);
	}
	
	private void changePage(int dir) {
		page += dir;
		if (page < 0) page = 0;
		else if (page > MAX_PAGES - 1) page = MAX_PAGES - 1;
		else this.dir = dir;
	}
	
	@Override
	public int getID() {
		return StateManager.HELP_STATE_ID;
	}
	
}
