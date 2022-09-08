
//Author: Gokulkrishnan Harikrishnan
//Period: 1
//Purpose: Creates the Bike object, with methods to move and change the attributes of the object
//Date: 05-07-2018
//Revsion: 4

import java.awt.Image;
import java.awt.Graphics;

public class Bike {
	// Image of bike initialized in Game
	private Image picture;

	// CoordinateSystem object to move and rotate bike
	private CoordinateSystem coordinates;

	// Keeps track of the direction of the bike, used in Controls when turning bike
	private int direction;

	// Constructor
	public Bike(int x, int y, Image pic) {
		picture = pic;
		coordinates = new CoordinateSystem(x, y, pic);
		direction = 4;
	}

	// Move the bike forward
	public void moveForward(int distance) {
		coordinates.shift(distance, 0);
	}

	// Turns this bike and keeps track of direction of bike
	// Used in game to turn the bike in different directions
	public void turn(int degrees) {
		coordinates.rotate(Math.PI * degrees / 180.0);

		// Only 90 and 270 degree rotations are used
		if (degrees == 90) {
			if (direction < 4)
				direction++;
			else
				direction = 1;
		} else if (degrees == 270) {
			if (direction > 1)
				direction--;
			else
				direction = 4;
		}
	}

	// Direction accessor method
	public int getDirection() {
		return direction;
	}

	// Draws the bike
	public void draw(Graphics g) {
		coordinates.drawImage(g, picture);
	}

}
