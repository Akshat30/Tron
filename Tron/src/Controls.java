
//Author: Akshat Jain
//Period: 1
//Purpose: Creates the panel on the right with the buttons, scoreboard, and boost tracker. Also takes in keyboard inputs to turn the bikes and apply boosts
//Date: 05-15-2018
//Revision: 7

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Controls extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	// Buttons for the menu on the right of the window
	private Button start, quit, music, restart;

	// Move object to move bikes
	private Move move;

	// Game object
	private Game players;

	// Number of boosts each bike has left
	private int blueBoosts;
	private int redBoosts;

	// Coordinates:
	// Start button
	private int sX = 40;
	private int sY = 50;

	// Quit button
	private int qX = 40;
	private int qY = 200;

	// Music button
	private int mX = 40;
	private int mY = 100;

	// Restart button
	private int rX = 40;
	private int rY = 150;

	// Scoreboard
	private int scX = 40;
	private int scY = 380;

	// Boosts
	private int bX = 60;
	private int bY = 280;

	// Number of time window for win/loss shows up
	private int windowCounter = 0;

	// State of music
	private enum State {
		PLAYING, PAUSED
	}

	private State state;

	// Whether the music button is crossed of or not
	private boolean line;

	// Whether the toggle button is on boost or no boosts, whether players can boost
	private boolean mode;

	// Colors for button
	private final Color FIRST = new Color(63, 168, 255);
	private final Color SECOND = new Color(175, 255, 248);

	// Number of points to win the match
	private final int POINTS_TO_WIN = 5;

	// Controls JPanel
	public Controls(Game player, boolean m) {
		players = player;
		setSize(new Dimension(50, 480));
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		start = new Button(sX, sY, FIRST, SECOND);
		quit = new Button(qX, qY, FIRST, SECOND);
		music = new Button(mX, mY, FIRST, SECOND);
		restart = new Button(rX, rY, FIRST, SECOND);
		move = new Move(players);
		line = false;
		state = State.PLAYING;
		windowCounter = 0;
		mode = m;
		blueBoosts = 0;
		redBoosts = 0;
	}

	// Draws panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);

		// Fills buttons as polygons
		g.setColor(start.setColor());
		g.fillPolygon(start);

		g.setColor(quit.setColor());
		g.fillPolygon(quit);

		g.setColor(music.setColor());
		g.fillPolygon(music);

		g.setColor(restart.setColor());
		g.fillPolygon(restart);

		// Cross on the music button based on playing or not
		g.setColor(Color.RED);
		if (line) {
			g.drawLine(mX + 25, mY - 10, mX + 100, mY + 10);
			g.drawLine(mX + 100, mY - 10, mX + 25, mY + 10);
		}

		// Draws outline around buttons
		g.setColor(Color.BLACK);
		g.drawPolygon(start);
		g.drawPolygon(quit);
		g.drawPolygon(music);
		g.drawPolygon(restart);

		// Draws string on the buttons
		g.drawString("Start", sX + 45, sY + 5);
		g.drawString("Quit", qX + 45, qY + 5);
		g.drawString("Music", mX + 45, mY + 5);
		g.drawString("Restart", rX + 42, rY + 5);

		// Scoreboard
		g.drawRect(scX, scY, 125, 50);
		g.drawString("Scoreboard", scX + 28, scY - 10);

		g.setColor(Color.BLUE);
		g.fillRect(scX, scY, 45, 50);

		g.setColor(Color.RED);
		g.fillRect(scX + 80, scY, 45, 50);

		g.setColor(Color.WHITE);
		g.drawString(move.getBlue() + "", scX + 15, scY + 30); // Blue Score
		g.drawString(move.getRed() + "", scX + 95, scY + 30); // Red score

		g.setColor(Color.BLACK);
		g.drawString("to", scX + 55, scY + 30);

		// Draws the number of boosts left
		if (mode) { // Only if boost option is selected
			g.drawString("Boosts", 76, 260);

			g.setColor(Color.BLUE);

			if (blueBoosts == 0) { // 3 boosts
				g.fillOval(bX, bY, 10, 10);
				g.fillOval(bX, bY + 20, 10, 10);
				g.fillOval(bX, bY + 40, 10, 10);
			} else if (blueBoosts == 1) { // 2 boosts
				g.fillOval(bX, bY, 10, 10);
				g.fillOval(bX, bY + 20, 10, 10);

			} else if (blueBoosts == 2) { // 1 boost
				g.fillOval(bX, bY, 10, 10);

			}

			g.setColor(Color.RED);
			if (redBoosts == 0) { // 3 boosts
				g.fillOval(bX + 60, bY, 10, 10);
				g.fillOval(bX + 60, bY + 20, 10, 10);
				g.fillOval(bX + 60, bY + 40, 10, 10);
			} else if (redBoosts == 1) { // 2 boosts
				g.fillOval(bX + 60, bY, 10, 10);
				g.fillOval(bX + 60, bY + 20, 10, 10);

			} else if (redBoosts == 2) { // 1 boost
				g.fillOval(bX + 60, bY, 10, 10);

			}
		}

		// If both or one of the bikes reaches the points to win, creates an option
		// message. Then exits the game.
		if (move.getBlue() == POINTS_TO_WIN && move.getRed() == POINTS_TO_WIN && windowCounter == 0) { // Both have 5
			UIManager.put("OptionPane.okButtonText", "Exit");
			JOptionPane.showMessageDialog(null, "Game Over!\nIt's a draw!", "Match Winner!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		} else if (move.getBlue() == POINTS_TO_WIN && windowCounter == 0) { // Blue has 5
			UIManager.put("OptionPane.okButtonText", "Exit");
			JOptionPane.showMessageDialog(null, "Game Over!\nBlue wins the match!", "Match Winner!",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		} else if (move.getRed() == POINTS_TO_WIN && windowCounter == 0) { // Red has 5
			UIManager.put("OptionPane.okButtonText", "Exit");
			JOptionPane.showMessageDialog(null, "Game Over!\nRed wins the match!", "Match Winner!",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}

	}

	// Changes color of button if mouse is hovering over it
	public void mouseMoved(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		if (start.contains(x, y)) { // If on start
			start.setHover(true);
		} else if (quit.contains(x, y)) { // If on quit
			quit.setHover(true);
		} else if (music.contains(x, y)) { // If on music
			music.setHover(true);
		} else if (restart.contains(x, y)) { // If on restart
			restart.setHover(true);
		} else { // If on none
			start.setHover(false);
			quit.setHover(false);
			music.setHover(false);
			restart.setHover(false);
		}

		repaint();
	}

	// If mouse clicks one of the buttons
	public void mousePressed(MouseEvent e) {
		int x, y;

		x = e.getX();
		y = e.getY();

		// Start button
		// Starts the game, resets the boosts, and allows players to turn
		if (start.contains(x, y)) {
			if (move.getStart()) {
				move.setTurn(false);
				move.play();
				players.restart();
				move.setTurn(false);
				move.setBoost(0);
				blueBoosts = 0;
				redBoosts = 0;
				move.setStart(false);
			}
		}

		// Quit button
		if (quit.contains(x, y)) {
			System.exit(0);
		}

		// Toggle music button
		if (music.contains(x, y)) {
			if (state == State.PLAYING) { // Pauses music
				Tron.controlMusic(true);
				line = true;
				state = State.PAUSED;
			} else if (state == State.PAUSED) { // Plays music
				Tron.controlMusic(false);
				line = false;
				state = State.PLAYING;
			}
		}

		// Restart button
		// Similar to the start button, except resets the score.
		if (restart.contains(x, y)) {
			players.restart();
			move.resetBlue();
			move.resetRed();
			move.setBoost(0);
			windowCounter = 0;
			blueBoosts = 0;
			redBoosts = 0;

		}
		repaint();
	}

	// Keys pressed to turn the bikes and apply boosts
	// Gets direction of bikes in order to decide which method to call (to turn the
	// bike)

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (!move.getTurn()) {
			if (key == KeyEvent.VK_LEFT) { // Left Arrow (Blue)
				if (players.getBlueDir() == 4) { // If up
					players.blueLeft();
					players.btTurnLeft();
				} else if (players.getBlueDir() == 2) { // If down
					players.blueRight();
					players.btTurnRight();
				}
			}

			else if (key == KeyEvent.VK_RIGHT) { // Right Arrow (Blue)
				if (players.getBlueDir() == 4) {// If up
					players.blueRight();
					players.btTurnRight();
				} else if (players.getBlueDir() == 2) { // If down
					players.blueLeft();
					players.btTurnLeft();
				}

			}

			else if (key == KeyEvent.VK_UP) { // Up Arrow (Blue)
				if (players.getBlueDir() == 1) { // If right
					players.blueLeft();
					players.btTurnLeft();
				} else if (players.getBlueDir() == 3) { // If left
					players.blueRight();
					players.btTurnRight();
				}
			}

			else if (key == KeyEvent.VK_DOWN) { // Down Arrow (Blue)
				if (players.getBlueDir() == 1) { // If right
					players.blueRight();
					players.btTurnRight();
				} else if (players.getBlueDir() == 3) { // If left
					players.blueLeft();
					players.btTurnLeft();
				}
			}

			if (key == KeyEvent.VK_A) { // A Key (Red Left)
				if (players.getRedDir() == 4) { // If up
					players.redLeft();
					players.rtTurnLeft();
				} else if (players.getRedDir() == 2) { // If down
					players.redRight();
					players.rtTurnRight();
				}
			}

			else if (key == KeyEvent.VK_D) { // D Key (Red Right)
				if (players.getRedDir() == 4) { // If up
					players.redRight();
					players.rtTurnRight();
				} else if (players.getRedDir() == 2) { // If down
					players.redLeft();
					players.rtTurnLeft();

				}
			}

			else if (key == KeyEvent.VK_W) { // W Key (Red Up)
				if (players.getRedDir() == 1) { // If right
					players.redLeft();
					players.rtTurnLeft();
				} else if (players.getRedDir() == 3) { // If left
					players.redRight();
					players.rtTurnRight();
				}
			}

			else if (key == KeyEvent.VK_S) { // S Key (Red Down)
				if (players.getRedDir() == 1) { // If right
					players.redRight();
					players.rtTurnRight();
				} else if (players.getRedDir() == 3) {// If left
					players.redLeft();
					players.rtTurnLeft();
				}
			}
			// Boosts the players based on their specific keys
			if (mode) { // Only if the game mode include boosts

				if (e.getKeyCode() == KeyEvent.VK_SLASH && blueBoosts < 3) { // Blue boost
					move.setBoost(2);
					blueBoosts++;
					players.resetBlueBoost();
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_Q && redBoosts < 3) { // Red boost
					move.setBoost(1);
					redBoosts++;
					players.resetRedBoost();
					repaint();
				}
			}
		}
		repaint();

	}

	// Unused methods
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
