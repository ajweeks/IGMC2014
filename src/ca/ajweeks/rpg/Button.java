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
	public boolean enabled;
	private boolean selected;
	
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
		this.enabled = true;
		this.hasImage = false;
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
		this.hover = false;
		this.enabled = true;
		this.image = image;
		this.hasImage = true;
	}
	
	public void setDeselected() {
		this.selected = false;
	}
	
	public void setSelected() {
		this.selected = true;
	}
	
	/** @return <code>true</code> if the mouse is being clicked in this button (but not dragged into it) */
	public boolean isDown(Input input) {
		if (!enabled) return false;
		if (input.x > this.x && input.x < this.x + this.width && input.y > this.y && input.y < this.y + this.height) {
			this.hover = true;
			if (input.leftMouse && !RPG.leftWasDown) return true;
		} else this.hover = false;
		return false;
	}
	
	public void render(Graphics g) {
		if (selected) {
			g.setColor(Colour.offWhite);
			g.fillRoundRect(x - 3, y - 3, width + 6, height + 6, 25, 25);
		}
		
		g.setColor(hover ? colour : hColour);
		g.fillRoundRect(x, y, width, height, 25, 25);
		
		g.setColor(tColour);
		g.drawString(text, x + 38, y + 45);
		
		if (hasImage) {
			g.drawImage(image, x + 5, y + 5, null);
		}
		
		if (!enabled) {
			g.setColor(new Color(125, 125, 125, 125));
			g.fillRoundRect(x, y, width, height, 25, 25);
		}
	}
}
