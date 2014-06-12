package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;

public class Particle extends Entity {
	
	public int width, height, life;
	public boolean removed = false;
	
	public Particle(int x, int y, int width, int height, int life, int xv, int yv) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.life = life;
		this.xv = xv;
		this.yv = yv;
	}
	
	public void update(double delta) {
		life--;
		if (life < 0) {
			removed = true;
			return;
		}
		x += xv;
		y += yv;
	}
	
	public void render(Graphics g) {}
}
