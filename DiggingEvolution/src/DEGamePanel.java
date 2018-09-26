import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DEGamePanel extends JPanel implements KeyListener {
	// Member Variables

	DEObjectManager om;

	static final int rows = 10;

	static final int cols = 9;

	DEObject[][] grid;

	static final int emptyBottom = 0;

	static final int emptyTop = 4;

	static final int ab = 1;

	static final int jef = 2;

	int jefCol = 4;

	int jefRow = 0;

	int count = 0;

	int abRow;

	int abCol;

	int score = 0;

	static final int startState = 0;

	static final int gameState = 1;

	static final int endState = 2;

	int menuState = startState;

	static final int esc = 27;

	int maxName = 15;

	String user;

	Font titleFont = new Font("Ariel", Font.BOLD, 22);

	Font instructionFont = new Font("Ariel", Font.PLAIN, 18);

	Font loserFont = new Font("Ariel", Font.PLAIN, 50);

	int[] leaderBoard = new int[7];

	boolean activeUser = false;

	String username = "";
	Random r = new Random();

	final int[][] initState = { { 4, 4, 4, 4, 2, 4, 4, 4, 4 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	int[][] state = new int[rows][cols];

	// Constructor
	public DEGamePanel() {

		om = new DEObjectManager();

		grid = new DEObject[rows][cols];

		start();

	}

	public void start() {

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				state[r][c] = initState[r][c];
			}
		}
		count = 0;
		abRow = r.nextInt(rows - 1) + 1;
		abCol = r.nextInt(cols);
		state[abRow][abCol] = ab;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {

				grid[r][c] = new DEObject(DERunner.width / cols, (DERunner.height - 20) / rows, r, c, state[r][c]);
				if (state[r][c] == jef) {
					jefRow = r;
					jefCol = c;
				}

				om.addDEObject(grid[r][c]);

			}
		}

	}

	// Methods
	public void paintComponent(Graphics g) {
		if (menuState == gameState) {
			om.draw(g);

		} else if (menuState == startState) {
			g.setColor(Color.blue);

			g.fillRect(0, 0, DERunner.width, DERunner.height);

			g.setColor(Color.white);

			g.setFont(titleFont);
			g.drawString("Welcome to Digging Evolution", 30, 50);

			g.setFont(instructionFont);
			g.drawString("Press 'Space' for Instructions", 78, 130);
			g.drawString("Press 'Enter' to Start the Game", 70, 210);
			g.drawString("Enter Username by pressing 'Esc'", 60, 290);

			g.setFont(titleFont);
			g.drawString("Lets Get Started", 110, 410);

			g.setFont(instructionFont);
			g.drawRect(30, 330, 340, 25);
			g.drawString(username, 33, 349);

		} else if (menuState == endState) {
			g.setColor(Color.YELLOW);

			g.fillRect(0, 0, DERunner.width, DERunner.height);

			g.setColor(Color.red);

			g.setFont(loserFont);
			g.drawString("You Lost", 90, 50);

			g.setFont(instructionFont);
			g.drawString("Your score is " + ((Integer) score).toString(), 135, 90);
			// rect
			g.drawRect(100, 125, 200, 225);
			// line
			g.drawRect(250, 125, 1, 225);

			if (user.length() > maxName) {

				g.drawString(user.substring(0, maxName), 110, 155);

				user = " ";
			} else {
				g.drawString(user, 110, 155);
			}

			g.setFont(titleFont);
			g.drawString("Press Enter to Restart", 78, 400);
			g.drawString("Better Luck Next Time", 78, 450);
		}
		repaint();
	}

	@Override
	// all
	// of
	// this
	// stuff
	// is
	// for
	// jeffery
	// the
	// macho
	// man
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (menuState == gameState) {
			gameStateKeys(e);

		}

		else if (menuState == startState) {

			int key = e.getKeyCode();

			if (key == esc) {

				activeUser = !activeUser;

			} else if (activeUser) {
				user = username += ((Character) e.getKeyChar()).toString();

			}

			// ignore
			// makes not user name things work
			else if (!activeUser) {
				// enter
				if (e.getKeyCode() == 10) {
					menuState = gameState;
				}
				// space
				else if (e.getKeyCode() == 32) {
					JOptionPane.showMessageDialog(null,
							"Press Enter to start the game\nUse the arrow keys to move all directions\nThe object of the game is to search around the grid\nUntil a faint green square shows up and you must obtain it\nYou only have 13 moves to complete it");
				}
			}

			repaint();
		}
		if (menuState == endState) {
			if (e.getKeyCode() == 10) {
				menuState = startState;
			}
		}

	}

	private void gameStateKeys(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (jefCol < (cols - 1)) {

				state[jefRow][jefCol] = emptyBottom;

				grid[jefRow][jefCol].state(emptyBottom);

				jefCol++;

				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
					score++;
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (jefCol > 0) {

				state[jefRow][jefCol] = emptyBottom;

				grid[jefRow][jefCol].state(emptyBottom);

				jefCol--;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
					score++;
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			if (jefRow < (rows - 1)) {

				state[jefRow][jefCol] = emptyBottom;

				grid[jefRow][jefCol].state(emptyBottom);

				jefRow++;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
					score++;
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {

			if (jefRow > 0) {
				state[jefRow][jefCol] = emptyBottom;

				grid[jefRow][jefCol].state(emptyBottom);

				jefRow--;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
					score++;
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}
		System.out.println("        " + count);
		if (count > 13) {
			count = 0;
			System.out.println("ending");
			menuState = endState;

		} else if (jefRow == abRow && jefCol == abCol) {
			System.out.println("found it");

			om.restart();
			start();
		}



			if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) >= 5) {
				DEObject.setVisible(6);
			} else if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) == 1) {
				DEObject.setVisible(1);
			}

			else if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) == 2) {
				DEObject.setVisible(2);
			}

			else if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) == 3) {
				DEObject.setVisible(3);
			}

			else if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) == 4) {
				DEObject.setVisible(4);
			}

			else if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) <= 5) {
				DEObject.setVisible(5);
			}
		

		for (int i = 0; i < cols; i++) {

			if (state[0][i] != jef) {
				state[0][i] = emptyTop;
				grid[0][i].state(emptyTop);
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
