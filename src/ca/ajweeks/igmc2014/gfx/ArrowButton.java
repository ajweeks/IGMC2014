package ca.ajweeks.igmc2014.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;

public class ArrowButton extends Button {
	
	//public static final int UP = 0;
	//public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private int dir = 0;
	private int width = 55, height = 110;
	
	public Image arrowBtnRightON = new ImageIcon("res/arrow_btn_right_on.png").getImage().getScaledInstance(width, height,
			Image.SCALE_SMOOTH);
	public Image arrowBtnRightOFF = new ImageIcon("res/arrow_btn_right_off.png").getImage().getScaledInstance(width, height,
			Image.SCALE_SMOOTH);
	public Image arrowBtnLeftON = new ImageIcon("res/arrow_btn_left_on.png").getImage().getScaledInstance(width, height,
			Image.SCALE_SMOOTH);
	public Image arrowBtnLeftOFF = new ImageIcon("res/arrow_btn_left_off.png").getImage().getScaledInstance(width, height,
			Image.SCALE_SMOOTH);
	
	public ArrowButton(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour, int dir) {
		super(x, y, width, height, text, colour, hColour, tColour);
		this.dir = dir;
	}
	
	@Override
	public boolean isDown() {
		if (!enabled) return false;
		if (Game.input.x > x && Game.input.x < x + width && Game.input.y > y && Game.input.y < y + height) {
			hover = true;
			if (Game.input.lM.clicked) return true;
		} else hover = false;
		return false;
	}
	
	public void render(Graphics g, boolean enabled) {
		if (dir == LEFT) {
			if (!enabled) return;
			else if (hover) g.drawImage(arrowBtnLeftON, x, y, null);
			else g.drawImage(arrowBtnLeftOFF, x, y, null);
		} else if (dir == RIGHT) {
			if (!enabled) return;
			else if (hover) g.drawImage(arrowBtnRightON, x, y, null);
			else g.drawImage(arrowBtnRightOFF, x, y, null);
		}
	}
}
