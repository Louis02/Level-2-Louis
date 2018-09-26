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

	private static final Color four = new Color(200, 85, 50);

	private static final Color three = new Color(119, 85, 80);

	private static final Color two = new Color(119, 85, 100);

	private static final Color one = new Color(119, 85, 115);

	

	private static final Color Invisible = new Color(119, 85, 34);

	// next

	private static final Color bottomState = new Color(119, 85, 34);

	private static final Color topState = new Color(200, 100, 100);

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

		if (DEGamePanel.emptyTop == state) {

			g.setColor(topState);

			g.fillRect(x, y, width, height);

		} else if (DEGamePanel.emptyBottom == state) {

			g.setColor(bottomState);

			g.fillRect(x, y, width, height);

		}

		else if (DEGamePanel.ab == state) {
			if (colors == 1) {

				g.setColor(one);

			} else if (colors == 2) {

				g.setColor(two);

			} else if (colors == 3) {

				g.setColor(three);

			} else if (colors == 4) {

				g.setColor(four);

			} else if (colors == 6) {

				g.setColor(Invisible);

			}
			g.fillRect(x, y, width, height);

		}
		if (DEGamePanel.jef == state) {
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);

		}

	}

	public static void setVisible(int v) {
		colors = v;
	}

	public void state(int state) {
		this.state = state;
	}
}
