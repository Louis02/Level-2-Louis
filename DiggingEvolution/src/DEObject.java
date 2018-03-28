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

	Random r;

	// Constructor
	public DEObject() {
		x = 0;

		y = 0;

		width = 50;

		height = 50;

		r = new Random();

	}

	// Methods
	public void draw(Graphics g) {

		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		
		g.fillRect(x, y, width, height);
		
		g.drawRect(x, y, width, height);

	}
}
