
//Author: Akshat Jain
//Period: 1
//Purpose: Creates the window with the Grid and Controls panels, starting the game
//Date: 05-10-2018
//Revision: 1

import java.awt.*;
import javax.swing.*;

public class TronGame extends JFrame {
	public TronGame(Game players, Grid grid, boolean mode) {
		super("Tron");

		Container c = getContentPane();

		// Controls Panel
		Controls control = new Controls(players, mode);

		// Add panels
		c.add(grid);
		c.add(control);

		// Sets layout of panels
		BoxLayout box = new BoxLayout(c, BoxLayout.LINE_AXIS);
		c.setLayout(box);

		grid.setBackground(Color.BLACK);

		// Window set up
		setBounds(0, 0, 1000, 725);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		players.setup();
	}

}
