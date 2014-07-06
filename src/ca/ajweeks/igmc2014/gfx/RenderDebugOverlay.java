package ca.ajweeks.igmc2014.gfx;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;
import ca.ajweeks.igmc2014.state.StateManager;

public class RenderDebugOverlay {
	
	private static int ypos = 3;
	private static int xpos = 3;
	private static int height = 15;
	
	public static void render(Graphics g) {
		fps(g);
		currentState(g);
		if (Game.sm.getCurrentStateIndex() == StateManager.GAME_STATE) {
			onGround(g);
			hasDoubleJumped(g);
			playerSpeed(g);
			xy(g);
		}
		ypos = 3;
	}
	
	private static void fps(Graphics g) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, 44, 15);
		
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString(Game.fps + " FPS", xpos + 3, ypos + 12);
		ypos += height;
	}
	
	private static void currentState(Graphics g) {
		String msg = "Current state: " + Game.sm.getCurrentStateSimpleName();
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, (int) (msg.length() * 6.5), 15);
		
		g.setColor(Color.WHITE);
		g.drawString(msg, xpos + 3, ypos + 12);
		ypos += height;
	}
	
	private static void onGround(Graphics g) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, GameState.player.onGround ? 90 : 95, 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("onGround: " + GameState.player.onGround, xpos + 3, ypos + 12);
		ypos += height;
	}
	
	private static void hasDoubleJumped(Graphics g) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, GameState.player.hasDoubleJumped ? 140 : 145, 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("hasDoubleJumped: " + GameState.player.hasDoubleJumped, xpos + 3, ypos + 12);
		ypos += height;
	}
	
	private static void playerSpeed(Graphics g) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, 98, 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.WHITE);
		g.drawString("player speed: " + Player.speed, xpos + 3, ypos + 12);
		ypos += height;
	}
	
	private static void xy(Graphics g) {
		g.setFont(Game.fontDebug);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos, g.getFontMetrics().stringWidth(String.valueOf(GameState.player.getX()) + "x = " + 5), 15);
		g.setColor(Color.WHITE);
		g.drawString("x = " + GameState.player.getX(), xpos + 3, ypos + 12);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(xpos, ypos + 15, g.getFontMetrics().stringWidth(String.valueOf(GameState.player.getY()) + "y = " + 5), 15);
		g.setColor(Color.WHITE);
		g.drawString("y = " + GameState.player.getY(), xpos + 3, ypos + 26);
		ypos += height * 2;
	}
}
