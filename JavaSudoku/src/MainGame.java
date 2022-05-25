import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Class contains main frame, can control most of the game features through the menu bar
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class MainGame extends JFrame{

	private GamePanel gamePanel;
	private OptionPanel options;
	private JFileChooser fileChooser;
	private GameSplash splash;
	
	/**
	 * Constructor opens the main frame, sets size layout and main functions
	 */
	public MainGame() {
		super("Gonq Sudoku"); // title
		
		gamePanel = new GamePanel();
		options = new OptionPanel();
		fileChooser = new JFileChooser();
		
		setLayout(new BorderLayout());
		add(options, BorderLayout.EAST);
		
		setJMenuBar(createMenuBar()); // setting top menu bar
		setSize(800, 600); // sets default open size
		setMinimumSize(new Dimension(500, 400)); // minimum size user can use
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops process when user quits
		setLocationRelativeTo(null);// Setting location to the center of screen
		setVisible(true); // shows application
		splash = new GameSplash();
	}
	
	/**
	 * Creates application menu bar
	 * @return menu bar is returned
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu newGame = new JMenu("New Game");
		JMenuItem design = new JMenuItem("Design");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		// Play .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainGame.this, "Do you want to Play a new game?",
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
		
		// Design .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		design.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainGame.this, "Do you want to Design a new game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) { // user confirms new game
					//TODO add new game logic
					newGame();
					System.out.println("User Clicked Design, through menu bar"); // logging
				}
				else
					System.out.println("User cancelled Design through menu bar");
			}
		});
		
		//Save
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainGame.this) == JFileChooser.APPROVE_OPTION) {
				}
			}
		});
		
		//Load
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainGame.this) == JFileChooser.APPROVE_OPTION) {
				}
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
		newGame.add(design);
		newGame.add(play);
		
		//Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F); // File ( f )
		exitItem.setMnemonic(KeyEvent.VK_X); // Exit ( x )
		newGame.setMnemonic(KeyEvent.VK_N); // New Game ( n )
		saveGame.setMnemonic(KeyEvent.VK_S);
		loadGame.setMnemonic(KeyEvent.VK_L);
		
		//Accelerators
		
		return menuBar;
	}
	
	/**
	 * New game function. starts a new game in the application
	 */
	public void newGame() {
		add(gamePanel, BorderLayout.CENTER);
		invalidate();
		validate();
		repaint();
	}

}
