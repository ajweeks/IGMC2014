package ca.ajweeks.igmc2014.button;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.gfx.Colour;

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
		
		if (colour == Colour.button && hColour == Colour.hButton && tColour == Colour.offWhite)
			System.err.println("Unneccessary args!, use other constructor!!");
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
	
	/** uses default colours (Colour.button etc..) */
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
	public boolean isDown(Input input) {
		if (!enabled) return false;
		if (input.getMouseX() > x && input.getMouseX() < x + width && input.getMouseY() > y && input.getMouseY() < y + height) {
			hover = true;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) return true;
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
		g.drawString(text, x + (width / 2) - (g.getFont().getWidth(text) / 2), y + (height / 2)
				- (g.getFont().getHeight(text) / 2));
		
		if (hasImage) g.drawImage(image, x + 2, y + 2, null);
		
		if (!enabled) {
			g.setColor(new Color(125, 125, 125, 125));
			g.fillRoundRect(x, y, width, height, radius, radius);
		}
	}
}
