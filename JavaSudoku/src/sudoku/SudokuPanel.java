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
 * Class holds the rules for generating a sudoku puzzle
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class SudokuPanel extends JPanel {

	private int[][] gameBoard;
	public int gridDim; // 4x4(2x2), 9x9(3x3), 16x16(4x4)
	private JLabel[][] grid;

	/**
	 * Creates a grid
	 */
	public SudokuPanel(int gridDim, boolean play) {

		grid = new SudokuGridLabel[gridDim][gridDim];
		this.gridDim = gridDim;
		JPanel panel = new JPanel(new GridLayout(gridDim, gridDim, 1, 1));
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.setBackground(Color.BLACK);
		
		for (int row = 0; row < gridDim; row++) {
			for (int col = 0; col < gridDim; col++) {
				grid[row][col] = new SudokuGridLabel("", 0, row, col, '0');
				grid[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
				grid[row][col].setOpaque(true);
				grid[row][col].setBackground(Color.WHITE);
				grid[row][col].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) {
						validMove(me.getComponent());
					}
					
				});
				panel.add(grid[row][col]);
				
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
							|| (row > 7 && row < 12  && col <= 3) || (row > 7 && row < 12 && col > 7 && col < 12)
							|| (row > 11 && col >= 4 && col <= 7 ) ||  (row > 11 && col > 7 && col >= 12)) ? Color.WHITE : Color.GRAY);
					break;
				}
			}
		}
		createBoard(play);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
	}

	protected void validMove(Component component) {
		SudokuGridLabel test = (SudokuGridLabel) component;
		int selectedNum = NumberInputPanel.currentSelection - 48;
		int x = ((SudokuGridLabel) component).getRow();
		int y = ((SudokuGridLabel) component).getCol();
		
		if(isPossibleX(gameBoard, x, selectedNum) && isPossibleY(gameBoard, y, selectedNum)
					&& isPossibleBlock(gameBoard, x, y, selectedNum)) {
			gameBoard[x][y] = selectedNum;
			OptionPanel.appendText("Set: " + selectedNum + "[" + x + "," + y + "]");
			test.setText(String.valueOf(selectedNum));
		}
		
	}

	/**
	 * Method: createBoard Populates the game board.
	 */
	public void createBoard(boolean play) {
		if (!play) {
			gameBoard = new int[gridDim][gridDim];
		} else {
			gameBoard = generateSolution(new int[gridDim][gridDim], 0);

			for (int col = 0; col < gridDim; col++) {
				for (int row = 0; row < gridDim; row++) {
					grid[row][col].setText(String.valueOf((char) (gameBoard[row][col] + 48)));
				}
			}
		}
	}

	/**
	 * Method: generateSolution
	 *
	 * Recursively generates a randomly created Sudoku game, based on the pivot
	 * point or cluster range.
	 *
	 * @param Board Game to populate, it will call itself to subsequently add more
	 *              items.
	 * @param Index The current index being worked on, initially set to zero for the
	 *              starting point.
	 * @return int[][] Will return the completed version of the board.
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
	 * Method: getNextNum Checks to see if it is a valid number in which to place
	 * into the present index, if so it will return the number, else it will return
	 * -1. It slowly reduces the list from the prior method via call by reference.
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

		// This will need to be adjusted for each additional jump in 4x4 9x9 16x16
		// 25x25, because there will need to be additional &&'s to adjust for
		// 1.5 being 2/3's of the cluster IE 6/9
		// 3 being a third of the cluster 3/9
		switch (gridDim) {
		case 4:
		case 9:
			if (x_axis < (gridDim / 1.5) && x_axis >= (gridDim / 3))
				horCluster = (gridDim / 3);
			else if (x_axis >= (gridDim / 1.5))
				horCluster = (int) (gridDim / 1.5);

			if (y_axis < (gridDim / 1.5) && y_axis >= 3)
				vertCluster = (gridDim / 3);
			else if (y_axis >= (gridDim / 1.5))
				vertCluster = (int) (gridDim / 1.5);
			break;
		case 16:
			
			horCluster = (x_axis < 3) ? 0: (x_axis > 3 && x_axis < 8) ? 4: (x_axis > 8 && x_axis <  12)? 8: 12;    
			vertCluster = (y_axis < 3) ? 0: (y_axis > 3 && y_axis < 8) ? 4: (y_axis > 8 && y_axis <  12)? 8: 12;   	
			break;

		}

		for (int col = vertCluster; col < vertCluster + (gridDim / 3); col++) {
            for (int row = horCluster; row < horCluster + (gridDim / 3); row++) {

                if (boardState[row][col] == current_num)
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
