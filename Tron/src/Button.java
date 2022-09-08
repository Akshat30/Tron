
//Author: Coby Young
//Period: 1
//Purpose: Object extending polygon that represents a button, used in Controls and Tron
//Date: 05-07-2018
//Revision: 1

import java.awt.*;

public class Button extends Polygon {
	// Used in control if mouse above the button
	private boolean hover;

	// The two colors of the button
	private Color color1;
	private Color color2;

	// Button object
	public Button(int x, int y, Color c1, Color c2) {
		super(getArrayX(x), getArrayY(y), 6);
		hover = false;
		color1 = c1;
		color2 = c2;

	}

	// Hover accessor
	public boolean getHover() {
		return this.hover;
	}

	// Hover modifier, whether there is a mouse over the button, hover then affects
	// the color of the button.
	public void setHover(boolean change) {
		this.hover = change;
	}

	// Contains method to check if a point is inside the button
	public boolean contains(int x, int y) {
		return super.contains(x, y);
	}

	// Set color based on if mouse is hovering over button
	public Color setColor() {
		if (this.hover)
			return color1;
		else
			return color2;
	}

	// Helper methods to get the x-array from the single x coordinate
	private static int[] getArrayX(int sX) {
		int[] startXCoordinates = { sX, sX + 25, sX + 100, sX + 125, sX + 100, sX + 25 };

		return startXCoordinates;
	}

	// Helper methods to get the y-array from the single y coordinate
	private static int[] getArrayY(int sY) {
		int[] startYCoordinates = { sY, sY - 10, sY - 10, sY, sY + 10, sY + 10 };

		return startYCoordinates;
	}

}
