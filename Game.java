import javax.swing.SwingUtilities;

public class Game{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { // Multithreading, allows for multiple instances to be ran without closing previous

			public void run() {
				new MainGame();			
			}
			
		});
	}
	
}
