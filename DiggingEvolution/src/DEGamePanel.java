import java.awt.Graphics;

import javax.swing.JPanel;

public class DEGamePanel extends JPanel {
	//Member Variables
	
	DEObjectManager om;
	
	DEObject obj;

	//Constructor
	public DEGamePanel() {
		om = new DEObjectManager();
		
		obj = new DEObject();
		
		om.addDEObject(obj
				);
	}
	
	//Method
	public void paintComponent(Graphics g) {
om.draw(g);
repaint();
	}
}
