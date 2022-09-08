
//Author: Coby Young
//Period: 1
//Purpose: Draws the bikes, trails, and the background grid using update method.
//Date: 05-11-2018
//Revision: 1

import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel {

	// Grid JPanel
	public Grid() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 480));
	}

	// Updates game
	public void update(Game players) {
		this.players = players;
		repaint();
	}

	// Game object
	private Game players;

	// Draws bikes, trails, and grid
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(new Color(0, 0, 175));

		int i = 20; // Distance between lines in grid

		for (int x = i; x < 2000; x += i) { // Draws vertical lines
			g.drawLine(x, 0, x, 2000);
		}
		for (int x = i; x < 2000; x += i) { // Draws horizontal lines
			g.drawLine(0, x, 2000, x);
		}

		players.draw(g); // Draws the players (bikes and trails)

	}

}
