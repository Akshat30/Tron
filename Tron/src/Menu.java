import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

//Important class, creates panel on the side and takes in all the inputs
public class Menu extends JPanel implements MouseListener, MouseMotionListener{
	private Button play, instructions, quit;

	private int pX = 40;
	private int pY = 50;

	private int qX = 40;
	private int qY = 150;

	private int iX = 40;
	private int iY = 100;
	

	private Thread t;
	private Music music;
	
	private int button;

	private static File song = new File("Carnivores.wav");

	private final Color first = new Color(63, 168, 255);
	private final Color second = new Color(175, 255, 248);

	// Constructor
	public Menu() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		music = new Music(song);
		t = new Thread(music);
		t.start();
		play = new Button(pX, pY, first, second);
		quit = new Button(qX, qY, first, second);
		instructions = new Button(iX, iY, first, second);
		button = 0;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);

		g.setColor(play.setColor());
		g.fillPolygon(play);

		g.setColor(quit.setColor());
		g.fillPolygon(quit);

		g.setColor(instructions.setColor());
		g.fillPolygon(instructions);

		g.setColor(Color.BLACK);
		g.drawPolygon(play);
		g.drawPolygon(quit);
		g.drawPolygon(instructions);
		g.drawString("Play", pX + 45, pY + 5);
		g.drawString("Quit", qX + 45, qY + 5);
		g.drawString("Instructions", iX + 45, iY + 5);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		if (play.contains(x, y)) {
			play.setHover(true);
		} else if (quit.contains(x, y)) {
			quit.setHover(true);
		} else if (instructions.contains(x, y)) {
			instructions.setHover(true);
		} else {
			play.setHover(false);
			quit.setHover(false);
			instructions.setHover(false);
		}

		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		if (play.contains(x, y)) {
			button = 1;
		}

		if (quit.contains(x, y)) {
			button = 2;
		}

		if (instructions.contains(x, y)) {
			button = 3;
		}

		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		if (play.contains(x, y)) {
		}

		repaint();

	}
	
	public int getButton() {
		System.out.println("a");
		return button;
	}
	
	

}
