package ca.ajweeks.igmc2014.gfx;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;

public class Camera {
	
	public int x, y;
	public Player player;
	
	public Camera(Player player) {
		x = player.x - Game.SIZE.width / 2;
		y = player.y - Game.SIZE.height / 2;
		this.player = player;
	}
	
	public void update(int delta) {
		this.x = player.x - Game.SIZE.width / 2;
		this.y = player.y - Game.SIZE.height / 2;
	}
	
	public void render(Graphics g) {
		//......
	}
	
}
