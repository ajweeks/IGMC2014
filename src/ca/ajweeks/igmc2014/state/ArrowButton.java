package ca.ajweeks.igmc2014.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.gfx.Button;

public class ArrowButton extends Button {
	
	//	public static final int UP = 0;
	//	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public Image arrowBtnRight = new ImageIcon("res/arrow_btn_right.png").getImage();
	public Image arrowBtnLeft = new ImageIcon("res/arrow_btn_left.png").getImage();
	
	public ArrowButton(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour) {
		super(x, y, width, height, text, colour, hColour, tColour);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
