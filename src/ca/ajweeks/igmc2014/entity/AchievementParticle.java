package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;

public class AchievementParticle extends Particle {
	
	public static final String JUMP = "Congrats! You jumped! :D";
	
	public String message;
	private static double v = 1.0;
	
	public AchievementParticle(int x, int y, int width, int height, int life, String message) {
		super(x, y, width, height, life, v, 0, 90);
		this.message = message;
	}
	
	@Override
	public void render(Graphics g) {
		int yoff = 0;
		yoff /= 3; //wut?
		
		g.setColor(new Color(75, 75, 75, 50));
		g.fillRect(x, y + yoff, width, height);
		
		g.setColor(Color.WHITE);
		g.drawString(message, x + 5, y + 20 + yoff);
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
	}
}
