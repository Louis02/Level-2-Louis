import java.awt.Graphics;

import javax.swing.JPanel;

public class DEGamePanel extends JPanel {
	// Member Variables

	DEObjectManager om;

	static final int rows = 10;

	static final int cols = 8;

	DEObject[][] grid;

	// Constructor
	public DEGamePanel() {

		om = new DEObjectManager();

		grid = new DEObject[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				
				grid[r][c] = new DEObject(DERunner.width / cols, (DERunner.height - 20 )/ rows , r, c);

				om.addDEObject(grid[r][c]);

			}
		}

	}

	// Method
	public void paintComponent(Graphics g) {
		om.draw(g);
		repaint();
	}
}
