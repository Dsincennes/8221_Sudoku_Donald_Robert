package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	/*
	 * addMouseListener(new MouseAdapter() { public void mousePressed(MouseEvent me)
	 * { fireActionPerformed(new ActionEvent(SudokuGridLabel.this,
	 * ActionEvent.ACTION_PERFORMED, "")); } });
	 */
}

  protected void fireActionPerformed(ActionEvent ae) {

	OptionPanel.appendText("Set: " + String.valueOf(NumberInputPanel.currentSelection) + " Grid: [" + row + "," + col + "]");
	
	// 			if (isPossibleX(board, y_axis, number) && isPossibleY(board, x_axis, number)
	//&& isPossibleBlock(board, x_axis, y_axis, number))
	
    this.setText(String.valueOf(NumberInputPanel.currentSelection));
//    else
//    	this.setText("");

   
   
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