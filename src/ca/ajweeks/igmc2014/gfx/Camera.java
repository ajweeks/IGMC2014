package ca.ajweeks.igmc2014.gfx;

import ca.ajweeks.igmc2014.entity.Player;

public class Camera {
	
	public int x, y;
	public Player player;
	
	public Camera(Player player) {
		this.player = player;
	}
	
	public void update(double delta) {
		this.x = -player.getX() + 200;
		this.y = -player.getY() + 330;
		
		clamp();
	}
	
	private void clamp() {
		if (player.getX() < 200) x = 0;
		if (player.getY() > 330) y = 0;
	}
	
}
