package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Button;
import ca.ajweeks.igmc2014.Colour;
import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.Sound;

public class HelpState extends BasicState {
	
	private int page = 0;
	private static final int MAX_PAGES = 3;
	private Button back;
	
	public HelpState() {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton, Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (Game.input.right.clicked) {
			page++;
			if (page >= MAX_PAGES) page = 0;
		} else if (Game.input.left.clicked) {
			page--;
			if (page < 0) page = MAX_PAGES - 1;
		}
		
		if (back.isDown() || Game.input.esc.clicked || Game.input.enter.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		String[] message;
		
		switch (page) {
		case 0:
			g.setFont(Game.font.deriveFont(24f));
			g.setColor(Color.WHITE);
			message = new String[] { "One!" };
			for (int i = 0; i < message.length; i++) {
				g.drawString(message[i], Game.SIZE.width / 2 - 510, Game.SIZE.height / 2 - 200 + i * 45);
			}
			break;
		case 1:
			g.setFont(Game.font.deriveFont(24f));
			g.setColor(Color.WHITE);
			message = new String[] { "Two!" };
			for (int i = 0; i < message.length; i++) {
				g.drawString(message[i], Game.SIZE.width / 2 - 510, Game.SIZE.height / 2 - 200 + i * 45);
			}
			break;
		case 2:
			g.setFont(Game.font.deriveFont(24f));
			g.setColor(Color.WHITE);
			message = new String[] { "Three!" };
			for (int i = 0; i < message.length; i++) {
				g.drawString(message[i], Game.SIZE.width / 2 - 510, Game.SIZE.height / 2 - 200 + i * 45);
			}
			break;
		}
		
		for (int i = 0; i < MAX_PAGES; i++) {
			if (i == page) g.setColor(Color.LIGHT_GRAY);
			else g.setColor(Color.DARK_GRAY);
			g.fillOval(i * 35 + Game.SIZE.width / 2 - MAX_PAGES * 16, Game.SIZE.height - 35, 25, 25);
		}
		
		back.render(g);
	}
	
}
