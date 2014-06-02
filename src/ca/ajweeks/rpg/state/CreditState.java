package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class CreditState extends BasicState {
	
	Button back;
	
	public CreditState() {
		back = new Button(RPG.SIZE.width / 2 - 110 / 2, RPG.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton, Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (back.isDown() || RPG.input.esc.clicked || RPG.input.enter.clicked) {
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		g.setFont(RPG.font.deriveFont(24f));
		g.setColor(Color.WHITE);
		String[] message = new String[] { RPG.GAME_TITLE + " is a 2D (insert game genre) game made by AJ Weeks in June 2014 for",
				"the Indie Game Maker Contest 2014." };
		//LATER Add scrolling credits??
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], RPG.SIZE.width / 2 - 510, RPG.SIZE.height / 2 - 200 + i * 45);
		}
		
		back.render(g);
	}
}
