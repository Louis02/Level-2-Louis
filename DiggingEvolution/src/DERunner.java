import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DERunner {

	int width;
	int height;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DEGamePanel gp = new DEGamePanel();
		frame.add(gp);
	}
}