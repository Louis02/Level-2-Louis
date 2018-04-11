import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DERunner {

	// Member Variables

	final static int width = 400;

	final static int height = 500;

	// Main
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		DEGamePanel gp = new DEGamePanel();

		frame.add(gp);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		frame.setSize(width, height);

	}
}