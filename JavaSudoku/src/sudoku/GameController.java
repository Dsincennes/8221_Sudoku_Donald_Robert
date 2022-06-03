package sudoku;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * Class contains main frame, can control most of the game features through the menu bar
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class GameController extends JFrame{

	private SudokuPanel gamePanel;
	private OptionPanel options;
	private JFileChooser fileChooser;
	private GameSplash splash;
	private NumberInputPanel options2;
	
	/**
	 * Constructor opens the main frame, sets size layout and main functions
	 */
	public GameController() {
		super("Donald Sincennes & Robert Jackson Sudoku"); // title
		
		gamePanel = new SudokuPanel(9, false);
		options = new OptionPanel();
		options2 = new NumberInputPanel(9);
		fileChooser = new JFileChooser();
		
		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER); // remove maybe?
		add(options, BorderLayout.EAST);
		add(options2, BorderLayout.SOUTH);
		setJMenuBar(createMenuBar()); // setting top menu bar
		setSize(800, 600); // sets default open size
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops process when user quits
		setLocationRelativeTo(null);// Setting location to the center of screen
		setVisible(true); // shows application
//		splash = new GameSplash(); // shows splash
	}
	
	/**
	 * Creates application menu bar
	 * @return menu bar is returned
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu newGame = new JMenu("New Game");
		JMenu design = new JMenu("Design");
		JMenu play = new JMenu("Play");
		JMenuItem designTwo = new JMenuItem("Design 2x2");
		JMenuItem designThree = new JMenuItem("Design 3x3");
		JMenuItem playTwo = new JMenuItem("Play 2x2");
		JMenuItem playThree = new JMenuItem("Play 3x3");
		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem clear = new JMenuItem("Clear");
		
		// Play .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		playTwo.addActionListener(e -> {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Play a new 2x2 Game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) { // user confirms new game
					newGame(4, true);
					OptionPanel.appendText("Play 2x2");
				}
				else
					OptionPanel.appendText("Cancel Play 2x2");
		});
		
		// Play .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		playThree.addActionListener(e -> {
			int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Play a new 3x3 game?",
					"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
			if(action == JOptionPane.OK_OPTION) { // user confirms new game
				newGame(9, true);
				OptionPanel.appendText("Play 3x3");
			}
			else
				OptionPanel.appendText("Cancel Play 3x3");
		});
		
		// Design .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		designTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Design a new 2x2 game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) { // user confirms new game
					newGame(4, false);
					OptionPanel.appendText("Design 2x2");
				}
				else
					OptionPanel.appendText("Cancel Design 2x2");
			}
		});
		
		// Design .... Beginning implementation of new game. Will eventually clear and start new fresh puzzle
		designThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to Design a new 3x3 game?",
						"Confirm New Game", JOptionPane.OK_CANCEL_OPTION); // User pressed Cancel, nothing happens
				if(action == JOptionPane.OK_OPTION) { // user confirms new game
					newGame(9, false);
					OptionPanel.appendText("Design 3x3");
				}
				else
					OptionPanel.appendText("Cancel design 3x3");
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you want to clear puzzle","confirm clear", JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
//					newGame(4);
//					revalidate();
//					repaint();
					//TODO add logic
					OptionPanel.appendText("Cleared");
				}
				else
					OptionPanel.appendText("Cancel Clear");
			}
		});
		
		//Save
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(GameController.this) == JFileChooser.APPROVE_OPTION) {
					OptionPanel.appendText("Saved");
				}
				else {
					OptionPanel.appendText("Cancel Save");
				}
			}
		});
		
		//Load
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(GameController.this) == JFileChooser.APPROVE_OPTION) {
					OptionPanel.appendText("Load");
				}
				else {
					OptionPanel.appendText("Cancel Load");
				}
			}
		});
		
		// Exit... This function adds a listener that reacts to mouse click. creates a confirm dialog to confirm exit if pressed
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(GameController.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) // Exits if confirmed
					System.exit(0);
				else
					OptionPanel.appendText("Cancel Exit");
			}
		});
		

		
		menuBar.add(fileMenu); // Adding File to the menu bar (makes it visible)
		fileMenu.add(newGame); // Adding new game to file drop down
		fileMenu.add(saveGame);
		fileMenu.add(loadGame);
		fileMenu.add(exitItem); // Adding Exit to file tab
		fileMenu.add(clear);
		newGame.add(design);
		newGame.add(play);
		play.add(playTwo);
		play.add(playThree);
		design.add(designTwo);
		design.add(designThree);
		
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
	public void newGame(int dim, boolean play) {
		if(gamePanel != null)
			this.remove(gamePanel);  
        gamePanel = new SudokuPanel(dim, play);
		
        this.remove(options2);
        options2 = new NumberInputPanel(dim);
        
        add(gamePanel, BorderLayout.CENTER);
        add(options2, BorderLayout.SOUTH);
        
        revalidate();
        repaint();
	}
	
	public class GameSplash extends JPanel	{

		JFrame frame;// Creating object of JFrame
		JLabel image = new JLabel(new ImageIcon("sudoku.png")); // Loading image

		/**
		 * Constructor that loads entire splash frame
		 */
		public GameSplash()
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

}
