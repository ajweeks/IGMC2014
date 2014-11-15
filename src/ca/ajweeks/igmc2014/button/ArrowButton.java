package ca.ajweeks.igmc2014.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.input.Mouse;

public class ArrowButton extends Button {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public static final int WIDTH = 55, HEIGHT = 110;
	
	private int dir = 0;
	
	public static Image arrowBtnRightON;
	public static Image arrowBtnRightOFF;
	public static Image arrowBtnLeftON;
	public static Image arrowBtnLeftOFF;
	
	public ArrowButton(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour, int dir) {
		super(x, y, width, height, text, colour, hColour, tColour);
		this.dir = dir;
		
		arrowBtnRightON = new ImageIcon("res/arrow_btn_right_on.png").getImage();
		arrowBtnRightOFF = new ImageIcon("res/arrow_btn_right_off.png").getImage();
		arrowBtnLeftON = new ImageIcon("res/arrow_btn_left_on.png").getImage();
		arrowBtnLeftOFF = new ImageIcon("res/arrow_btn_left_off.png").getImage();
	}
	
	@Override
	public boolean isClicked() {
		if (!enabled) return false;
		Mouse mouse = Game.mouse;
		if (dir == RIGHT || dir == LEFT) {
			if (mouse.getX() > x && mouse.getX() < x + WIDTH) {
				hover = true;
				if (mouse.isLeftClicked()) return true;
			} else hover = false;
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
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
