package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.graphics.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Keyboard.Key;

public class AboutState extends BasicState {
	
	private Button back;
	
	public AboutState(Game game) {
		super(game);
	}
	
	@Override
	public void init() {
		back = new Button(Game.SIZE.width / 2 - 110 / 2, Game.SIZE.height - 120, 110, 75, "Back");
		back.setSelected(true);
	}
	
	@Override
	public void update(double delta) {
		if (back.isClicked() || Key.ESC.clicked || Key.SPACE.clicked || Key.ENTER.clicked) {
			game.enterState(StateManager.MAINMENU_STATE_ID);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setColor(new Color(240, 240, 240, 200));
		g.setFont(Game.font34);
		g.drawString("About", (int) ((Game.SIZE.width / 2) - (g.getFontMetrics().getStringBounds("About", g).getWidth() / 2)), 80);
		
		g.setFont(Game.font24);
		g.setColor(Color.white);
		String[] message = new String[] { Game.GAME_TITLE + " is a 2D platformer game made by AJ Weeks in June 2014",
				"originally for the Indie Game Maker Contest 2014. (But not finished on time)" };
		for (int i = 0; i < message.length; i++) {
			Rectangle2D bounds = g.getFontMetrics().getStringBounds(message[i], g);
			int xoff = (int) (0);
			int yoff = (int) (0) + 300;
			g.drawString(message[i], (Game.SIZE.width / 2) + xoff - (int) (bounds.getWidth() / 2),
					yoff + i * (int) bounds.getHeight());
		}
		
		back.render(g);
		
		if (Game.renderDebug) RenderDebugOverlay.render(g);
	}
	
	@Override
	public int getID() {
		return StateManager.ABOUT_STATE_ID;
	}
}
