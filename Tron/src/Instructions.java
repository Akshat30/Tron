
//Author: Gokulkrishnan Harikrishnan
//Period: 1
//Purpose: JPanel used in Tron to draw the instructions image, also made by Gokul
//Date: 05-17-2018
//Revision: 1

import java.awt.*;
import javax.swing.*;

public class Instructions extends JPanel {
	private Image instructions;

	// Instructions JPanel
	public Instructions() {
		instructions = (new ImageIcon("instructions.png")).getImage(); // Instructions Image
	}

	// For the JPanel to repaint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(instructions, 0, 0, null);

	}
}
