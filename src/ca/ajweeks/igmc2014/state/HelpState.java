package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Button;
import ca.ajweeks.igmc2014.Colour;
import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.Sound;

public class HelpState extends BasicState {
	
	private static final int MAX_PAGES = 3;
	private int page = 0;
	private int xoff = 0;
	private int dir = 0;
	private Button back;
	
	public HelpState() {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton,
				Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (Game.input.lM.clicked) {
			if (Game.input.y >= 640 && Game.input.y <= 665) {
				
			}
		}
		if (Game.input.right.clicked) {
			if (dir == 0) {
				page++;
				if (page > MAX_PAGES - 1) page = MAX_PAGES - 1;
				else dir = 1;
			}
		} else if (Game.input.left.clicked) {
			if (dir == 0) {
				page--;
				if (page < 0) page = 0;
				else dir = -1;
			}
		}
		
		xoff += dir * 50;
		if (xoff % Game.SIZE.width == 0) dir = 0;
		
		if (back.isDown() || Game.input.esc.clicked || Game.input.enter.clicked) { //Change if we ever put add more buttons!
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		String[] message;
		
		g.setFont(Game.font24);
		g.setColor(Color.WHITE);
		
		//One
		message = new String[] { "1" };
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
	}
	
}
