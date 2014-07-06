package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.state.GameState;

public class Coin {
	
	private Image[] image = new Image[] { new ImageIcon("res/coin0.png").getImage(), new ImageIcon("res/coin1.png").getImage(),
			new ImageIcon("res/coin2.png").getImage(), new ImageIcon("res/coin3.png").getImage(),
			new ImageIcon("res/coin4.png").getImage(), new ImageIcon("res/coin5.png").getImage(),
			new ImageIcon("res/coin6.png").getImage(), new ImageIcon("res/coin7.png").getImage(),
			new ImageIcon("res/coin8.png").getImage(), };
	
	private int i = 0;
	private int ticks = 0;
	
	private int x, y;
	
	public boolean removed;
	
	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		ticks++;
		if (ticks > 8) {
			ticks = 0;
			i++;
			if (i > image.length - 1) i = 0;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(image[i], (x * Tile.TILE_WIDTH) + GameState.camera.x, (y * Tile.TILE_WIDTH) + GameState.camera.y, null);
	}
	
}
