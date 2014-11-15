package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;

public class Coin extends Tile {
	private final int TICKS_PER_FRAME = 7;
	
	private static Image[] coins;
	
	{
		coins = new Image[] { new ImageIcon("res/coin0.png").getImage(), new ImageIcon("res/coin1.png").getImage(),
				new ImageIcon("res/coin2.png").getImage(), new ImageIcon("res/coin3.png").getImage(),
				new ImageIcon("res/coin4.png").getImage(), new ImageIcon("res/coin5.png").getImage(),
				new ImageIcon("res/coin6.png").getImage(), new ImageIcon("res/coin7.png").getImage(),
				new ImageIcon("res/coin8.png").getImage(), new ImageIcon("res/coin9.png").getImage() };
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
	public void render(Graphics g) {
		int x = 0;
		int y = 0;
		
		g.setColor(Color.white);
		g.drawImage(blank, x, y, null);
		if (removed) return;
		g.drawImage(coins[index], x + 17, y + 17, null);
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
