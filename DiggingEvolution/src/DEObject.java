import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class DEObject {
 
	// Member Variables

	int x;

	int y;

	int row;

	int col;

	int width;

	int height;

	int state;

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

			g.setColor(new Color(200, 100, 0));
			g.fillRect(x, y, width, height);

		}
		if (DEGamePanel.jef == state) {
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);
			
		}

	}

	public void state(int state) {
		this.state = state;
	}
}
