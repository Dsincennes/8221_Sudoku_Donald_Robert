import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainGame extends JFrame{

	private Toolbar toolbar;
	private GamePanel gamePanel;
	private OptionPanel options;
	private JFileChooser fileChooser;
	
	public MainGame() {
		super("Gonq Sudoku"); // title
		
		toolbar = new Toolbar();
		gamePanel = new GamePanel();
		options = new OptionPanel();
		fileChooser = new JFileChooser();
		
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		add(options, BorderLayout.EAST);
		
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
		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		

		
		// New Game .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainGame.this, "Do you want to start a new game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) { // user confirms new game
					//TODO add new game logic
					newGame();
					System.out.println("User Clicked New Game, through menu bar"); // logging
				}
				else
					System.out.println("User cancelled new game through menu bar");
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
		
		menuBar.add(fileMenu); // Adding File to the menu bar (makes it visible)
		fileMenu.add(newGame); // Adding new game to file drop down
		fileMenu.add(saveGame);
		fileMenu.add(loadGame);
		fileMenu.add(exitItem); // Adding Exit to file tab
		
		//Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F); // File ( f )
		exitItem.setMnemonic(KeyEvent.VK_X); // Exit ( x )
		newGame.setMnemonic(KeyEvent.VK_N); // New Game ( n )
		saveGame.setMnemonic(KeyEvent.VK_S);
		loadGame.setMnemonic(KeyEvent.VK_L);
		
		//Accelerators
		
		
		//Save
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainGame.this) == JFileChooser.APPROVE_OPTION) {
				}
			}
		});
		
		//Load
		
		
		return menuBar;
	}
	
	public void newGame() {
		add(gamePanel, BorderLayout.WEST);
		invalidate();
		validate();
		repaint();
	}


}
