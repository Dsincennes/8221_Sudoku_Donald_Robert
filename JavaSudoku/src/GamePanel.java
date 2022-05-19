import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	JFormattedTextField[] field = new JFormattedTextField[9];

	public GamePanel() {
		setBackground(Color.yellow);
		
		// solved puzzle for testing
		int[][] puzzle = 
			{ 
				{ 8, 2, 7, 1, 5, 4, 3, 9, 6 }, 
				{ 9, 6, 5, 3, 2, 7, 1, 4, 8 }, 
				{ 3, 4, 1, 6, 8, 9, 7, 5, 2 },
				{ 5, 9, 3, 4, 6, 8, 2, 7, 1 }, 
				{ 4, 7, 2, 5, 1, 3, 6, 8, 9 }, 
				{ 6, 1, 8, 9, 7, 2, 4, 3, 5 },
				{ 7, 8, 6, 2, 3, 5, 9, 1, 4 }, 
				{ 1, 5, 4, 7, 9, 6, 8, 2, 3 }, 
				{ 2, 3, 9, 8, 4, 1, 5, 6, 7 } 
			};

		setLayout(new GridLayout(9, 9));
		Dimension dim = getPreferredSize();
		dim.width = 550;
		setPreferredSize(dim);

		for (int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				field[i] = new JFormattedTextField(puzzle[i][j]);;
				field[i].setHorizontalAlignment(JTextField.CENTER);
				field[i].setBackground(Color.GRAY);
				add(field[i]);
				if(i <3 && j < 3 )
					field[i].setBackground(Color.PINK);
				if(i < 3 && j > 5 && j < 9)
					field[i].setBackground(Color.PINK);
				if(i < 6 && i > 2 && j > 2 && j < 6)
					field[i].setBackground(Color.PINK);
				if(i > 5 && j > 5)
					field[i].setBackground(Color.PINK);
				if(i > 5 && j < 3)
					field[i].setBackground(Color.PINK);
				
			}
		}
		
	}
	public void saveToFile(File file) throws IOException {
		
		
	}
}
