package ca.ajweeks.igmc2014.gfx;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;

public class RenderDebugOverlay {
	
	private static Game game;
	private static GameState gs;
	private static int y, x, height;
	
	public RenderDebugOverlay(Game game, GameState gs) {
		RenderDebugOverlay.gs = gs;
		RenderDebugOverlay.game = game;
	}
	
	public static void render(Graphics g) {
		g.setFont(Game.fontDebug);
		
		y = 3;
		x = 3;
		height = g.getFont().getLineHeight() + 2;
		
		drawString(g, Integer.toString(game.getContainer().getFPS()) + " FPS");
		drawString(g, "Current state: " + game.getCurrentStateSimpleName());
		if (game.getCurrentStateID() == Game.GAME_STATE_ID) {
			drawString(g, "hasDoubleJumped: " + gs.player.hasDoubleJumped);
			drawString(g, "x = " + gs.player.getX());
			drawString(g, "y = " + gs.player.getY());
			drawString(g, "xv = " + gs.player.getXv());
			drawString(g, "yv = " + gs.player.getYv());
			drawString(g, "max xv = " + Player.maxHorizontalVelocity);
		}
	}
	
	private static void drawString(Graphics g, String str) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth(str) + 5, height);
		
		g.setColor(Color.white);
		g.drawString(str, x + 3, y + 2);
		
		y += height;
	}
}
