package ca.ajweeks.igmc2014.gfx;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.state.GameState;

public class RenderDebugOverlay {
	
	private static Game game;
	
	public RenderDebugOverlay(Game game) {
		RenderDebugOverlay.game = game;
	}
	
	public static void render(Graphics g) {
		int y = 3;
		int x = 3;
		int height = 15;
		
		g.setFont(Game.fontDebug);
		
		currentState(g, x, y + height * 0);
		if (game.getCurrentStateID() == Game.GAME_STATE) {
			onGround(g, x, y + height * 1);
			hasDoubleJumped(g, x, y + height * 2);
			xy(g, x, y + height * 3);
			xvyv(g, x, y + height * 5);
			maxHorizontalVelocity(g, x, y + height * 7);
			verticalVelocity(g, x, y + height * 8);
		}
	}
	
	private static void currentState(Graphics g, int x, int y) {
		String msg = "Current state: " + game.getCurrentStateSimpleName();
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth(msg) + 5, 15);
		
		g.setColor(Color.white);
		g.drawString(msg, x + 3, y + 12);
	}
	
	private static void onGround(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth("onGround: " + String.valueOf(GameState.player.onGround) + 5), 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.white);
		g.drawString("onGround: " + GameState.player.onGround, x + 3, y + 12);
	}
	
	private static void hasDoubleJumped(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, GameState.player.hasDoubleJumped ? 140 : 145, 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.white);
		g.drawString("hasDoubleJumped: " + GameState.player.hasDoubleJumped, x + 3, y + 12);
	}
	
	private static void maxHorizontalVelocity(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth("max horizontal velocity: " + String.valueOf(Player.maxHorizontalVelocity) + 5), 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.white);
		g.drawString("max horizontal velocity: " + Player.maxHorizontalVelocity, x + 3, y + 12);
	}
	
	private static void verticalVelocity(Graphics g, int x, int y) {
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth("vertical velocity: " + String.valueOf(GameState.player.getYv()) + 5), 15);
		g.setFont(Game.fontDebug);
		g.setColor(Color.white);
		g.drawString("vertical velocity: " + GameState.player.getYv(), x + 3, y + 12);
	}
	
	private static void xy(Graphics g, int x, int y) {
		g.setFont(Game.fontDebug);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth("x = " + String.valueOf(GameState.player.getX())) + 5, 15);
		g.setColor(Color.white);
		g.drawString("x = " + GameState.player.getX(), x + 3, y + 12);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, g.getFont().getWidth("y = " + String.valueOf(GameState.player.getY())) + 5, 15);
		g.setColor(Color.white);
		g.drawString("y = " + GameState.player.getY(), x + 3, y + 27);
	}
	
	private static void xvyv(Graphics g, int x, int y) {
		g.setFont(Game.fontDebug);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y, g.getFont().getWidth("xv = " + String.valueOf(GameState.player.getXv())) + 5, 15);
		g.setColor(Color.white);
		g.drawString("xv = " + GameState.player.getXv(), x + 3, y + 12);
		
		g.setColor(Colour.translucentBlack);
		g.fillRect(x, y + 15, g.getFont().getWidth("yv = " + String.valueOf(GameState.player.getYv())) + 5, 15);
		g.setColor(Color.white);
		g.drawString("yv = " + GameState.player.getYv(), x + 3, y + 27);
	}
}
