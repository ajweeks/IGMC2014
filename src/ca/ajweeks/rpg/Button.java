package ca.ajweeks.rpg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import ca.ajweeks.rpg.input.Input;

public class Button {
	public int x, y, width, height;
	public boolean hover;
	private Color colour, hColour, tColour;
	private String text;
	private Image image;
	private boolean hasImage;
	
	public Button(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.colour = colour;
		this.hColour = hColour;
		this.tColour = tColour;
		this.hasImage = false;
		this.hover = false;
	}
	
	public Button(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.colour = colour;
		this.hColour = hColour;
		this.tColour = tColour;
		this.image = image;
		this.hasImage = true;
		this.hover = false;
	}
	
	/** @return <code>true</code> if the mouse has clicked in this button*/
	public boolean isDown(Input input) {
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
		
		if (hasImage) {
			g.drawImage(image, x + 5, y + 5, null);
		}
	}
	
}
