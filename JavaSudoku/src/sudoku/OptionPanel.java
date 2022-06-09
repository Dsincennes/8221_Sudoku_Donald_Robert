package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * CET - CS Academic Level 4
 * 
 * 
 * File Name: OptionPanel.java
 * Assessment: Assignment 1.2 
 * Student Name: Donald Sincennes and Robert Jackson 
 * Student Number: 041011305 & 040627795
 * Course: CST8221 -
 * Java Application Programming
 * 
 * @JavaVersion v13
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 */

/**
 * Class: Option Panel.
 * Purpose: This is a panel that contains our selection of sudoku field options. It allows for selecting of a number, 
 * then afterwards you can select a position on the grid.
 * 
 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
 */
public class OptionPanel extends JPanel {

	/**
	 * Combo Box for game difficulty
	 */
	private JComboBox<String> diff;
	/**
	 * Label for Difficulty combo box
	 */
	private JLabel diffLabel;
	/**
	 * Scroll pane for text area
	 */
	private JScrollPane scroll;
	/**
	 * button for about/help
	 */
	private JButton jb;
	
	/**
	 * JtextArea for logging
	 */
	private static JTextArea textArea; // Will be changed to purely private when we establish an MVC model.

	/**
	 * Method: OptionPanel default constructor.
	 * Purpose: Default constructor for class Option Panel, this will set the attributes for the panel.
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 */
	public OptionPanel() {
		
		
		// * =============================================================================================================
		// * Setting up base layout attributes as well as initializing our J objects.
		// * =============================================================================================================
		diffLabel = new JLabel("Difficulty");
		
		textArea = new JTextArea();
		scroll = new JScrollPane(textArea);
		
		jb = new JButton(new ImageIcon("sudoku_logo.png"));
		jb.setBounds(50, 50, 90, 20);

		diff = new JComboBox<String>(new String[]{ "easy", "medium", "hard" });
		diff.setBounds(50, 50, 90, 20);
		
		// * =============================================================================================================
		// * Setting up constraints of the text area.
		// * =============================================================================================================
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Verdana", Font.BOLD, 10));
		textArea.setEditable(false);
		
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		


		setPreferredSize(new Dimension(200, 50));
		setBackground(Color.LIGHT_GRAY);
		layoutComponents();

	} // End of default Constructor.

	/**
	 * helper method for placement of all option components
	 */
	private void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// * =============================================================================================================
		// * First Row of items in the Gridbag.
		// * =============================================================================================================
		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(diffLabel, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(diff, gc);

		// * =============================================================================================================
		// * Second Row of items in the Gridbag.
		// * =============================================================================================================

		gc.gridy++;

		gc.weightx = 0;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 5, 5, 5);
		gc.fill = GridBagConstraints.BOTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;

		add(scroll, gc);

		// * =============================================================================================================
		// * Third Row of items in the Gridbag.
		// * =============================================================================================================

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 3, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(jb, gc);

		// Listener for About Button
		jb.addActionListener(e ->  {
				SwingUtilities.invokeLater(() ->{
					new AboutPanel();	
				});
		});

	} // End of method layoutComponents.

	/**
	 * Method name: appendText.
	 * Purpose: To add to our text area in the option pane, to reflect an action being taken.
	 * 
	 * @param text of type String, What we wish to append to our text field.
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 */
	public static void appendText(String text) {
		try {
			textArea.append(text);
			textArea.append("\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	} // End of method appendText.

	
	/**
	 * Class Name: AboutPanel
	 * Purpose: This is an inner class containing a frame and panel elements, this is an about frame that populates when its thread is called.
	 * 
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 *
	 */
	public class AboutPanel {
		
		/**
		 * Frame for about and help panel
		 */
		JFrame frame;
		/**
		 * Label or Title for the about frame
		 */
		JLabel about = new JLabel("<HTML><U><B>How To Play</B></U></HTML>", SwingConstants.CENTER);
		/**
		 * Image for about panel
		 */
		JLabel image = new JLabel(new ImageIcon("sudoku_about.png"));
		/**
		 * Close Button
		 */
		JButton close = new JButton("Close");


		/**
		 * Method Name: AboutPanel.
		 * Purpose: This is the default constructor for class AboutPanel, it initializes and sets the property of the frame.
		 * 
		 * @author Donald Sincennes and Robert Jackson
	 	 * @version 0.1
		 */
		public AboutPanel() {
			frame = new JFrame("About Page");
			frame.getContentPane().setLayout(new BorderLayout());
			frame.getContentPane().setBackground(Color.lightGray);
			
			frame.setUndecorated(true);// no title bar
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);// frame location center
			
			frame.add(about, BorderLayout.NORTH);
			frame.add(close, BorderLayout.SOUTH);
			frame.add(image, BorderLayout.CENTER); // add image to frame
			
			image.setSize(600, 400);
			close.setPreferredSize(new Dimension(50, 50));
			about.setFont(new Font("Serif", Font.BOLD, 36));

			frame.setVisible(true);
			
			// Ends frame thread.
			close.addActionListener( e -> { frame.dispose(); });
		} // End of default constructor.
	} // End of class AboutPanel.
} // End of class OptionPanel.
