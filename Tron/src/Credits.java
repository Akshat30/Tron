
//Author: Coby Young
//Period: 1
//Purpose: JPanel used in Tron to draw the credits image, also made by Coby
//Date: 05-17-2018
//Revsion: 1

import java.awt.*;
import javax.swing.*;

public class Credits extends JPanel {
	private Image credit;

	// Credits JPanel
	public Credits() {
		credit = (new ImageIcon("credits.png")).getImage();
	}

	// For the JPanel to repaint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(credit, 0, 0, null);

	}
}