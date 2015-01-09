package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.level.Level;
import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;
import ca.ajweeks.igmc2014.state.GameState;

public class Coin extends Tile {
	private final int TICKS_PER_FRAME = 10;
	
	private static Image[] coins;
	
	{
		coins = new Image[] { new ImageIcon("res/coins/coin0.png").getImage(), new ImageIcon("res/coins/coin1.png").getImage(),
				new ImageIcon("res/coins/coin2.png").getImage(), new ImageIcon("res/coins/coin3.png").getImage(),
				new ImageIcon("res/coins/coin4.png").getImage(), new ImageIcon("res/coins/coin5.png").getImage(),
				new ImageIcon("res/coins/coin6.png").getImage(), new ImageIcon("res/coins/coin7.png").getImage(),
				new ImageIcon("res/coins/coin8.png").getImage(), new ImageIcon("res/coins/coin9.png").getImage() };
	}
	
	private int index = 0;
	private int ticks = 0;
	
	private boolean removed = false;
	
	public Coin(int x, int y) {
		super(x, y, Tile.Type.COIN);
	}
	
	@Override
	public void update() {
		if (removed) return;
		if (ticks++ >= TICKS_PER_FRAME) {
			ticks = 0;
			index++;
			if (index > coins.length - 1) index = 0;
		}
	}
	
	@Override
	public void render(Level level, Graphics g) {
		g.drawImage(blank, (int) (getX() * PIXEL_WIDTH + GameState.camera.x), (int) (getY() * PIXEL_WIDTH + GameState.camera.y),
				null);
		if (removed) return;
		g.drawImage(coins[index], (int) (getX() * PIXEL_WIDTH + 17 + GameState.camera.x),
				(int) (getY() * PIXEL_WIDTH + 17 + GameState.camera.y), null);
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void remove() {
		Sound.COIN.play(); //LATER make coin sound shorter
		removed = true;
	}
	
	public Image getImage() {
		return coins[index];
	}
	
	public static Image getFlatCoinImage() {
		return Coin.coins[4];
	}
	
}
