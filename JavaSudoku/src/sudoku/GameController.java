package sudoku;

import java.awt.BorderLayout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;

/**
 * CET - CS Academic Level 4
 * 
 * 
 * File Name: GameController.java 
 * Assessment: Assignment 1.2
 * Student Name:  Donald Sincennes and Robert Jackson 
 * Student Number: 041011305 & 040627795
 * Course: CST8221 - Java Application Programming
 * 
 * @JavaVersion v13
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 */

/**
 * 
 * Class GameController
 * Purpose: To contain the main frame, and acts as a controller though the majority of the game.
 * 
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 *
 */
public class GameController extends JFrame {

	/**
	 * Root Frame
	 */
	private SudokuPanel gamePanel;
	/**
	 * options Object, buttons, difficulty, textarea
	 */
	private OptionPanel options;
	/**
	 * Future use, Save function
	 */
	private JFileChooser fileChooser;
	/**
	 * Buttons for input
	 */
	private NumberInputPanel inputChoices;

	/**
	 * Default constructor: Initializes our variables, as well as sets the attributes of our JFrame.
	 */
	public GameController() {
		
		super("Donald Sincennes & Robert Jackson Sudoku"); // title

		options = new OptionPanel();
		fileChooser = new JFileChooser();

		setLayout(new BorderLayout());
		
		add(options, BorderLayout.EAST);
		
		setJMenuBar(createMenuBar()); // Sets Menu bar.
		setSize(800, 600); // Sets window size.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Enables the close operation on the frame.
		setLocationRelativeTo(null);// Setting location to the center of screen
		setVisible(true); // shows application
		
		SwingUtilities.invokeLater(() -> { new GameSplash(); }); // Invokes splash screen.

	}

	/**
	 * Method name: createMenuBar.
	 * Purpose: To create a menu bar for the frame to use.
	 * 
	 * @author Donald Sincennes and Robert Jackson
	 * @return JMenuBar, Returns the created menu bar.
	 * @version 0.1
	 */
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu newGame = new JMenu("New Game");
		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem clear = new JMenuItem("Clear");
		
		JMenu design = new JMenu("Design");
		JMenuItem designTwo = new JMenuItem("Design 2x2");
		JMenuItem designThree = new JMenuItem("Design 3x3");
		
		JMenu play = new JMenu("Play");
		JMenuItem playTwo = new JMenuItem("Play 2x2");
		JMenuItem playThree = new JMenuItem("Play 3x3");
		

// * =============================================================================================================
// * Series of file menu systems action listeners being implemented.
// * =============================================================================================================


		/*
		 * Gives menu prompt for action, if OK is pressed it creates a new game with the selected dimension.
		 */
		playTwo.addActionListener(e -> {
			int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Play a new 2x2 Game?",
					"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
			
			if (action == JOptionPane.OK_OPTION) { // user confirms new game
				newGame(4, true);
				OptionPanel.appendText("Play 2x2");
			} else
				OptionPanel.appendText("Cancel Play 2x2");
		});

		/*
		 * Gives menu prompt for action, if OK is pressed it creates a new game with the selected dimension.
		 */
		playThree.addActionListener(e -> {
			int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Play a new 3x3 game?",
					"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
			if (action == JOptionPane.OK_OPTION) { // user confirms new game
				newGame(9, true);
				OptionPanel.appendText("Play 3x3");
			} else
				OptionPanel.appendText("Cancel Play 3x3");
		});

		/*
		 * Gives menu prompt for action, if OK is pressed it creates a new design game with the selected dimension.
		 */
		designTwo.addActionListener(e -> {
			
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Design a new 2x2 game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				
				if (action == JOptionPane.OK_OPTION) { // user confirms new game
					newGame(4, false);
					OptionPanel.appendText("Design 2x2");
				} else
					OptionPanel.appendText("Cancel Design 2x2");
		});

		/*
		 * Gives menu prompt for action, if OK is pressed it creates a new design game with the selected dimension.
		 */
		designThree.addActionListener(e -> {
			
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Design a new 3x3 game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				
				if (action == JOptionPane.OK_OPTION) { // user confirms new game
					newGame(9, false);
					OptionPanel.appendText("Design 3x3");
				} else
					OptionPanel.appendText("Cancel design 3x3");
			
		});

