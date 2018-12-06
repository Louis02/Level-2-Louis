import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	static int r;

	static int colors;
	
	// private static final Color invisible = new Color(119, 85, 34);

	// next

	// private static final Color topState = new Color(200, 100, 100);

	static final Float[] opaque = { 1f, .7f, .4f, .2f, 0.12f };

	// Constructor
	public DEObject(int width, int height, int row, int col, int state) {
		x = width * col;

		y = height * row;

		r = new Random().nextInt(2);

		this.width = width;

		this.height = height;

		this.state = state;

	}

	// Methods
	public void draw(Graphics g) {

		if (DEGamePanel.emptyTop == state) {
			
			if (r  == 1) {
				g.drawImage(DEGamePanel.skyPic, x, y, width, height, null);
			} else if (r == 0) {
				g.drawImage(DEGamePanel.cloudlessPic, x, y, width, height, null);
			}
		} else if (DEGamePanel.emptyBottom == state) {

			g.drawImage(DEGamePanel.dirtPic, x, y, width, height, null);

		}

		else if (DEGamePanel.ab == state) {

			// System.out.println(" " + colors + " " + colorList.length + " ");

			if (colors < opaque.length) {
				// g.setColor(colorList[colors]);
				g.drawImage(DEGamePanel.dirtPic, x, y, width, height, null);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setComposite(AlphaComposite.SrcOver.derive(opaque[colors]));
				g2d.drawImage(DEGamePanel.ApricotPic, x, y, width, height, null);
				g2d.dispose();
			} else {
				g.drawImage(DEGamePanel.dirtPic, x, y, width, height, null);

			}
		} else if (DEGamePanel.jef == state) {
			if (y == 0) {
				g.drawImage(DEGamePanel.skyPic, x, y, width, height, null);
			}

			else {
				g.drawImage(DEGamePanel.dirtPic, x, y, width, height, null);
			}

			g.drawImage(DEGamePanel.ChefPic, x, y, width, height, null);

		} else if (DEGamePanel.grass == state) {
			g.drawImage(DEGamePanel.grassPic, x, y, width, height, null);
		}

	}
	
	public static void clouds () {
		r=new Random().nextInt(2);
	}

	public static void setVisible(int v) {
		colors = v;
	}

	public void state(int state) {
		this.state = state;
	}
}
