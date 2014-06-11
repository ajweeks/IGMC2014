package ca.ajweeks.igmc2014.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class AchievementParticle extends Particle {
	
	public static final String JUMP = "Congrats! You jumped! :D";
	
	public String message;
	
	public AchievementParticle(int x, int y, int width, int height, int life, float xV, float yV, String message) {
		super(x, y, width, height, life, xV, yV);
		this.message = message;
	}
	
	@Override
	public void render(Graphics g) {
		int yoff = 0;
		yoff /= 3;
		
		g.setColor(new Color(75, 75, 75, 50));
		g.fillRect(x, y + yoff, width, height);
		
		g.setColor(Color.WHITE);
		g.drawString(message, x + 5, y + 20 + yoff);
	}
	
	@Override
	public void update() {
		super.update();
	}
}
