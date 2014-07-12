package ca.ajweeks.igmc2014.gfx;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;
import ca.ajweeks.igmc2014.state.StateManager;

public class RenderDebugOverlay {
	
	public static void render(Graphics g) {
		int y = 3;
		int x = 3;
		int height = 15;
		
		g.setFont(Game.fontDebug);
		
		fps(g, x, y + height * 0);
		currentState(g, x, y + height * 1);
		if (Game.sm.getCurrentStateIndex() == StateManager.GAME_STATE) {
			onGround(g, x, y + height * 2);
			hasDoubleJumped(g, x, y + height * 3);
			xy(g, x, y + height * 4);
			xvyv(g, x, y + height * 6);
			maxHorizontalVelocity(g, x, y + height * 8);
			verticalVelocity(g, x, y + height * 9);
		}
	}
	
	private static void fps(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFontMetrics().stringWidth(String.valueOf(Game.fps) + " FPS") + 5, 15);
		
		g.setColor(Color.WHITE);
		g.drawString(Game.fps + " FPS", x + 3, y + 12);
	}
	
	private static void currentState(Graphics g, int x, int y) {
		String msg = "Current state: " + Game.sm.getCurrentStateSimpleName();
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFontMetrics().stringWidth(msg) + 5, 15);
		
		g.setColor(Color.WHITE);
		g.drawString(msg, x + 3, y + 12);
	}
	
	private static void onGround(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFontMetrics().stringWidth("onGround: " + String.valueOf(GameState.player.onGround) + 5), 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("onGround: " + GameState.player.onGround, x + 3, y + 12);
	}
	
	private static void hasDoubleJumped(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, GameState.player.hasDoubleJumped ? 140 : 145, 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("hasDoubleJumped: " + GameState.player.hasDoubleJumped, x + 3, y + 12);
	}
	
	private static void maxHorizontalVelocity(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y,
				g.getFontMetrics().stringWidth("max horizontal velocity: " + String.valueOf(Player.maxHorizontalVelocity) + 5),
				15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("max horizontal velocity: " + Player.maxHorizontalVelocity, x + 3, y + 12);
	}
	
	private static void verticalVelocity(Graphics g, int x, int y) {
		//		g.setColor(Colour.translucentBlack);
		//		g.fillRect(x, y, g.getFontMetrics().stringWidth("vertical velocity: " + String.valueOf(Player.vertical_velocity) + 5), 15);
		//		g.setFont(Game.fontDebug);
		//		g.setColor(Color.WHITE);
		//		g.drawString("vertical velocity: " + Player.vertical_velocity, x + 3, y + 12);
	}
	
	private static void xy(Graphics g, int x, int y) {
		g.setFont(Game.fontDebug);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFontMetrics().stringWidth("x = " + String.valueOf(GameState.player.getX())) + 5, 15);
		g.setColor(Color.WHITE);
		g.drawString("x = " + GameState.player.getX(), x + 3, y + 12);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, g.getFontMetrics().stringWidth("y = " + String.valueOf(GameState.player.getY())) + 5, 15);
		g.setColor(Color.WHITE);
		g.drawString("y = " + GameState.player.getY(), x + 3, y + 27);
	}
	
	private static void xvyv(Graphics g, int x, int y) {
		g.setFont(Game.fontDebug);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFontMetrics().stringWidth("xv = " + String.valueOf(GameState.player.getXv())) + 5, 15);
		g.setColor(Color.WHITE);
		g.drawString("xv = " + GameState.player.getXv(), x + 3, y + 12);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, g.getFontMetrics().stringWidth("yv = " + String.valueOf(GameState.player.getYv())) + 5, 15);
		g.setColor(Color.WHITE);
		g.drawString("yv = " + GameState.player.getYv(), x + 3, y + 27);
	}
}
