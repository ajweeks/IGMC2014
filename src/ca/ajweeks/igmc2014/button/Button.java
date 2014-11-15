package ca.ajweeks.igmc2014.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.input.Mouse;

public class Button {
	
	public int x, y, width, height;
	public boolean hover;
	
	protected Color colour, hColour, tColour;
	protected String text;
	protected Image image;
	protected boolean hasImage;
	protected boolean selected;
	protected boolean enabled;
	protected int radius = 10;
	
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
	
	/** Use this constructor if you want to use the default colours (Colour.button etc..) */
	public Button(int x, int y, int width, int height, String text) {
		this(x, y, width, height, text, Colour.button, Colour.hButton, Colour.offWhite);
	}
	
	/** Use this constructor if you want to supply an image to display on button */
	public Button(int x, int y, int width, int height, String text, Color colour, Color hColour, Color tColour, Image image) {
		this(x, y, width, height, text, colour, hColour, tColour);
		this.image = image;
		this.hasImage = true;
		this.selected = false;
	}
	
	public void setSelected(boolean isSelected) {
		this.selected = isSelected;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	/** @return <code>true</code> if the mouse is being clicked in this button (but not dragged into it) */
	public boolean isClicked() {
		if (!enabled) return false;
		Mouse mouse = Game.mouse;
		if (mouse.getX() > x && mouse.getX() < x + width && mouse.getY() > y && mouse.getY() < y + height) {
			hover = true;
			if (mouse.isLeftClicked()) return true;
		} else hover = false;
		return false;
	}
	
	public void render(Graphics g) {
		g.setFont(Game.font34);
		if (selected) {
			g.setColor(Colour.offWhite);
			g.fillRoundRect(x - 3, y - 3, width + 6, height + 6, radius, radius);
		}
		
		g.setColor(hover ? colour : hColour);
		g.fillRoundRect(x, y, width, height, radius, radius);
		
		g.setColor(tColour);
		g.drawString(text, x + (width / 2) - (g.getFontMetrics().stringWidth(text) / 2), y + (height / 2)
				+ (g.getFontMetrics().getHeight() / 4));
		
		g.setColor(Color.white);
		if (hasImage) g.drawImage(image, x + 2, y + 2, null);
		
		if (!enabled) {
			g.setColor(new Color(125, 125, 125, 125));
			g.fillRoundRect(x, y, width, height, radius, radius);
		}
	}
}
