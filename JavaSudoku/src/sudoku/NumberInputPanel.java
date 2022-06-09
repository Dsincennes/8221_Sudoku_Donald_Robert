package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import javax.swing.border.TitledBorder;

/**
 * CET - CS Academic Level 4
 * 
 * 
 * File Name: NumberInputPanel.java 
 * Assessment: Assignment 1.2
 * Student Name:  Donald Sincennes & Robert Jackson 
 * Student Number: 041011305 & 040627795
 * Course: CST8221 - Java Application Programming
 * 
 * @JavaVersion v13
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 */

/**
 * Class: NumberInputPanel.
 * Purpose: This is a panel that contains our selection of sudoku field options. It allows for selecting of a number, 
 * then afterwards you can select a position on the grid.
 * 
 * @author Donald Sincennes and Robert Jackson
 *
 */
public class NumberInputPanel extends JPanel {

	/**
	 * Buttons for input
	 */
	private JToggleButton[] inputs;
	/**
	 * button group for each button to be added to
	 */
	private ButtonGroup bg;
	/**
	 * panel for buttons
	 */
	private JPanel pnlNumPanel;
	/**
	 * help with alignment
	 */
	private JPanel pnlAlign;
	/**
	 * inner panel for individual numbers
	 */
	private JPanel pnlNumbers;
	
	/**
	 * to keep track of current selection
	 */
	static char currentSelection; // This will be changed to private after we move to an MVC model.

	/**
	 * Method: Default constructor
	 * Purpose: To set the attributes of the number input panel.
	 * @param buttonNum of type int, this is the number of buttons that will appear on the panel.
	 */
	public NumberInputPanel(int buttonNum) {
		
		setLayout(new BorderLayout());
		currentSelection = '1';
		
		// * =============================================================================================================
		// * Setting up base layout attributes as well as initializing our panels.
		// * =============================================================================================================
		pnlAlign = new JPanel();
		pnlNumPanel = new JPanel();
		pnlNumbers = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
		pnlNumPanel.setLayout(new BoxLayout(pnlNumPanel, BoxLayout.PAGE_AXIS));
		
		
		bg = new ButtonGroup();

		inputs = new JToggleButton[buttonNum + 1];
		
		pnlNumPanel.setBorder( BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK), "Input", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		
		// * =============================================================================================================
		// * For loop to add the appropriate amount of numbers into our number panel.
		// * =============================================================================================================
		for (int i = 1; i <= buttonNum; i++) {
			if (i + 48 > 57)
				inputs[i] = new JToggleButton("" + ((char) (i + 55)));
			else
				inputs[i] = new JToggleButton("" + ((char) (i + 48)));
				inputs[i].setPreferredSize(new Dimension(50, 50));
				inputs[i].addActionListener(e -> {
				currentSelection = e.getActionCommand().charAt(0);
				OptionPanel.appendText("User Clicked: " + e.getActionCommand());

			});

			bg.add(inputs[i]);
			pnlNumbers.add(inputs[i]);
		}
		
		// * =============================================================================================================
		// * Setting NumberInputPanel's panel attributes, as well as the contents of NumberInputPanel's inner panels.
		// * =============================================================================================================
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize((new Dimension(80,85)));
		
		pnlAlign.add(pnlNumPanel);
		pnlNumPanel.add(pnlNumbers);
		add(pnlAlign, BorderLayout.NORTH);
		
		
	} // End of NumberInputPanel constructor 
	
	
	/**
	 * Method name: getNumberSelected.
	 * Purpose: To retrieve the char value of the item pressed.
	 * @return char, The button that is presently being used.
	 */
	public char getNumberSelected() {
		return currentSelection;
	}
	
	
	
} // End of class NumberInputPanel.