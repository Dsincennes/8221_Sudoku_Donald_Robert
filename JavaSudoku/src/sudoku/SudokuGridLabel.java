package sudoku;

import javax.swing.JLabel;

/**
 * CET - CS Academic Level 4
 * 
 * 
 * File Name: SudokuGridLabel.java 
 * Assessment: Assignment 1.2
 * Student Name:  Donald Sincennes and Robert Jackson 
 * Student Number: 041011305 & 040627795
 * Course: CST8221 - Java Application Programming
 * 
 * @JavaVersion v13
 * @author Donald Sincennes & Robert Jackson
 * @version 0.1
 * 
 */

/**
 * Class Name: SudokuGridLabel
 * Purpose: To implement a customized version of JLabel, so we are able to keep track of attributes that pretain to its position and value. 
 * 
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 */
public class SudokuGridLabel extends JLabel {

	/**
	 * row and col of the current grid selection
	 */
	private int row, col;

	/**
	 * 
	 * Method Name: SudokuGridLabel
	 * Method Purpose: The default constructor, this is simply to initialize the values.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @param row of type int, The row of which this label is on.
	 * @param col of type int, The column of which this label is on.
	 */
	public SudokuGridLabel(int row, int col) {
		super("", 0);

		this.row = row;
		this.col = col;
	}


	/**
	 * Method name: getRow
	 * Method Purpose: To get the attribute of the row.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @return int, the row to be returned.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Method name: setRow
	 * Method Purpose: To set the attribute of the row.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @param row of type int, The row to be set.
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Method name: getCol
	 * Method Purpose: To get the attribute of col.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @return int, the column to be returned.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * Method name: setCol
	 * Method Purpose: To set the attribute of the col integer.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * 
	 * @param col of type int, the column to be set.
	 */
	public void setCol(int col) {
		this.col = col;
	}
} // End of class SudokuGridLabel.