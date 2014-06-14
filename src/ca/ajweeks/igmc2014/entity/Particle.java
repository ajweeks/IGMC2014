package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;

public class Particle extends Entity {
	
	public int width, height, life, angle;
	public double v, a;
	public boolean removed = false;
	
	public Particle(int x, int y, int width, int height, int life, double v, double a, int angle) {
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
		life--;
		if (life < 0) {
			removed = true;
			return;
		}
		v -= a * delta;
		if (v < 0) v = 0;
		x += v * Math.sin(angle) * delta;
		y += v * Math.cos(angle) * delta;
		
	}
	
	public void render(Graphics g) {}
}
