package ca.ajweeks.igmc2014.gfx;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.Player;
import ca.ajweeks.igmc2014.level.Chunk;
import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.level.Tile;

public class Camera {
	
	public double x, y;
	private Player player;
	private Level level;
	
	private int xoff = 180;
	private int yoff = 400;
	
	public Camera(Player player, Level level) {
		this.player = player;
		this.level = level;
	}
	
	public void update(double delta) {
		x = -player.getX() * Tile.WIDTH + xoff;
		y = -player.getY() * Tile.WIDTH + yoff;
		
		clamp();
	}
	
	private void clamp() {
		if (player.getX() * Tile.WIDTH < xoff) x = 0;
		if (player.getY() * Tile.WIDTH < yoff) y = 0;
		
		double maxX = level.chunks[0].length * Chunk.WIDTH * Tile.WIDTH - Game.SIZE.width + xoff;
		if (player.getX() * Tile.WIDTH > maxX)
			x = -(level.chunks[0].length) * Chunk.WIDTH * Tile.WIDTH + Game.SIZE.width;
		
		double maxY = (level.chunks.length - 1) * Tile.WIDTH * Chunk.WIDTH;
		if (player.getY() * Tile.WIDTH > maxY) y = maxY;
		
		if(y < 0) y = 0;
	}
}
