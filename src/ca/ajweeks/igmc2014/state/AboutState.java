package ca.ajweeks.igmc2014.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.gfx.RenderDebugOverlay;

public class AboutState extends BasicGameState {
	
	private Button back;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		back = new Button(Game.SIZE.width / 2 - 110 / 2, Game.SIZE.height - 120, 110, 75, "Back");
		back.setSelected(true);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, org.newdawn.slick.Graphics g) throws SlickException {
		Input input = gc.getInput();
		
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		g.setColor(new Color(240, 240, 240, 200));
		g.setFont(Game.font34);
		g.drawString("About", (Game.SIZE.width / 2) - (g.getFont().getWidth("About") / 2), 80);
		
		g.setFont(Game.font24);
		g.setColor(Color.white);
		String[] message = new String[] { Game.GAME_TITLE + " is a 2D platformer game made by AJ Weeks in June 2014",
				"originally for the Indie Game Maker Contest 2014. (But not finished on time)" };
		for (int i = 0; i < message.length; i++) {
			int xoff = (int) (input.getMouseX() / 80);
			int yoff = (int) (input.getMouseY() / 100) + 300;
			g.drawString(message[i], (Game.SIZE.width / 2) + xoff - (g.getFont().getWidth(message[i]) / 2), yoff + i
					* g.getFont().getHeight(message[i]));
		}
		
		back.render(g);
		
		if (Game.renderDebug) RenderDebugOverlay.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_F3)) Game.renderDebug = !Game.renderDebug;
		if (back.isDown(input) || input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_SPACE)
				|| input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(Game.MAINMENU_STATE_ID);
		}
	}
	
	@Override
	public int getID() {
		return Game.ABOUT_STATE_ID;
	}
}
