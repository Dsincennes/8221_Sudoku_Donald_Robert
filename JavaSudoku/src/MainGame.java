import java.awt.BorderLayout;

import javax.swing.JFrame;
// private test conflict
public class MainGame extends JFrame{

	public MainGame() {
		super("Gonq Sudoku");
		setLayout(new BorderLayout());

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		System.out.println("Test Commit");
	}


}
