import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class holds the rules for generating a sudoku puzzle
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class GamePanel extends JPanel {

	JFormattedTextField[] field = new JFormattedTextField[9];

	private int[][] gameBoard;
	private int gridDim = 9; // 4x4(2x2), 9x9(3x3), 16x16(4x4), 25x25(5x5).

	private int panelWidth = 550;

	/**
	 * Creates a grid
	 */
	public GamePanel() {

		setBackground(Color.yellow);
		createBoard();

		setLayout(new GridLayout(gridDim, gridDim));
		Dimension dim = getPreferredSize();
		dim.width = panelWidth;
		setPreferredSize(dim);

		for (int col = 0; col < gridDim; col++)
			for (int row = 0; row < gridDim; row++) {
				field[col] = new JFormattedTextField(gameBoard[col][row]);
				;
				field[col].setHorizontalAlignment(JTextField.CENTER);
				field[col].setBackground(((col < 3 && row < 3) || (col < 3 && row > 5 && row < 9)
						|| (col < 6 && col > 2 && row > 2 && row < 6) || (col > 5 && row > 5) || (col > 5 && row < 3))
								? Color.PINK
								: Color.GRAY);
				field[col].setFont(new Font("Some-Font-Name", Font.BOLD, 16)); // Makes it easier to read with the pink and grey background

				add(field[col]);
				/*
				 * // solved puzzle for testing int[][] puzzle = { { 8, 2, 7, 1, 5, 4, 3, 9, 6
				 * }, { 9, 6, 5, 3, 2, 7, 1, 4, 8 }, { 3, 4, 1, 6, 8, 9, 7, 5, 2 }, { 5, 9, 3,
				 * 4, 6, 8, 2, 7, 1 }, { 4, 7, 2, 5, 1, 3, 6, 8, 9 }, { 6, 1, 8, 9, 7, 2, 4, 3,
				 * 5 }, { 7, 8, 6, 2, 3, 5, 9, 1, 4 }, { 1, 5, 4, 7, 9, 6, 8, 2, 3 }, { 2, 3, 9,
				 * 8, 4, 1, 5, 6, 7 } };
				 */

				/*
				 * TODO, Figure out a more concise version for odds and evens, so we can do this at different sizes.
				 * Just saving space and condensing it. if(i <3 && j < 3 )
				 * field[i].setBackground(Color.PINK); if(i < 3 && j > 5 && j < 9)
				 * field[i].setBackground(Color.PINK); if(i < 6 && i > 2 && j > 2 && j < 6)
				 * field[i].setBackground(Color.PINK); if(i > 5 && j > 5)
				 * field[i].setBackground(Color.PINK); if(i > 5 && j < 3)
				 * field[i].setBackground(Color.PINK);
				 */
			}
	}

	/**
	 * Method: createBoard Populates the game board.
	 */
	public void createBoard() {
		gameBoard = generateSolution(new int[gridDim][gridDim], 0);
	}

	/**
	 * Method: generateSolution
	 *
	 * Recursively generates a randomly created Sudoku game, based on the pivot point or cluster range.
	 *
	 * @param 			Board Game to populate, it will call itself to subsequently add more items.
	 * @param 			Index The current index being worked on, initially set to zero for the starting point.
	 * @return int[][] 	Will return the completed version of the board.
	 */
	public int[][] generateSolution(int[][] board, int index) {
		if (index > (Math.pow(gridDim, 2) - 1)) // Returns the game when it is populated
			return board;

		int x = index % gridDim; // Horizontal Axis
		int y = index / gridDim; // Vertical Axis

		List<Integer> numbers = new ArrayList<Integer>();

		for (int i = 1; i <= gridDim; i++)
			numbers.add(i);

		Collections.shuffle(numbers);

		while (numbers.size() > 0) { // While numbers isn't zero
			int number = getNextNumb(board, x, y, numbers); // Generate next possible number at this place.
			if (number == -1)
				return null;

			board[y][x] = number;

			int[][] tmpGame = generateSolution(board, index + 1);
			if (tmpGame != null)
				return tmpGame;
			board[y][x] = 0;
		}

		return null;
	}

	/**
	 * Method: getNextNum
	 * Checks to see if it is a valid number in which to place into the present index, if so it will return the number, else it will return -1.
	 * It slowly reduces the list from the prior method via call by reference.
	 * @param board   	   Board to check.
	 * @param x_axis       X position in game.
	 * @param y_axis       Y position in game.
	 * @param numList      List of remaining numbers.
	 * @return int		   Returns the next possible number, if it is not valid it will return -1.
	 */
	public int getNextNumb(int[][] board, int x_axis, int y_axis, List<Integer> numList) {
		while (numList.size() > 0) {
			int number = numList.remove(0);
			if (isPossibleX(board, y_axis, number) && isPossibleY(board, x_axis, number) && isPossibleBlock(board, x_axis, y_axis, number))
				return number;
		}
		return -1;
	}

	/**
	 * Returns true of false, dependent on if the current number is within the
	 * cluster
	 *
	 * @param boardState Current state of the board to check.
	 * @param x_axis     Current X position.
	 * @param y_axsis    Current Y position.
	 * @param number     Current number to look for.
	 * @return boolean Returns a true of false statement if the block is presently
	 *         able to accommodate the number.
	 *
	 *         TODO Add modifier to pivot based on changed 3x3 6x6 etc.
	 */
	public boolean isPossibleBlock(int[][] boardState, int x_axis, int y_axis, int current_num) {

		// If the x index is < 3 then it is in the first cluster.
		// If it is < 6 then it is in the second cluster.
		// Else it is in the third cluster.
		int horCluster = 0;
		int vertCluster = 0;

		if (gridDim % 3 == 0) { // This will need to be adjusted for each additional jump in 4x4 9x9 16x16
								// 25x25, because there will need to be additional &&'s to adjust for
			// 1.5 being 2/3's of the cluster IE 6/9
			// 3 being a third of the cluster 3/9
			if (x_axis < (gridDim / 1.5) && x_axis >= (gridDim / 3))
				horCluster = (gridDim / 3);
			else if (x_axis >= (gridDim / 1.5))
				horCluster = (int) (gridDim / 1.5);

			if (y_axis < (gridDim / 1.5) && y_axis >= 3)
				vertCluster = (gridDim / 3);
			else if (y_axis >= (gridDim / 1.5))
				vertCluster = (int) (gridDim / 1.5);

		} else {

			if (x_axis < 4 && x_axis >= 2)
				horCluster = 2;

			if (y_axis < 4 && y_axis >= 2)
				vertCluster = 2;

		}

		for (int col = vertCluster; col < vertCluster + (gridDim / 3); col++) {
			for (int row = horCluster; row < horCluster + (gridDim / 3); row++) {
				if (boardState[col][row] == current_num) // TODO Convert this to take chars Though it should not be hard
					return false;
			}
		}

		return true;
	}

	/**
	 * Returns whether given number is candidate on x axis for given game.
	 *
	 * @param board      Board to check.
	 * @param x_axis     Position of x axis to check.
	 * @param currentNum Present number to compare
	 * @return boolean Returns true if the number will fit within the limits of the
	 *         row.
	 */
	public boolean isPossibleX(int[][] board, int x_axis, int currentNum) {

		for (int row = 0; row < gridDim; row++)
			if (board[x_axis][row] == currentNum)
				return false;

		return true;
	}

	/**
	 * Returns true of false, given if a number is already present in a prior
	 * column.
	 *
	 * @param board      Board to check.
	 * @param y_axis     Position of y axis to check.
	 * @param currentNum Present number to compare
	 * @return boolean Returns true if the number will fit within the limits of the
	 *         column.
	 */
	public boolean isPossibleY(int[][] board, int y_axis, int currentNum) {

		for (int col = 0; col < gridDim; col++)
			if (board[col][y_axis] == currentNum)
				return false;

		return true;
	}

	/**
	 * SaveFile
	 * @param file file to be saved
	 * @throws IOException incase cannot save
	 */
	public void saveToFile(File file) throws IOException {

	}
}
