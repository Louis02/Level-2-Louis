import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DEGamePanel extends JPanel implements KeyListener, ActionListener {
	// Member Variables

	DEObjectManager om;

	static final int rows = 10;

	static final int cols = 9;

	DEObject[][] grid;

	static final int emptyBottom = 0;

	static final int emptyTop = 4;

	static final int ab = 1;

	static final int jef = 2;

	static final int grass = 3;

	int jefCol = 4;

	int jefRow = 0;

	int count = 0;

	int abRow;

	int abCol;

	int score = 0;

	int lastScore = 0;

	JFrame f;

	int startingColor = 6;

	Timer timer;

	int limit = 13;

	static final int startState = 0;

	static final int gameState = 1;

	static final int endState = 2;

	static final int congratState = 3;

	int menuState = startState;

	static final int esc = 27;

	private static Image diggerGif;

	static BufferedImage skyPic;

	static BufferedImage grassPic;

	static BufferedImage dirtPic;

	static BufferedImage ApricotPic;

	static BufferedImage ChefPic;

	static BufferedImage cloudlessPic;

	int maxName = 15;

	int level = 1;

	static final int mult = 5;

	ArrayList<LeaderBoard> names = new ArrayList<LeaderBoard>();

	int[] scoreList;

	String user;

	String lastUser;

	Font titleFont = new Font("Ariel", Font.BOLD, 22);

	Font instructionFont = new Font("Ariel", Font.PLAIN, 18);

	Font loserFont = new Font("Ariel", Font.PLAIN, 50);

	int[] leaderBoard = new int[7];

	boolean activeUser = false;

	String username = "";
	Random r = new Random();

	final int[][] initState = { { 4, 4, 4, 4, 2, 4, 4, 4, 4 }, { 3, 3, 3, 3, 3, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	int[][] state = new int[rows][cols];

	public void restart() {
		username = "";
		om = new DEObjectManager();

		grid = new DEObject[rows][cols];

		start();
	}

	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	// Color setter
	public void colorSetter() {
		int dist = Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol);
		if (dist > 5) {
			dist = 6;
		}
		if (dist > startingColor) {

			DEObject.setVisible(5);
		} else {
			DEObject.setVisible(dist - 1);
		}

		System.out.println(level + " " + dist + " " + score + "     " + startingColor);

	}
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter
	// End of Color setter

	public void levelMult() {

		if (score >= (level * mult)) {
			level++;
			menuState = congratState;
			f.pack();
			f.setSize(DERunner.width, DERunner.height);
			startingColor = 5 - (score / 5);
		}
	}
	// Constructor

	public DEGamePanel(JFrame f) {
		this.f = f;
		om = new DEObjectManager();
		diggerGif = new ImageIcon(getClass().getResource("giphy.gif")).getImage();
		try {
			skyPic = ImageIO.read(this.getClass().getResourceAsStream("sky.png"));
			grassPic = ImageIO.read(this.getClass().getResourceAsStream("grass.png"));
			dirtPic = ImageIO.read(this.getClass().getResourceAsStream("dirt.png"));
			ApricotPic = ImageIO.read(this.getClass().getResourceAsStream("apricot.png"));
			ChefPic = ImageIO.read(this.getClass().getResourceAsStream("chef.png"));
			cloudlessPic = ImageIO.read(this.getClass().getResourceAsStream("cloudless.jpg"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer = new Timer(1000, this);

		grid = new DEObject[rows][cols];

		start();
		timer.start();

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
		colorSetter();
	}

	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	// Methods
	public void paintComponent(Graphics g) {
		if (menuState == gameState) {

			om.draw(g);

			g.setColor(Color.BLUE);
			g.fillRect(0, DERunner.height - 20, DERunner.width, DERunner.blockSize);
			g.setColor(Color.red);
			g.drawString("Your Level is " + (level), 10, DERunner.height + 6);
			g.drawString("Your Score is " + (score), 295, DERunner.height + 6);

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
			g.drawString("Your score is " + ((Integer) lastScore).toString(), 135, 85);
			// System.out.println(lastUser);
			// System.out.println(user);

			// rect
			g.drawRect(100, 125, 200, 229);
			// line
			g.drawRect(250, 125, 1, 229);
			g.drawRect(100, 148, 200, 1);
			g.drawRect(100, 171, 200, 1);
			g.drawRect(100, 194, 200, 1);
			g.drawRect(100, 217, 200, 1);
			g.drawRect(100, 240, 200, 1);
			g.drawRect(100, 263, 200, 1);
			g.drawRect(100, 286, 200, 1);
			g.drawRect(100, 309, 200, 1);
			g.drawRect(100, 332, 200, 1);

			Collections.sort(names);
			int numMax = names.size();
			if (numMax > 10) {
				numMax = 10;
			}

			for (int i = 0; i < numMax; i++) {

				if (user != null && user.length() > maxName) {

					g.drawString(names.get(i).getName().substring(0, maxName), 110, 155);
					g.drawString("" + names.get(i).getScore(), 110, 200);

				}

				else {

					g.drawString(names.get(i).getName(), 110, 145 + (i * 23));
					g.drawString("" + names.get(i).getScore(), 260, 145 + (i * 23));

				}
				score = 0;
				level = 1;
				startingColor = 6;
			}
			if (user == null) {
				user = lastUser;
			}
			g.setFont(titleFont);
			g.drawString("Press Enter to Restart", 78, 400);
			g.drawString("Better Luck Next Time", 78, 450);

		} else if (menuState == congratState) {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, DERunner.width, DERunner.height);
			g.setColor(Color.BLACK);
			g.setFont(titleFont);
			g.drawString("Congrats on beating level " + (level - 1), 45, 30);
			g.setFont(instructionFont);
			g.drawString("Press enter to go onto level " + level, 80, 400);
			g.drawImage(diggerGif, 75, 85, 250, 250, null);

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
			else if (!activeUser || activeUser) {
				// enter
				if (e.getKeyCode() == 10) {
					menuState = gameState;

					f.pack();
					f.setSize(DERunner.width, DERunner.height + DERunner.blockSize);
				}
				}
			else if(!activeUser) {
				// space
				 if (e.getKeyCode() == 32) {
					JOptionPane.showMessageDialog(null,
							"Press Enter to start the game\nUse the arrow keys to move all directions\nThe object of the game is to search around the grid\nUntil a faint green square shows up and you must obtain it\nYou only have 13 moves to complete it");
				}
			}
		
			repaint();
		}
	
		if (menuState == endState) {
			if (user != null) {
				lastUser = user;
			}
			if (e.getKeyCode() == 10) {
				System.out.println(lastUser);
				score = 0;
				menuState = startState;
				restart();
			}
		}
		if (menuState == congratState) {
			if (e.getKeyCode() == 10) {
				menuState = gameState;
				f.pack();
				f.setSize(DERunner.width, DERunner.height + DERunner.blockSize);
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
					levelMult();
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
					levelMult();
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
					levelMult();
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
					levelMult();
					System.out.println("found him");
					score++;
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;

				count++;
			}
		}
		colorSetter();

		System.out.println("        " + count);
		if (count > limit) {
			count = 0;
			System.out.println("ending");
			menuState = endState;
			f.pack();
			f.setSize(DERunner.width, DERunner.height);
			lastScore = score;
			Collections.sort(names);
			if (names.size() == 0) {
				names.add(new LeaderBoard(user, score));
			} else {
				System.out.println(names.size() + "        " + user);
				for (int i =0; i<names.size(); i++) {

					if (!names.get(i).getName().equals(user)) {
						System.out.println(user);
						names.add(new LeaderBoard(user, score));
					}

					else if (names.get(i).score < score) {
						names.get(i).score = score;

					}
				}

			}
		} else if (jefRow == abRow && jefCol == abCol) {
			System.out.println("found it");

			om.restart();
			start();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		DEObject.clouds();

	}
}
