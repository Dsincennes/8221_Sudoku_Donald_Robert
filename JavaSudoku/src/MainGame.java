import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainGame extends JFrame{

	public MainGame() {
		super("Gonq Sudoku"); // title
		setLayout(new BorderLayout());

		setJMenuBar(createMenuBar()); // setting top menu bar
		setSize(800, 600); // sets default open size
		setMinimumSize(new Dimension(500, 400)); // minimum size user can use
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops process when user quits
		setVisible(true); // shows application
		
		
	}
	
	//Top Menu Bar
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		
		//Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F); // File ( f )
		exitItem.setMnemonic(KeyEvent.VK_X); // Exit ( x )
		newGame.setMnemonic(KeyEvent.VK_N); // New Game ( n )
		
		// New Game .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainGame.this, "Do you want to start a new game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) // user confirms new game
					//TODO add new game logic
					System.out.println("User pressed ok"); // logging
			}
		});
		
		
		// Exit... This function adds a listener that reacts to mouse click. creates a confirm dialog to confirm exit if pressed
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainGame.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) // Exits if confirmed
					System.exit(0);
			}
		});
		
		
		fileMenu.add(newGame);
		menuBar.add(fileMenu); // Adding File to the menu bar (makes it visible)
		fileMenu.add(exitItem); // Adding Exit to file tab
		
		return menuBar;
	}


}
