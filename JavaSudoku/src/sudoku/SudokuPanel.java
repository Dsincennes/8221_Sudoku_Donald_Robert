package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CET - CS Academic Level 4
 * 
 * File Name: SudokuPanel.java 
 * Assessment: Assignment 1.2
 * Student Name:  Donald Sincennes and Robert Jackson 
 * Student Number: 041011305 & 040627795
 * Course: CST8221 - Java Application Programming
 * 
 * @author Donald Sincennes & Robert Jackson
 * @version 0.1
 * 
 */

/**
 * 
 * Class Name: SudokuPanel
 * Class Purpose: this class holds the rules for generating a sudoku puzzle, as well as checking for errors while inputing numbers 
 * in design mode.
 * 
 * @author Donald Sincennes and Robert Jackson
 * @version 0.1
 *
 */
public class SudokuPanel extends JPanel {
	/**
	 * gridDim size for the gameboard array
	 */
	private int gridDim, gameBoard[][]; // 4x4(2x2), 9x9(3x3), 16x16(4x4)
	/**
	 * grid of Jlabels for sudoku puzzle
	 */
	private JLabel[][] grid;
	
	/**
	 * 
	 * Method Name: SudokuPanel
	 * Method Purpose: The overloaded constructor for class SudokuPanel.
	 * This sets up the grid based on the dimensions given, as well as which play mode you are presently in. 
	 * 
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 * @param gridDim, for different sizes of sudoku 2x2 or 3x3
	 * @param playMode, to differ from play or design
	 */
	public SudokuPanel(int gridDim, boolean playMode) {

		grid = new SudokuGridLabel[gridDim][gridDim];
		this.gridDim = gridDim;
		
		JPanel boardPanel = new JPanel(new GridLayout(gridDim, gridDim, 1, 1));
		
		boardPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		boardPanel.setBackground(Color.BLACK);

		for (int row = 0; row < gridDim; row++) {
			for (int col = 0; col < gridDim; col++) {
				grid[row][col] = new SudokuGridLabel(row, col);
				grid[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
				grid[row][col].setOpaque(true);
				grid[row][col].setBackground(Color.WHITE);
				
				grid[row][col].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) {
						validMove(me.getComponent());
					}

				});
				
				boardPanel.add(grid[row][col]);

				switch (gridDim) {
				case 4:
					grid[row][col].setBackground(
							((col <= 1 && row <= 1) || ((col > 1 && row > 1))) ? Color.WHITE : Color.GRAY);
					break;
				case 9:
					grid[row][col].setBackground(((row < 3 && col < 3) || (row < 3 && col > 5 && col < 9)
							|| (row < 6 && row > 2 && col > 2 && col < 6) || (row > 5 && col > 5)
							|| (row > 5 && col < 3)) ? Color.WHITE : Color.GRAY);
					break;
				case 16:
					grid[row][col].setBackground(((row <= 3 && col <= 3) || (row <= 3 && col > 7 && col < 12)
							|| (row > 3 && row < 8 && col >= 4 && col <= 7) || (row > 3 && row < 8 && col >= 12)
							|| (row > 7 && row < 12 && col <= 3) || (row > 7 && row < 12 && col > 7 && col < 12)
							|| (row > 11 && col >= 4 && col <= 7) || (row > 11 && col > 7 && col >= 12)) ? Color.WHITE
									: Color.GRAY);
					break;
				}
			}
		}
		
		createBoard(playMode);
		
		setLayout(new BorderLayout());
		
		add(boardPanel, BorderLayout.CENTER);
	} // End of constructor SudokuPanel.

	/**
	 * 
	 * Method Name: validMove
	 * Method Purpose: To validate that the action taken by the user is valid for data entry.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @param component of type Component, this is the custom label that we created for positional awareness.
	 */
	private void validMove(Component component) {
		SudokuGridLabel test = (SudokuGridLabel) component;
		int selectedNum = NumberInputPanel.currentSelection - 48;
		int x = ((SudokuGridLabel) component).getRow();
		int y = ((SudokuGridLabel) component).getCol();

		if (isPossibleX(gameBoard, x, selectedNum) && isPossibleY(gameBoard, y, selectedNum)
				&& isPossibleBlock(gameBoard, x, y, selectedNum)) {
			gameBoard[x][y] = selectedNum;
			OptionPanel.appendText("Set: " + selectedNum + "[" + x + "," + y + "]");
			test.setText(String.valueOf(selectedNum));
		}

	} // End of method validMove.

	/**
	 * 
	 * Method Name: createBoard
	 * Method Purpose: createBoard Populates the game board.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * @param mode of type boolean, This is to indicate which mode we are presently in.
	 * 
	 */
	public void createBoard(boolean mode) {
		if (!mode)
			gameBoard = new int[gridDim][gridDim];
		else {
			gameBoard = generateSolution(new int[gridDim][gridDim], 0);

			for (int col = 0; col < gridDim; col++)
				for (int row = 0; row < gridDim; row++) 
					grid[row][col].setText(String.valueOf((char) (gameBoard[row][col] + 48)));
		}
	} // End of method CreateBoard.

	/**
	 * Method Name: generateSolution
	 * Method Purpose: Recursively generates a randomly created Sudoku game, based on the pivot
	 * point or cluster range.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * 
	 * @param board of type int[][], Game to populate, it will call itself to subsequently add more
	 *              items.
	 * @param index of type int, The current index being worked on, initially set to zero for the
	 *              starting point.
	 * @return board Will return the completed version of the board.
	 */
	public int[][] generateSolution(int[][] board, int index) {
		
		if (index > (Math.pow(gridDim, 2) - 1)) // Returns the game when it is populated
			return board;

		int x = index % gridDim; 
		int y = index / gridDim; 

		List<Integer> numbers = new ArrayList<Integer>();

		for (int i = 1; i <= gridDim; i++)
			numbers.add(i);

		Collections.shuffle(numbers);

		while (numbers.size() > 0) { 
			int number = getNextNumb(board, x, y, numbers);
			if (number == -1)
				return null;

			board[y][x] = number;

			int[][] tmpGame = generateSolution(board, index + 1);
			if (tmpGame != null)
				return tmpGame;
			board[y][x] = 0;
		}

		return null;
	} // End of generateSolution.

	/**
	 * Method Name: getNextNumb
	 * Method Purpose: getNextNum Checks to see if it is a valid number in which to place
	 * into the present index, if so it will return the number, else it will return
	 * -1. It slowly reduces the list from the prior method via call by reference.
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
	 * 
	 * @param board   Board to check.
	 * @param x_axis  X position in game.
	 * @param y_axis  Y position in game.
	 * @param numList List of remaining numbers.
	 * @return int Returns the next possible number, if it is not valid it will
	 *         return -1.
	 */
	public int getNextNumb(int[][] board, int x_axis, int y_axis, List<Integer> numList) {
		while (numList.size() > 0) {
			int number = numList.remove(0);
			if (isPossibleX(board, y_axis, number) && isPossibleY(board, x_axis, number)
					&& isPossibleBlock(board, x_axis, y_axis, number))
				return number;
		}
		return -1;
	} // End of method getNextNumb.

	/**
	 * 
	 * Method Name: isPossibleBlock
	 * Method Purpose: Returns true of false, dependent on if the current number is within the
	 * cluster
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * 
	 * @param boardState Current state of the board to check.
	 * @param x_axis     Current X position.
	 * @param y_axis    Current Y position.
	 * @param current_num     Current number to look for.
	 * @return boolean Returns a true of false statement if the block is presently able to accommodate the number.
	 *
	 * TODO Add modifier to pivot based on changed 4x4 etc.
	 */
	public boolean isPossibleBlock(int[][] boardState, int x_axis, int y_axis, int current_num) {

		int horCluster = 0;
		int vertCluster = 0;

		// This will need to be adjusted for each additional jump in 4x4 9x9 16x16
		switch (gridDim) {
		case 4:				
					horCluster = (x_axis >= 2) ? 2:0;
					vertCluster = (y_axis >= 2) ? 2:0;
				
				for (int col = vertCluster; col < vertCluster  + (gridDim / 2); col++)
					for (int row = horCluster; row < horCluster  + (gridDim / 2); row++)
						if (boardState[row][col] == current_num)
							return false;
			break;
		case 9:
			if (x_axis < (gridDim / 1.5) && x_axis >= (gridDim / 3))
				horCluster = (gridDim / 3);
			else if (x_axis >= (gridDim / 1.5))
				horCluster = (int) (gridDim / 1.5);

			if (y_axis < (gridDim / 1.5) && y_axis >= 3)
				vertCluster = (gridDim / 3);
			else if (y_axis >= (gridDim / 1.5))
				vertCluster = (int) (gridDim / 1.5);
			
			
			for (int col = vertCluster; col < vertCluster + (gridDim / 3); col++)
				for (int row = horCluster; row < horCluster + (gridDim / 3); row++)
					if (boardState[row][col] == current_num)
						return false;
			break;

		}

		return true;
	} // End of method isPossibleBlock.

	/**
	 * 
	 * Method Name: isPossibleX
	 * Method Purpose: Returns whether given number is candidate on x axis for given game.
	 * 
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * 
	 * @param board of type int[][], The board to check.
	 * @param x_axis of type int, Position of x axis to check.
	 * @param currentNum of type int, Present number to compare
	 * @return boolean Returns true if the number will fit within the limits of the
	 *         row.
	 */
	public boolean isPossibleX(int[][] board, int x_axis, int currentNum) {

		for (int row = 0; row < gridDim; row++)
			if (board[x_axis][row] == currentNum)
				return false;

		return true;
	} // End of method isPossibleX.

	/**
	 * 
	 * Method Name: isPossibleY
	 * Method Purpose: Returns whether given number is candidate on y axis for given game.
	 * 
	 * 
     * @author Donald Sincennes and Robert Jackson
     * @version 0.1
     * 
	 * @param board of type int[][], The board to check.
	 * @param y_axis of type int, Position of y axis to check.
	 * @param currentNum of type int, Present number to compare
	 * @return boolean Returns true if the number will fit within the limits of the
	 *         row.
	 */
	public boolean isPossibleY(int[][] board, int y_axis, int currentNum) {

		for (int col = 0; col < gridDim; col++)
			if (board[col][y_axis] == currentNum)
				return false;

		return true;
	} // End of method isPossibleY.

	/**
	 * 
	 * Method Name: saveToFile
	 * Method Purpose: Saves our present game into a serialized version of it.
	 * 
	 * 
	 * @author Donald Sincennes and Robert Jackson
	 * @version 0.1
	 * 
	 * @param file file to be saved
	 * @throws IOException incase cannot save
	 */
	public void saveToFile(File file) throws IOException {

	} // End of method saveToFile.
} // End of class SudokuPanel.