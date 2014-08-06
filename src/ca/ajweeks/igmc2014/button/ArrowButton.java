package ca.ajweeks.igmc2014.button;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ArrowButton extends Button {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	private static final int WIDTH = 55, HEIGHT = 110;
	
	private int dir = 0;
	
	public static Image arrowBtnRightON;
	public static Image arrowBtnRightOFF;
	public static Image arrowBtnLeftON;
	public static Image arrowBtnLeftOFF;
	
	public ArrowButton(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour, int dir) {
		super(x, y, width, height, text, colour, hColour, tColour);
		this.dir = dir;
		
		try {
			arrowBtnRightON = new Image("res/arrow_btn_right_on.png").getScaledCopy(WIDTH, HEIGHT);
			arrowBtnRightOFF = new Image("res/arrow_btn_right_off.png").getScaledCopy(WIDTH, HEIGHT);
			arrowBtnLeftON = new Image("res/arrow_btn_left_on.png").getScaledCopy(WIDTH, HEIGHT);
			arrowBtnLeftOFF = new Image("res/arrow_btn_left_off.png").getScaledCopy(WIDTH, HEIGHT);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isDown(Input input) {
		if (!enabled) return false;
		if (dir == RIGHT || dir == LEFT) {
			if (input.getMouseX() > x && input.getMouseX() < x + WIDTH) {
				hover = true;
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) return true;
			} else hover = false;
		}
		return false;
	}
	
	public void render(Graphics g) {
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
