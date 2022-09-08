
//Author: Coby Young
//Period: 1
//Purpose: Creates the trail object, with methods to turn and move the object.
//Does NOT use CoordinateSystem
//Date: 05-15-2018
//Revision: 3

import java.awt.*;
import java.util.ArrayList;

public class Trail {

	// Starting point of trail
	private Point startPoint;

	// Ending point of trail (everytime the method to move is called)
	private Point endPoint;

	// Direction of the trail, from 1 to four
	private int direction;

	// Number of rectangles in the current trail object
	private int counter;

	// ArrayLists to store the x and coordinates of the rectangles in the trail
	private ArrayList<Integer> x = new ArrayList();
	private ArrayList<Integer> y = new ArrayList();

	// Trail Object
	public Trail(int xx, int yy) {
		direction = 1;
		startPoint = new Point(xx - 2, yy - 2);
		endPoint = new Point(xx - 2, yy - 2);
		x = new ArrayList(0);
		y = new ArrayList(0);
		counter = 0;
	}

	// Used in moveForward to add to the ArrayLists of the trail and add to the
	// coordinates
	public void add(int dx, int dy) {
		endPoint.x += dx;
		endPoint.y += dy;
		x.add(endPoint.x);
		y.add(endPoint.y);
		counter++;
	}

	// The trail moves forward based on the direction
	public void moveForward(int x) {
		if (direction == 1)
			this.add(0, -x);
		else if (direction == 2)
			this.add(x, 0);
		else if (direction == 3)
			this.add(0, x);
		else if (direction == 4)
			this.add(-x, 0);

	}

	// Turns the trail (changes the direction)
	public void turn() {
		if (direction < 4)
			direction++;
		else
			direction = 1;

		// To form new rectangle
		startPoint.x = endPoint.x;
		startPoint.y = endPoint.y;

	}

	// Iterates through the arrays, drawing every rectangle every time the method is
	// called to draw a trail
	public void draw(Graphics g, Color c) {
		g.setColor(c);
		for (int i = 0; i < x.size(); i++) {
			g.fillRect(x.get(i), y.get(i), 4, 4);
		}

	}

	// x coordinates of the rectangles in trail ArrayList accessor
	public ArrayList<Integer> getX() {
		return x;
	}

	// y coordinates of the rectangles in trail ArrayList accessor
	public ArrayList<Integer> getY() {
		return y;
	}

	// Gets direction of trail
	public int getDir() {
		return direction;
	}

	// Gets the most current x coordinate of the trail
	public int getEX() {
		return endPoint.x;
	}

	// Gets the most current y coordinate of the trail
	public int getEY() {
		return endPoint.y;
	}

	// Gets the number of rectangles in the trail
	public int getCount() {
		return counter;
	}
}
