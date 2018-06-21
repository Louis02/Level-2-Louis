import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class DEObject {

	// Member Variables

	int x;

	int y;

	int row;

	int col;

	int width;

	int height;

	int state;

	static int colors;

	Random r;

	// Constructor
	public DEObject(int width, int height, int row, int col, int state) {
		x = width * col;

		y = height * row;

		r = new Random();

		this.width = width;

		this.height = height;

		this.state = state;

	}

	// Methods
	public void draw(Graphics g) {

		if (DEGamePanel.empty == state) {

			g.setColor(new Color(90, 44, 16));

			g.fillRect(x, y, width, height);

		} else if (DEGamePanel.ab == state) {
			if (colors == 1) {

				g.setColor(new Color(200, 100, 0));

			} else if (colors == 2) {

				g.setColor(new Color(90, 44, 0));

			}
			// else if(colors == 3) {
			// g.setColor(125, 75, 0);
			// }
			g.fillRect(x, y, width, height);

		}
		if (DEGamePanel.jef == state) {
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);

		}

	}

	public static void setVisible(boolean v) {
		abVisible = v;
	}

	public void state(int state) {
		this.state = state;
	}
}
