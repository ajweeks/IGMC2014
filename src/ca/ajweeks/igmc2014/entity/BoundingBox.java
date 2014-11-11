package ca.ajweeks.igmc2014.entity;

import java.awt.Rectangle;

public class BoundingBox {
	
	private float x, y;
	private int width, height;
	/** represent a pixel precision view of each game object */
	private Rectangle bounds;
	
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
		bounds = new Rectangle((int) (x * scale), (int) (y * scale), width * scale, height * scale);
	}
	
	public Rectangle getBounds() {
		return bounds;
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
