
//Author: Gokulkrishnan Harikrishnan
//Period: 1
//Purpose: Similar to CoordinateSystem from DanceStudio, used for keeping track of the bike's coordinates
//Date: 05-07-2018
//Revsion: 1

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CoordinateSystem {
	private Image picture;
	private AffineTransform coordTransform;

	// CoordinateSystem object uses AffineTransform to track and move the bike
	public CoordinateSystem(int x, int y, Image pic) {
		picture = pic;
		coordTransform = new AffineTransform();

		int w = picture.getWidth(null) / 2;
		int h = picture.getHeight(null) / 2;
		coordTransform.translate(x - w, y - h);
	}

	// Methods used in Bike:

	// Move the object
	public void shift(double dx, double dy) {
		coordTransform.translate(dx, dy);
	}

	// Rotate the object
	public void rotate(double radians) {
		int w = picture.getWidth(null);
		int h = picture.getHeight(null);
		coordTransform.rotate(radians, w / 2, h / 2);
	}

	// Draw the image
	public void drawImage(Graphics g, Image picture) {
		((Graphics2D) g).drawImage(picture, coordTransform, null);
	}
}
