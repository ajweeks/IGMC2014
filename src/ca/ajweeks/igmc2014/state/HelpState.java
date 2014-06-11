package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.gfx.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class HelpState extends BasicState {
	
	private static final int MAX_PAGES = 3;
	private int page = 0;
	private int xoff = 0;
	private int dir = 0;
	private int scrollSpeed = 50;
	private Button back;
	
	public ArrowButton left;
	public ArrowButton right;
	
	public HelpState() {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton,
				Colour.offWhite);
		back.setSelected();
		
		left = new ArrowButton(20, 260, 0, 0, "", null, null, null, ArrowButton.LEFT);
		right = new ArrowButton(Game.SIZE.width - 20 - 55, 260, 0, 0, "", null, null, null, ArrowButton.RIGHT);
	}
	
	@Override
	public void update() {
		if (Game.input.right.clicked) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		} else if (Game.input.left.clicked) {
			if (page > 0) {
				Sound.SELECT.play();
				changePage(-1);
			}
		}
		
		xoff += dir * scrollSpeed;
		
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
		
		if (back.isDown() || Game.input.esc.clicked || Game.input.space.clicked || Game.input.enter.clicked) { //Change if we ever put add more buttons!
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (right.isDown()) {
			if (page < MAX_PAGES - 1) {
				Sound.SELECT.play();
				changePage(1);
			}
		}
		
		if (left.isDown()) {
			if (page > 0) {
				Sound.SELECT.play();
				changePage(-1);
			}
		}
	}
	
	private void changePage(int dir) {
		page += dir;
		if (page < 0) page = 0;
		else if (page > MAX_PAGES - 1) page = MAX_PAGES - 1;
		else this.dir = dir;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		String[] message;
		
		g.setFont(Game.font24);
		g.setColor(Color.WHITE);
		
		//One
		message = new String[] { "Controls:", "", "-WASD or arrow keys to move.", "-Space to jump", "-Esc to exit to main menu",
				"-Shift to sprint" };
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (Game.SIZE.width / 2) - (g.getFontMetrics().stringWidth(message[i]) / 2) - xoff,
					Game.SIZE.height / 2 - 200 + i * (g.getFontMetrics().getHeight()));
		}
		
		//Two
		message = new String[] { "2" };
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (1 * Game.SIZE.width) + (Game.SIZE.width / 2)
					- (g.getFontMetrics().stringWidth(message[i]) / 2) - xoff,
					Game.SIZE.height / 2 - 200 + i * (g.getFontMetrics().getHeight()));
		}
		
		//Three
		message = new String[] { "3" };
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (2 * Game.SIZE.width) + (Game.SIZE.width / 2)
					- (g.getFontMetrics().stringWidth(message[i]) / 2) - xoff,
					Game.SIZE.height / 2 - 200 + i * (g.getFontMetrics().getHeight()));
		}
		
		for (int i = 0; i < MAX_PAGES; i++) {
			if (i == page) g.setColor(Color.LIGHT_GRAY);
			else g.setColor(Color.DARK_GRAY);
			g.fillOval(i * 35 + Game.SIZE.width / 2 - MAX_PAGES * 16, Game.SIZE.height - 35, 25, 25);
		}
		
		back.render(g);
		left.render(g);
		right.render(g);
	}
	
}
