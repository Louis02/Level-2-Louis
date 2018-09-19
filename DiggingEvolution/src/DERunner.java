import javax.swing.JFrame;

class DERunner {

	// Member Variables
JFrame frame;
	final static int width = 400;

	final static int height = 500;

	DEGamePanel gp;

	// Main
	public static void main(String[] args) {
		new DERunner();

	}

	DERunner() {
		frame = new JFrame();
		gp = new DEGamePanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(gp);
		frame.setVisible(true);

		frame.addKeyListener(gp);

		frame.setSize(width, height);
		frame.setResizable(false);

	}

	public void startNewGame() {
		frame.dispose();
		new DERunner();

	}
}