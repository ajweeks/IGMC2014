package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class HelpState extends BasicState {
	
	private int page = 0;
	private static final int MAX_PAGES = 3;
	private Button back;
	
	public HelpState() {
		back = new Button(RPG.SIZE.width / 2 - 100 / 2, RPG.SIZE.height - 120, 100, 75, "Back", Colour.button, Colour.hButton, Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (RPG.input.right) {
			RPG.input.right = false;
			page++;
			if (page >= MAX_PAGES) page = 0;
		} else if (RPG.input.left) {
			RPG.input.left = false;
			page--;
			if (page < 0) page = MAX_PAGES - 1;
		}
		
		if (RPG.input.enter) {
			RPG.input.enter = false;
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (back.isDown() || RPG.input.esc) {
			RPG.input.esc = false;
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		switch (page) {
		case 0:
			g.setColor(Colour.offWhite);
			g.setFont(RPG.font.deriveFont(34f));
			g.drawString("", RPG.SIZE.width / 2 - 40, RPG.SIZE.height / 2);
			break;
		case 1:
			break;
		case 2:
			break;
		}
		
		for (int i = 0; i < MAX_PAGES; i++) {
			if (i == page) g.setColor(Color.LIGHT_GRAY);
			else g.setColor(Color.DARK_GRAY);
			g.fillOval(i * 35 + RPG.SIZE.width / 2 - MAX_PAGES * 16, RPG.SIZE.height - 35, 25, 25);
		}
		
		back.render(g);
	}
	
}
