package ca.ajweeks.rpg;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.input.Input;

public class Button {
	public int x, y, width, height;
	public boolean hover;
	public Color colour, hColour, tColour;
	public String text;
	
	public Button(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.colour = colour;
		this.hColour = hColour;
		this.tColour = tColour;
		this.hover = false;
	}
	
	/** @return <code>true</code> if the mouse has clicked in this button*/
	public boolean update(Input input) {
		if (input.x > this.x && input.x < this.x + this.width && input.y > this.y && input.y < this.y + this.height) {
			this.hover = true;
			if (input.leftMouse) return true;
		} else this.hover = false;
		return false;
	}
	
	public void render(Graphics g) {
		g.setColor(hover ? colour : hColour);
		g.fillRoundRect(x, y, width, height, 25, 25);
		
		g.setColor(tColour);
		g.drawString(text, x + 38, y + 45);
	}
	
}
