
//Author: Akshat Jain
//Period: 1
//Purpose: Creates the bikes and trails, has a lot of public methods for other classes to make changes, and also has methods to check for collisions
//Date: 05-14-2018
//Revision: 3

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Game {

	// Bikes
	private Bike red, blue;

	// Images for bikes
	private Image redBike, blueBike;

	// Trails
	private Trail blueTrail, redTrail;

	// Rectangles to test collisions
	private Rectangle btest;
	private Rectangle rtest;

	// Grid object to draw bikes on
	private Grid grid;

	// Counts how many times the bike jumps in a boost
	private int redCounter = 0;
	private int blueCounter = 0;

	// Pixels to "move" trail when checking for collisons
	private final int move = 10;

	// Game object
	public Game(Grid g) {
		grid = g;
		redBike = (new ImageIcon("redBike.gif")).getImage();
		blueBike = (new ImageIcon("blueBike.gif")).getImage();

	}

	// Sets up the bikes, used in start and restart in Controls
	public void setup() {
		int x = grid.getWidth() / 2;
		int y = grid.getHeight() / 2;
		red = new Bike(x - 200, y, redBike);
		blue = new Bike(x + 200, y, blueBike);
		red.turn(-90);
		blue.turn(-90);
		blueTrail = new Trail(x + 200, y);
		redTrail = new Trail(x - 200, y);
		grid.update(this);

	}

	// Moves the trails and bikes forward, and boosts them when needed
	// Called in Move
	public void makeNextStep(int i) {
		int b = 9;
		int jump = 10;
		if (i == 1 && redCounter < jump) { // If red boost is called
			red.moveForward(b);
			blue.moveForward(1);
			blueTrail.moveForward(1);
			redTrail.moveForward(b);
			grid.update(this);
			redCounter++;
		} else if (i == 2 && blueCounter < jump) { // If blue boost is called
			red.moveForward(1);
			blue.moveForward(b);
			blueTrail.moveForward(b);
			redTrail.moveForward(1);
			grid.update(this);
			blueCounter++;
		} else { // If no boost is called
			red.moveForward(1);
			blue.moveForward(1);
			blueTrail.moveForward(1);
			redTrail.moveForward(1);
			grid.update(this);
		}
	}

	// All these bike and trail turn/getDir methods are used in control to turn the
	// bikes/turns based on keyboard inputs.
	// Which is why the directions of the bikes is also necessary.

	// Turns the red bike to its left
	public void redLeft() {
		red.turn(270);
		grid.update(this);
	}

	// Turns red bike to its right
	public void redRight() {
		red.turn(90);
		grid.update(this);
	}

	// Turns blue bike to its left
	public void blueLeft() {
		blue.turn(270);
		grid.update(this);
	}

	// Turns blue bike to its right
	public void blueRight() {
		blue.turn(90);
		grid.update(this);
	}

	// Turns blue trail to its left
	public void btTurnLeft() {
		blueTrail.turn();
		blueTrail.turn();
		blueTrail.turn();
	}

	// Turns blue trail to its right
	public void btTurnRight() {
		blueTrail.turn();
	}

	// Turns red trail to its left
	public void rtTurnLeft() {
		redTrail.turn();
		redTrail.turn();
		redTrail.turn();
	}

	// Turns red trail to its right
	public void rtTurnRight() {
		redTrail.turn();
	}

	// Returns the direction of the blue trail
	public int getBTDir() {
		return blueTrail.getDir();
	}

	// Returns the direction of the red trail
	public int getRTDir() {
		return redTrail.getDir();
	}

	// Returns the direction of the blue bike
	public int getBlueDir() {
		return blue.getDirection();
	}

	// Returns the direction of the red bike
	public int getRedDir() {
		return red.getDirection();
	}

	// Draws the bike and trail
	public void draw(Graphics g) {
		blueTrail.draw(g, Color.BLUE);
		redTrail.draw(g, Color.RED);
		red.draw(g);
		blue.draw(g);
	}

	// Checks if red has collided, called in Move
	public boolean checkRed() {
		boolean check = false;

		int r = redTrail.getDir();

		// Arrays storing the x and y coordinates of all the rectangles in the trails
		ArrayList<Integer> xB = blueTrail.getX();
		ArrayList<Integer> yB = blueTrail.getY();
		ArrayList<Integer> xR = redTrail.getX();
		ArrayList<Integer> yR = redTrail.getY();

		// The most current x/y coordinate of the red/blue bike
		int x = redTrail.getEX();
		int y = redTrail.getEY();
		int xBlue = blueTrail.getEX();
		int yBlue = blueTrail.getEY();

		// Moves x or y depending on direction of bike to match the bikes tip when
		// colliding
		if (r == 1) {
			x = redTrail.getEX();
			y = redTrail.getEY() - move;
		} else if (r == 2) {
			x = redTrail.getEX() + move;
			y = redTrail.getEY();
		} else if (r == 3) {
			x = redTrail.getEX();
			y = redTrail.getEY() + move;
		} else if (r == 4) {
			x = redTrail.getEX() - move;
			y = redTrail.getEY();
		}

		// Checks if red will crash into the blue trail by creating a rectangle for each
		// set of points from the trail's arraylists and using the contains method
		for (int i = 0; i < xB.size(); i++) {

			rtest = new Rectangle(xB.get(i), yB.get(i), 10, 10);
			if (rtest.contains(x, y))
				check = true;

			rtest = new Rectangle(x, y, 10, 10);
			if (rtest.contains(xB.get(i), yB.get(i)))
				check = true;
		}

		// Checks if red will crash into its own trail by creating a rectangle for each
		// set of points from the trail's arraylists and using the contains method
		for (int i = redTrail.getCount() - 1; i > 0; i--) {

			rtest = new Rectangle(xR.get(i), yR.get(i), 10, 10);
			if (rtest.contains(x, y))
				check = true;

			rtest = new Rectangle(x, y, 10, 10);
			if (rtest.contains(xR.get(i), yR.get(i)))
				check = true;
		}

		// If bikes crash head to head
		if (x == xBlue && y == yBlue)
			check = true;

		// If bikes hit outer border of grid panel
		if (x <= 0)
			check = true;
		else if (x >= grid.getBounds().getWidth() - 5)
			check = true;
		else if (y <= 0)
			check = true;
		else if (y >= grid.getBounds().getHeight() - 5)
			check = true;

		return check;

	}

	// Checks if blue has collided, called in Move
	public boolean checkBlue() {
		boolean check = false;

		int b = blueTrail.getDir();

		// Arrays storing the x and y coordinates of all the rectangles in the trails
		ArrayList<Integer> xB = blueTrail.getX();
		ArrayList<Integer> yB = blueTrail.getY();
		ArrayList<Integer> xR = redTrail.getX();
		ArrayList<Integer> yR = redTrail.getY();

		// The most current x/y coordinate of the red/blue bike
		int x = blueTrail.getEX();
		int y = blueTrail.getEY();
		int xRed = redTrail.getEX();
		int yRed = redTrail.getEY();

		// Moves x or y depending on direction of bike to match the bikes tip when
		// colliding
		if (b == 1) {
			x = blueTrail.getEX();
			y = blueTrail.getEY() - move;
		} else if (b == 2) {
			x = blueTrail.getEX() + move;
			y = blueTrail.getEY();
		} else if (b == 3) {
			x = blueTrail.getEX();
			y = blueTrail.getEY() + move;
		} else if (b == 4) {
			x = blueTrail.getEX() - move;
			y = blueTrail.getEY();
		}

		// Checks if blue will crash into the red trail by creating a rectangle for each
		// set of points from the trail's arraylists and using the contains method
		for (int i = 0; i < xB.size(); i++) {
			btest = new Rectangle(xR.get(i), yR.get(i), 10, 10);
			if (btest.contains(x, y))
				check = true;
			btest = new Rectangle(x, y, 10, 10);
			if (btest.contains(xR.get(i), yR.get(i)))
				check = true;
		}

		// Checks if red will crash into its own trail by creating a rectangle for each
		// set of points from the trail's arraylists and using the contains method
		for (int i = blueTrail.getCount() - 1; i > 0; i--) {
			btest = new Rectangle(xB.get(i), yB.get(i), 10, 10);
			if (btest.contains(x, y))
				check = true;
			btest = new Rectangle(x, y, 10, 10);
			if (btest.contains(xB.get(i), yB.get(i)))
				check = true;
		}

		// If bikes crash head to head
		if (x == xRed && y == yRed)
			check = true;

		// If bikes hit outer border of grid panel
		if (x <= 0)
			check = true;
		else if (x >= grid.getBounds().getWidth() - 5)// 617
			check = true;
		else if (y <= 0)
			check = true;
		else if (y >= grid.getBounds().getHeight() - 5)// 464
			check = true;

		return check;
	}

	// Called from Controls, to restart the game
	public void restart() {
		this.setup();
	}

	// Resets the blue boost counter
	public void resetBlueBoost() {
		blueCounter = 0;
	}

	// Resets the red boost counter
	public void resetRedBoost() {
		redCounter = 0;
	}
}
