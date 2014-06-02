package ca.ajweeks.rpg;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.state.GameState;
import ca.ajweeks.rpg.state.StateManager;

public class RenderDebugOverlay {
	
	public static void render(Graphics g) {
		fps(g, 2, 2);
		ups(g, 2, 17);
		currentState(g, 2, 32);
		if (RPG.sm.getCurrentStateIndex() == StateManager.GAME_STATE) {
			onGround(g, 2, 47);
			xaya(g, 2, 62);
			xy(g, 2, 92);
		}
	}
	
	private static void ups(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, 52, 15);
		
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString(RPG.ups + " UPS", x + 3, y + 11);
	}
	
	private static void fps(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, 50, 15);
		
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString(RPG.fps + " FPS", x + 3, y + 11);
	}
	
	private static void currentState(Graphics g, int x, int y) {
		String msg = "Current state: " + RPG.sm.getCurrentStateSimpleName();
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, (int) (msg.length() * 6.5), 15);
		
		g.setColor(Color.WHITE);
		g.drawString(msg, x + 3, y + 11);
	}
	
	private static void onGround(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, 95, 15);
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString("onGround: " + GameState.player.onGround, x + 3, y + 11);
	}
	
	private static void xy(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, GameState.player.x > 1000 ? 53 : GameState.player.x < 100 ? 39 : 45, 15);
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString("x = " + GameState.player.x, x + 3, y + 11);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, GameState.player.y <= -1000 ? 53 : GameState.player.y > 100 ? 45 : 39, 15);
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString("y = " + GameState.player.y, x + 3, y + 25);
	}
	
	private static void xaya(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, 45, 15);
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString("xa = " + GameState.player.xa, x + 3, y + 11);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, 45, 15);
		g.setFont(RPG.debugFont);
		g.setColor(Color.WHITE);
		g.drawString("ya = " + GameState.player.ya, x + 3, y + 25);
	}
	
}
