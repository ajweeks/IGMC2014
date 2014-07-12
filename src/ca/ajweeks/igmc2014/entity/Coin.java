package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.state.GameState;

public class Coin {
	
	private static Image[] image = new Image[] { new ImageIcon("res/coin0.png").getImage(), new ImageIcon("res/coin1.png").getImage(),
			new ImageIcon("res/coin2.png").getImage(), new ImageIcon("res/coin3.png").getImage(),
			new ImageIcon("res/coin4.png").getImage(), new ImageIcon("res/coin5.png").getImage(),
			new ImageIcon("res/coin6.png").getImage(), new ImageIcon("res/coin7.png").getImage(),
			new ImageIcon("res/coin8.png").getImage(), new ImageIcon("res/coin9.png").getImage() };
	
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
