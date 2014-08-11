package ca.ajweeks.igmc2014.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ca.ajweeks.igmc2014.level.Tile;
import ca.ajweeks.igmc2014.sound.Sound;

public class Coin extends Tile {
	private static final long serialVersionUID = 1L;
	
	private final int TICKS_PER_FRAME = 7;
	
	private static Image[] coins;
	
	{
		try {
			coins = new Image[] { new Image("res/coin0.png"), new Image("res/coin1.png"), new Image("res/coin2.png"),
					new Image("res/coin3.png"), new Image("res/coin4.png"), new Image("res/coin5.png"),
					new Image("res/coin6.png"), new Image("res/coin7.png"), new Image("res/coin8.png"),
					new Image("res/coin9.png") };
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private int index = 0;
	private int ticks = 0;
	
	private boolean removed = false;
	
	public Coin(int x, int y, int width, int height) {
		super(Tile.Type.COIN, x, y, width, height);
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
	public void render(int x, int y, Graphics g) {
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
		return coins[4];
	}
	
}
