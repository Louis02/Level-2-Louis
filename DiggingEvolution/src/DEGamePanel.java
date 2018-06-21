import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

public class DEGamePanel extends JPanel implements KeyListener {
	// Member Variables

	DEObjectManager om;

	static final int rows = 10;

	static final int cols = 9;

	DEObject[][] grid;

	static final int empty = 0;

	static final int ab = 1;

	static final int jef = 2;

	int jefCol = 4;

	int jefRow = 0;

	int count = 0;

	int abRow;

	int abCol;
	Random r = new Random();

	final int[][] initState = { { 0, 0, 0, 0, 2, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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
		om.draw(g);
		// repaint();
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

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (jefCol < (cols - 1)) {

				state[jefRow][jefCol] = empty;

				grid[jefRow][jefCol].state(empty);

				jefCol++;

				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (jefCol > 0) {

				state[jefRow][jefCol] = empty;

				grid[jefRow][jefCol].state(empty);

				jefCol--;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			if (jefRow < (rows - 1)) {

				state[jefRow][jefCol] = empty;

				grid[jefRow][jefCol].state(empty);

				jefRow++;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {

			if (jefRow > 0) {
				state[jefRow][jefCol] = empty;

				grid[jefRow][jefCol].state(empty);

				jefRow--;
				if (state[jefRow][jefCol] == ab) {
					System.out.println("found him");
				}

				grid[jefRow][jefCol].state(jef);

				state[jefRow][jefCol] = jef;
				count++;
			}
		}
		System.out.println("        " + count);
		if (count > 13) {
			count = 0;
			System.out.println("aaaaaaaaaaa");
			om.restart();
			start();

		} else if (jefRow == abRow && jefCol == abCol) {
			System.out.println("tttttttttttt");

			om.restart();
			start();
		}
		if (Math.abs(jefRow - abRow) + Math.abs(jefCol - abCol) < 4) {
			DEObject.setVisible(true);
		} else {
			DEObject.setVisible(false);
		}

		repaint();

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
