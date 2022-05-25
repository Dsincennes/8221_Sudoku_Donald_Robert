import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * This class handles the splash screen for our sudoku application
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class GameSplash {

	JFrame frame;// Creating object of JFrame
	JLabel image = new JLabel(new ImageIcon("sudoku.png")); // Loading image

	/**
	 * Constructor that loads entire splash frame
	 */
	GameSplash()
	{
		frame = new JFrame(); // Create jframe object
		frame.getContentPane().setLayout(null);// set layout to null
		frame.setUndecorated(true);// no title bar
		frame.setSize(800, 600);// Frame size
		frame.setLocationRelativeTo(null);// frame location center
		frame.add(image); // add image to frame
		image.setSize(800,600); // image size
		frame.setVisible(true);// show frame		
		new ResourceLoader().execute(); // new object, needed to thread sleep, ( splash screen 5 second )
	}

	/**
	 * Class handles splash screen loading for 5 seconds only
	 * @author Donald Sincennes & Robert Jackson
	 *
	 */
	public class ResourceLoader extends SwingWorker<Object, Object> {

		/**
		 * Overridden method to handle showing splash screen for 5 seconds then hiding it.s
		 */
		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(5000); // wait 5 seconds
			frame.setVisible(false); // close splash
			return null;
		}
		
	}

}
