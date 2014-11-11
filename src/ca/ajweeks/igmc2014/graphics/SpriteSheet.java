package ca.ajweeks.igmc2014.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	protected int tileSize;
	protected int width, height;
	
	public SpriteSheet(String path, int tileSize, int width, int height) {
		this.path = path;
		this.tileSize = tileSize;
		this.width = width;
		this.height = height;
	}
	
	/** @return image located at <code>path</code> OR a BufferedImage with size 0 */
	public BufferedImage load() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		new FileNotFoundException("no file found at " + path);
		return image;
	}
	
}
