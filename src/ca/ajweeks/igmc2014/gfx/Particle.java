package ca.ajweeks.igmc2014.gfx;

import java.awt.Graphics;

public class Particle {
	
	public int x, y, width, height, life;
	public float xV, yV;
	public boolean removed = false;
	
	public Particle(int x, int y, int width, int height, int life, float xV, float yV) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.life = life;
		this.xV = xV;
		this.yV = yV;
	}
	
	public void update() {
		life--;
		if (life < 0) {
			removed = true;
			return;
		}
		x += xV;
		y += yV;
	}
	
	public void render(Graphics g) {}
}
