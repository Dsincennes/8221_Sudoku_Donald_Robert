package sudoku;
import javax.swing.JLabel;

public class SudokuGridLabel extends JLabel {

	int row;
	int col;
	char value;

	public SudokuGridLabel(String string, int i, int row, int col, char value) {
		super(string, i);

		this.row = row;
		this.col = col;
		this.value = value;
	}

	public void setValue(int i) {
		this.value = (char) i;
	}

	public char getValue() {
		return this.value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}