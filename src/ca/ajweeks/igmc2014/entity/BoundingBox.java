package ca.ajweeks.igmc2014.entity;

/** A representation of a static 2D rectangle */
public class BoundingBox {
	
	protected float x, y;
	protected float width, height;
	
	public BoundingBox(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/** @return true if this BoundingBox contains b entirely */
	public boolean contains(BoundingBox b) {
		return (b.x > x && b.x < x + width && b.y > y && b.y < y + height);
	}
	
	/** @return true if this BoundingBox touches b at any point */
	public boolean intersects(BoundingBox b) {
		if ((b.x > x & b.x < x + width && b.y > y && b.y < b.height) || //b's top left corner is inside this box
				(b.x + b.width > x && b.x + b.width < x + width && b.y > y && b.y < y + height) || //b's top right corner is inside this box
				(b.x > x & b.x < x + width && b.y + b.height > y && b.y + b.height < y + height) || //b's bottom left corner is inside this box
				(b.x + b.width > x && b.x + b.width < x + width && b.y + b.height > y && b.y + b.height < y + height)) { //b's bottom right corner is inside this box
			return true;
		} else return false;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
