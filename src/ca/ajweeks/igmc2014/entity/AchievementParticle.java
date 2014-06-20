package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;

public class AchievementParticle extends Particle {
	
	public String message;
	private static double v = 1.0;
	
	public AchievementParticle(int x, int y, int width, int height, int life, String message) {
		super(x, y, width, height, life, v, 0.3, 0); //TODO fix acceleration issues
		this.message = message;
	}
	
	@Override
	public void render(Graphics g) {
		int yoff = (life + 1) / 5;
		int alpha = Math.max(0, Math.min(255, life * 5));
		
		g.setColor(new Color(120, 150, 240, alpha));
		g.fillRect(x, y + yoff - 12, width, height);
		
		g.setFont(Game.font24);
		g.setColor(new Color(210, 210, 255, alpha));
		g.drawString("Achievement", x + 5, y + 12 + yoff);
		
		g.setColor(new Color(255, 255, 255, alpha));
		g.drawString(message, x + 5, y + 40 + yoff);
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
	}
}
