
//Author: Akshat Jain
//Period 1
//Purpose: A JPanel class with a main method that creates the menu, and then creates more windows based on what the user chooses.
//Date: 05-16-2018
//Revision: 2

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

//Runnable class
public class Tron extends JPanel implements MouseListener, MouseMotionListener {
	private Button play, instructions, quit, gameMode, credits;

	private int x = 650; // x coordinate of all the Buttons.
	private int y = 120; // y coordinates of the Buttons as a group

	// Play Button
	private int pX = x;
	private int pY = y - 20;

	// Quit Button
	private int qX = x;
	private int qY = y + 180;

	// Instructions Button
	private int iX = x;
	private int iY = y + 30;

	// GameMode Toggle Button
	private int gmX = x;
	private int gmY = y + 80;

	// Credits Button
	private int cX = x;
	private int cY = y + 130;

	// Stores what string is written on toggle game mode button
	private boolean gm;

	// Different states of the game selected
	private enum GameMode {
		NOT_SELECTED, NO_BOOSTS, BOOSTS
	}

	// Game Mode of the game
	private GameMode mode;

	// Menu window, Instructions window, and Credits window
	private static JFrame window, window2, window3;

	// Background
	private final Image tron = (new ImageIcon("tronImage.jpg").getImage());

	// Thread to run music
	private Thread t;
	private static Music music;

	// Background Music
	private static File song = new File("theSonOfFlynn.wav");

	// Colors for the buttons
	private final Color FIRST = new Color(63, 168, 255);
	private final Color SECOND = new Color(83, 252, 249);

	// Color for the toggle button
	private final Color MODES1 = new Color(203, 255, 155);
	private final Color MODES2 = new Color(116, 216, 23);

	// Tron JPanel
	public Tron() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		music = new Music(song);
		t = new Thread(music);
		t.start();
		play = new Button(pX, pY, FIRST, SECOND);
		quit = new Button(qX, qY, FIRST, SECOND);
		instructions = new Button(iX, iY, FIRST, SECOND);
		gameMode = new Button(gmX, gmY, MODES2, MODES1);
		credits = new Button(cX, cY, FIRST, SECOND);
		mode = GameMode.NO_BOOSTS;
		gm = false;

	}

	// Creates a window and adds the Tron Panel
	public static void main(String[] args) {
		Tron panel = new Tron();
		window = new JFrame("Menu");
		Container c = window.getContentPane();
		c.add(panel, BorderLayout.CENTER);
		window.setBounds(100, 100, 823, 419);
		window.setResizable(false);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

	// Draws buttons and accessories on the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(tron, 0, 0, null);

		// Fills buttons as polygons
		g.setColor(play.setColor());
		g.fillPolygon(play);

		g.setColor(quit.setColor());
		g.fillPolygon(quit);

		g.setColor(instructions.setColor());
		g.fillPolygon(instructions);

		g.setColor(credits.setColor());
		g.fillPolygon(credits);

		g.setColor(gameMode.setColor());
		g.fillPolygon(gameMode);

		// Draws a border around the buttons with some thickness
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(3F));
		g.setColor(FIRST);
		g.drawPolygon(play);
		g.drawPolygon(quit);
		g.drawPolygon(instructions);
		g.drawPolygon(credits);
		g.setColor(MODES2);
		g.drawPolygon(gameMode);
		
		g.setColor(Color.BLACK);

		// Toggle button string
		if (!gm)
			g.drawString("No Boosts", gmX + 34, gmY + 5);
		else
			g.drawString("Boosts", gmX + 43, gmY + 5);

		// Draws the string on the buttons
		g.drawString("Play", pX + 45, pY + 5);
		g.drawString("Quit", qX + 45, qY + 5);
		g.drawString("Instructions", iX + 30, iY + 5);
		g.drawString("Credits", cX + 40, cY + 5);

	}

	// Sets the color of button based on if the mouse is hovering over the button
	@Override
	public void mouseMoved(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		if (play.contains(x, y)) { // If mouse is on play
			play.setHover(true);
		} else if (quit.contains(x, y)) { // If mouse is on quit
			quit.setHover(true);
		} else if (instructions.contains(x, y)) { // If mouse is on instructions
			instructions.setHover(true);
		} else if (gameMode.contains(x, y)) { // If mouse is on game mode
			gameMode.setHover(true);
		} else if (credits.contains(x, y)) { // If mouse is on credits
			credits.setHover(true);
		} else { // If mouse is on none
			play.setHover(false);
			quit.setHover(false);
			instructions.setHover(false);
			gameMode.setHover(false);
			credits.setHover(false);
		}

		repaint();

	}

	// If one of the buttons is clicked
	@Override
	public void mousePressed(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		// Play button creates TronGame and removes menu
		if (play.contains(x, y)) {
			if (mode == GameMode.NO_BOOSTS) { // No boosts
				Grid grid = new Grid();
				Game players = new Game(grid);
				TronGame tron = new TronGame(players, grid, false);
				window.setVisible(false);
			} else if (mode == GameMode.BOOSTS) { // Boosts
				Grid grid = new Grid();
				Game players = new Game(grid);
				TronGame tron = new TronGame(players, grid, true);
				window.setVisible(false);
			}
		}

		// Quit button exits program
		if (quit.contains(x, y)) {
			System.exit(0);
		}

		// Instruction button creates window with Instructions panel
		if (instructions.contains(x, y)) {
			Instructions panel = new Instructions();
			window2 = new JFrame("Instructions");
			Container c = window2.getContentPane();
			c.add(panel, BorderLayout.CENTER);
			window2.setBounds(0, 0, 653, 729);
			window2.setDefaultCloseOperation(window2.HIDE_ON_CLOSE);
			window2.setResizable(false);
			window2.setVisible(true);
		}

		// Game Mode toggle sets game mode
		if (gameMode.contains(x, y)) {
			if (gm == true) { // No boosts
				mode = GameMode.NO_BOOSTS;
				gm = false;
			} else if (gm == false) { // Boosts
				mode = GameMode.BOOSTS;
				gm = true;
			}
		}

		// Credits button creates window with Credits panel
		if (credits.contains(x, y)) {
			Credits panel = new Credits();
			window3 = new JFrame("Credits");
			Container c = window3.getContentPane();
			c.add(panel, BorderLayout.CENTER);
			window3.setBounds(100, 100, 729, 475);
			window3.setResizable(false);
			window3.setDefaultCloseOperation(window3.HIDE_ON_CLOSE);
			window3.setVisible(true);
		}

		repaint();

	}

	// Allows Controls to control the background music
	public static void controlMusic(boolean m) {
		if (m) // Pause music
			music.pause();
		else // Play music
			music.play();
	}

	// Unused methods
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
