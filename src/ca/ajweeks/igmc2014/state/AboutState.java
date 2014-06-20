package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class AboutState extends BasicState {
	
	Button back;
	
	public AboutState() {
		back = new Button(Game.SIZE.width / 2 - 110 / 2, Game.SIZE.height - 120, 110, 75, "Back");
		back.setSelected();
	}
	
	@Override
	public void update(double delta) {
		if (back.isDown() || Game.input.esc.clicked || Game.input.space.clicked || Game.input.enter.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setColor(new Color(240, 240, 240, 200));
		g.setFont(Game.font34);
		g.drawString("About", (Game.SIZE.width / 2) - (g.getFontMetrics().stringWidth("About") / 2), 80);
		
		g.setFont(Game.font24);
		g.setColor(Color.WHITE);
		String[] message = new String[] { Game.GAME_TITLE + " is a 2D platformer game made by AJ Weeks in June 2014 for",
				"the Indie Game Maker Contest 2014." };
		for (int i = 0; i < message.length; i++) {
			int xoff = (int) (Game.input.x / 80);
			int yoff = (int) (Game.input.y / 100) + 300;
			g.drawString(message[i], (Game.SIZE.width / 2) + xoff - (g.getFontMetrics().stringWidth(message[i]) / 2), yoff + i
					* g.getFontMetrics().getHeight());
		}
		
		back.render(g);
	}
}
