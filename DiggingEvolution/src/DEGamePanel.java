import java.awt.Graphics;

import javax.swing.JPanel;

public class DEGamePanel extends JPanel {
	DEObjectManager om;
	DEObject obj;

	public DEGamePanel() {
		om = new DEObjectManager();
		obj = new DEObject();
	}
	public void paintComponent(Graphics g) {
		
	}
}
