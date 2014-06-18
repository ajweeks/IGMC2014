package ca.ajweeks.igmc2014.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.gfx.Colour;

public class Button {
	
	public int x, y, width, height;
	public boolean hover;
	public boolean enabled;
	protected Color colour, hColour, tColour;
	protected String text;
	protected Image image;
	protected boolean hasImage;
	protected boolean selected;
	
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
		this.selected = false;
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
		this.selected = false;
	}
	
	/** user default colours (Colour.button etc..) */
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.colour = Colour.button;
		this.hColour = Colour.hButton;
		this.tColour = Colour.offWhite;
		this.hover = false;
		this.enabled = true;
		this.hasImage = false;
		this.selected = false;
	}
	
	public void setDeselected() {
		this.selected = false;
	}
	
	public void setSelected() {
		this.selected = true;
	}
	
	/** @return <code>true</code> if the mouse is being clicked in this button (but not dragged into it) */
	public boolean isDown() {
		if (!enabled) return false;
		if (Game.input.x > x && Game.input.x < x + width && Game.input.y > y && Game.input.y < y + height) {
			hover = true;
			if (Game.input.lM.clicked) return true;
		} else hover = false;
		return false;
	}
	
	public void render(Graphics g) {
		g.setFont(Game.font34.deriveFont(34f));
		if (selected) {
			g.setColor(Colour.offWhite);
			g.fillRoundRect(x - 3, y - 3, width + 6, height + 6, 25, 25);
		}
		
		g.setColor(hover ? colour : hColour);
		g.fillRoundRect(x, y, width, height, 25, 25);
		
		g.setColor(tColour);
		g.drawString(text, (int) (x + width / 9.5), (int) (y + height * 0.66));
		
		if (hasImage) g.drawImage(image, x + 2, y + 2, null);
		
		if (!enabled) {
			g.setColor(new Color(125, 125, 125, 125));
			g.fillRoundRect(x, y, width, height, 25, 25);
		}
	}
}
