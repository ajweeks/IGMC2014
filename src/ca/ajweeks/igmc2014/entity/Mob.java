package ca.ajweeks.igmc2014.entity;

public class Mob extends Entity {
	public int width, height;
	public int xa, ya;
	
	public Mob(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setAcceleration(int xa, int ya) {
		this.xa = xa;
		this.ya = ya;
	}
	
}
