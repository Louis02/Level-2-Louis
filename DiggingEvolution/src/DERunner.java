import javax.swing.JFrame;

class DERunner {

	// Member Variables
	JFrame frame;
	final static int numCols = 9;
	final static int numWidth = 45;
	final static int width = numCols * numWidth;
final static int numRows = 10;

	final static int height = numRows*numWidth;

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

		frame.setSize(width, height + numWidth);
		frame.setResizable(false);

	}

	public void startNewGame() {
		frame.dispose();
		new DERunner();

	}
}