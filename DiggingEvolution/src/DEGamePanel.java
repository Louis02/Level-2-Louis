import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class DEGamePanel extends JPanel {
	// Member Variables

	DEObjectManager om;

	static final int rows = 10;

	static final int cols = 9;

	DEObject[][] grid;

	static final int es = 0;

	static final int ab = 1;;

	static final int jef = 2;
	
	Random x = new Random();

	
	int[][] state = {{0,0,0,0,2,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
	};

	// Constructor
	public DEGamePanel() {

		om = new DEObjectManager();

		grid = new DEObject[rows][cols];

		state[x.nextInt(cols)][x.nextInt(rows - 1) + 1] = ab;
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {

			
				grid[r][c] = new DEObject(DERunner.width / cols, (DERunner.height - 20) / rows, r, c, state[r][c]);

				om.addDEObject(grid[r][c]);

			}
		}
		

	}

	// Methods
	public void paintComponent(Graphics g) {
		om.draw(g);
		repaint();
	}
}