		/*
		 * Gives menu prompt for action, if OK is pressed it saves the presently active game/design
		 */
		saveGame.addActionListener(e -> {
			
				if (fileChooser.showSaveDialog(GameController.this) == JFileChooser.APPROVE_OPTION) {
					OptionPanel.appendText("Saved");
				} else {
					OptionPanel.appendText("Cancel Save");
				}
		});

		/*
		 * Gives menu prompt for action, if OK is pressed it loads a file loader, to select a game they wish to load.
		 */
		loadGame.addActionListener(e -> {
				if (fileChooser.showOpenDialog(GameController.this) == JFileChooser.APPROVE_OPTION) {
					OptionPanel.appendText("Load");
				} else {
					OptionPanel.appendText("Cancel Load");
				}
		});

		/*
		 * Gives menu prompt for action, if OK is pressed the game is closed.
		 */
		exitItem.addActionListener(e -> {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) // Exits if confirmed
					System.exit(0);
				else
					OptionPanel.appendText("Cancel Exit");
		});

// * =============================================================================================================
// * Adds menu items to their respective drop down menu.
// * =============================================================================================================
		menuBar.add(fileMenu); 
		
		fileMenu.add(newGame);
		fileMenu.add(saveGame);
		fileMenu.add(loadGame);
		fileMenu.add(exitItem);
		fileMenu.add(clear);	

		newGame.add(design);
		design.add(designTwo);
		design.add(designThree);
		
		newGame.add(play);
		play.add(playTwo);
		play.add(playThree);

		
		
// * =============================================================================================================
// * Disables un-implemented menu items.
// * =============================================================================================================
		
		saveGame.setEnabled(false);
		loadGame.setEnabled(false);
		play.setEnabled(false);
		clear.setEnabled(false);

		
// * =============================================================================================================
// * Sets manual nemonic's for keyboard shortcuts.
// * =============================================================================================================

		fileMenu.setMnemonic(KeyEvent.VK_F); // File ( f )
		exitItem.setMnemonic(KeyEvent.VK_X); // Exit ( x )
		newGame.setMnemonic(KeyEvent.VK_N); // New Game ( n )
		saveGame.setMnemonic(KeyEvent.VK_S);
		loadGame.setMnemonic(KeyEvent.VK_L);


		return menuBar;
	} // End of method createMenuBar.


	/**
	 * Method: newGame
	 * Purpose: To start a new game within the Sudoku game pane.
	 * @param dim of type int, used to set the dimensions of the grid being made.
	 * @param play of type boolean, used to set the flag for type of game state being made.
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 */
	private void newGame(int dim, boolean play) {
		if (gamePanel != null)
			this.remove(gamePanel);
		
		if (inputChoices != null) 
			this.remove(inputChoices);
		
		gamePanel = new SudokuPanel(dim, play);

		inputChoices = new NumberInputPanel(dim);

		
		add(gamePanel, BorderLayout.CENTER);
		add(inputChoices, BorderLayout.SOUTH);

		// Refresh pane.
		revalidate();
		repaint();
	} // End of method newGame.
	
	
	/**
	 * Class: GameSplash
	 * Purpose: An inner class that launches a new frame with a panel at the beginning of the game, duration five secounds.
	 * 
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 *
	 */
	private class GameSplash{

		/**
		 * frame of splash screen
		 */
		JFrame frame;
		/**
		 * image for splash
		 */
		JLabel image = new JLabel(new ImageIcon("sudoku.png"));

		/**
		 * Default constructor, sets up panel attributes.
		 */
		public GameSplash() {
			frame = new JFrame();
			frame.getContentPane().setLayout(null);
			frame.setUndecorated(true);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.add(image); 
			frame.setVisible(true);
			
			image.setSize(800, 600);
			
			
			SwingUtilities.invokeLater(() -> {
				try {
					Thread.sleep(5000);
					frame.setVisible(false);
				} catch (InterruptedException e) {
					
					System.err.println(e);
					
				}
			});
			
		} // End of constructor.
	} // End of class GameSplash.
} // End of class GameController
