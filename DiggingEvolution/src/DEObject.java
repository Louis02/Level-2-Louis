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

	private static final Color visible = new Color(0, 150, 0);

	private static final Color secondClosest = new Color(0, 75, 0);

	private static final Color farthestAway = new Color(0, 50, 0);

	private static final Color Invisible = new Color(90, 44, 16);

	private static final Color bottomState = new Color(90, 44, 16);

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

		}
		 else if (DEGamePanel.emptyBottom == state) {

			g.setColor(bottomState);

			g.fillRect(x, y, width, height);

		}

		else if (DEGamePanel.ab == state) {
			if (colors == 1) {

				g.setColor(visible);

			} else if (colors == 2) {

				g.setColor(secondClosest);

			} else if (colors == 3) {

				g.setColor(farthestAway);

			} else if (colors == 4) {
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
