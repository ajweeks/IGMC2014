package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;

public class Particle extends Entity {
	
	public int width, height, life;
	public double v, a, angle;
	public boolean removed = false;
	
	public Particle(int x, int y, int width, int height, int life, double v, double a, double angle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.life = life;
		this.v = v;
		this.a = a;
		this.angle = angle;
	}
	
	public void update(double delta) {
		if (--life < 0) {
			removed = true;
			return;
		}
		
		v -= (a * delta) + (Math.random() * 0.30);
		if (v < 0) v = 0;
		
		x += v * Math.cos(Math.toRadians(angle)) * delta;
		y += v * Math.sin(Math.toRadians(angle)) * delta;
		
	}
	
	public void render(Graphics g) {}
}
