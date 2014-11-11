package ca.ajweeks.igmc2014.entity;

public class BoundingBox {
	
	private float x, y;
	private int width, height;
	
	/** @param x - the number of tiles from the left of the screen this tile is
	 *  @param y - the number of tiles from the bottom of the screen this bounding box is
	 *  @param width - the number of tiles wide this bounding box is
	 *  @param height - the number of tiles tall this bounding box is
	 *  @param scale - the number of pixels per tile)
	 *  */
	public BoundingBox(float x, float y, int width, int height, int scale) {
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
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
