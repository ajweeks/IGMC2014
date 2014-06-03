package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Button;
import ca.ajweeks.igmc2014.Colour;
import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.Sound;

public class CreditState extends BasicState {
	
	Button back;
	
	public CreditState() {
		back = new Button(Game.SIZE.width / 2 - 110 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton, Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (back.isDown() || Game.input.esc.clicked || Game.input.enter.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setFont(Game.font.deriveFont(24f));
		g.setColor(Color.WHITE);
		String[] message = new String[] { Game.GAME_TITLE + " is a 2D (insert game genre) game made by AJ Weeks in June 2014 for",
				"the Indie Game Maker Contest 2014." };
		//LATER Add scrolling credits??
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], Game.SIZE.width / 2 - 510, Game.SIZE.height / 2 - 200 + i * 45);
		}
		
		back.render(g);
	}
}
