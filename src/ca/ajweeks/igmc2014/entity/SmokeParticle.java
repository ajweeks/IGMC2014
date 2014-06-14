package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ca.ajweeks.igmc2014.Game;

public class SmokeParticle extends Particle {
	
	private static Random rand = new Random(12489126486132L); //does there really need to be a seed?
	
	public SmokeParticle(int x, int y, int width, int height, int life, double v, double a, int angle) {
		super(x, y, width, height, life, v, a, angle);
	}
	
	public static SmokeParticle[] randomParticles(int size) {
		SmokeParticle[] result = new SmokeParticle[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = randomParticle();
		}
		return result;
	}
	
	private static SmokeParticle randomParticle() {
		int v;
		do {
			v = (rand.nextInt(18)) - 9;
		} while (v > -5 && v < 5);
		int angle = rand.nextInt(360);
		return new SmokeParticle(Game.input.x, Game.input.y, 10, 10, rand.nextInt(10) + 50, v, 0.3, angle);
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
	}
	
	@Override
	public void render(Graphics g) {
		int l = life * 4;
		if (l > 255) l = 255;
		if (l < 0) l = 0;
		g.setColor(new Color(150, 150, 150, l));
		g.fillRect(x, y, width, height);
	}
}
