package ca.ajweeks.igmc2014.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.state.GameState;

public class Coin {
	
	private static Image[] image;
	
	{
		try {
			image = new Image[] { new Image("res/coin0.png"), new Image("res/coin1.png"),
					new Image("res/coin2.png"), new Image("res/coin3.png"),
					new Image("res/coin4.png"), new Image("res/coin5.png"),
					new Image("res/coin6.png"), new Image("res/coin7.png"),
					new Image("res/coin8.png"), new Image("res/coin9.png") };
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private int i = 0;
	private int ticks = 0;
	
	public int x, y;
	
	public boolean removed;
	
	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if (ticks++ > 6) {
			ticks = 0;
			i++;
			if (i > image.length - 1) i = 0;
		}
	}
	
	public void render(Graphics g, int xoff, int yoff) {
		g.drawImage(image[i], (int) (x * Tile.WIDTH + GameState.camera.x + xoff), (int) (y * Tile.WIDTH + GameState.camera.y + yoff), null);
	}
	
	public static Image getFlatCoinImage() {
		return image[4];
	}
	
}
