import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class GameSplash {

	JFrame frame;// Creating object of JFrame
	JLabel image = new JLabel(new ImageIcon("sudoku.png"));

	GameSplash()// Creating constructor of the class
	{
		frame = new JFrame();
		frame.getContentPane().setLayout(null);// setting layout to null
		frame.setUndecorated(true);// Turning off Title bar
		frame.setSize(800, 600);// Setting size
		frame.setLocationRelativeTo(null);// Setting location to the center of screen
		frame.add(image);
		image.setSize(800, 600);
		frame.setVisible(true);// setting visibility
		System.out.println("in constructor");
		
		new ResourceLoader().execute();;

	}

	public class ResourceLoader extends SwingWorker<Object, Object> {

		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(5000);
			frame.setVisible(false);
			return null;
		}
		
	}

}
