package ca.ajweeks.igmc2014.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.state.BasicState;
import ca.ajweeks.igmc2014.state.GameState;
import ca.ajweeks.igmc2014.state.StateManager;

public class RenderDebugOverlay {
	
	private static Game game;
	private static int y, x;
	
	public RenderDebugOverlay(Game game) {
		RenderDebugOverlay.game = game;
	}
	
	public static void render(Graphics g) {
		g.setFont(Game.fontDebug);
		
		y = 15;
		x = 15;
		
		BasicState currentState = Game.sm.getCurrentState();
		
		drawString(g, Integer.toString(game.getFps()) + " FPS");
		drawString(g, "Current state: " + Game.sm.getCurrentStateSimpleName());
		if (Game.sm.getCurrentStateID() == StateManager.GAME_STATE_ID) {
			drawString(g, "hasDoubleJumped: " + ((GameState) currentState).getPlayer().hasDoubleJumped);
			drawString(g, "x = " + ((GameState) currentState).getPlayer().getX());
			drawString(g, "y = " + ((GameState) currentState).getPlayer().getY());
			drawString(g, "xv = " + ((GameState) currentState).getPlayer().getXv());
			drawString(g, "yv = " + ((GameState) currentState).getPlayer().getYv());
			drawString(g, "max xv = " + ((GameState) currentState).getPlayer().maxHorizontalVelocity);
		}
	}
	
	private static void drawString(Graphics g, String str) {
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(str, g);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x - 1, y - 14, (int) bounds.getWidth() + 8, (int) bounds.getHeight());
		
		g.setColor(Color.white);
		g.drawString(str, x + 3, y + 2);
		
		y += bounds.getHeight() + 3;
	}
}
