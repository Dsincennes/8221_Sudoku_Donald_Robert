import javax.swing.SwingUtilities;

/**
 * Class hold method main, drives the program
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class Game{

	/**
	 * Method main, runs the application, allows for multithreading
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { // Multithreading, allows for multiple instances to be ran without closing previous

			public void run() {
				new MainGame();			
			}
			
		});
	}
	
}
