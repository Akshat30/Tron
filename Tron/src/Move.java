
//Author: Akshat Jain
//Period: 1
//Purpose: Moves the bikes forward based on a timer, and also checks to see if a bike hit an obstacle. Used in Controls.
//Date: 05-10-2018
//Revision: 5

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class Move implements ActionListener {
	// Timer object
	private Timer clock;

	// Game object
	private Game players;

	public boolean start; // Ensures that start button is not pressed mid-game

	private boolean turn; // Ensures that bikes cannot turn when not moving

	private int bPoints; // The number of points the blue player has
	private int rPoints; // The number of pointe the red bike has

	private int boost; // Determines which bike is allowed to boost forward

	// The speed at which the bikes move
	private final int SPEED = 5;

	// Move object
	public Move(Game player) {
		players = player;
		turn = true;
		bPoints = 0;
		rPoints = 0;
		boost = 0;
		start = true;

	}

	// Play the timer
	public void play() {
		clock = new Timer(SPEED, this);
		clock.start();
	}

	// Stop the timer
	public void stop() {
		clock.stop();
	}

	// Either prompts the user with a window when some crashes or makes the next
	// step for the players after each timer event
	public void actionPerformed(ActionEvent e) {

		if (players.checkBlue() && players.checkRed()) { // If both bikes crashed at the same time
			bPoints++;
			rPoints++;
			start = true;
			turn = true;
			this.stop();
			JOptionPane.showMessageDialog(null, "Both bikes crashed!\nDraw!", "Draw!", JOptionPane.PLAIN_MESSAGE);

		} else if (players.checkBlue()) { // If the blue bike crashed
			turn = true;
			rPoints++;
			start = true;
			this.stop();
			JOptionPane.showMessageDialog(null, "Blue Crashed!\nRed Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}

		else if (players.checkRed()) { // If the red bike crashed
			turn = true;
			bPoints++;
			start = true;
			this.stop();
			JOptionPane.showMessageDialog(null, "Red Crashed!\nBlue Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}

		players.makeNextStep(boost); // Calls Game's next step method

	}

	// Get the turn field
	public boolean getTurn() {
		return turn;
	}

	// Set the turn field
	public void setTurn(boolean t) {
		turn = t;
	}

	// Set the boost for the makeNextStep method
	public void setBoost(int x) {
		boost = x;
	}

	// Get the number of points blue has
	public int getBlue() {
		return bPoints;
	}

	// Get the number of points red has
	public int getRed() {
		return rPoints;
	}

	// Reset the number of points blue has
	public void resetBlue() {
		bPoints = 0;
	}

	// Reset the number of points red has
	public void resetRed() {
		rPoints = 0;
	}

	// Set the start field
	public void setStart(boolean m) {
		start = m;
	}

	// Get the start field
	public boolean getStart() {
		return start;
	}
}
