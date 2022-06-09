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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * This Class handles our Buttons that are to be selected for input into sudoku panel
 * @author Donald Sincennes & Robert Jackson
 *
 */
public class NumberInputPanel extends JPanel{

	private JToggleButton[] inputs;
	private ButtonGroup bg;
	private JPanel pnlNumbers;
	static char currentSelection;
	
	public NumberInputPanel(int num) {
        super(new BorderLayout());
        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);
        
		setBackground(Color.LIGHT_GRAY);
		currentSelection = '1';
		Dimension dim = getPreferredSize();
		dim.height = 85;
		
		setPreferredSize(dim);
		
		inputs = new JToggleButton[16];
		bg = new ButtonGroup();
        pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(line, "Input", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        pnlAlign.add(pnlNumbers);
        
		JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlNumbers.add(pnlNumbersNumbers);
		
		for(int i = 1; i <= num; i++) {
			if(i + 48 > 57)
				inputs[i] = new JToggleButton("" + ((char)(i + 55)));	
			else
			inputs[i] = new JToggleButton("" + ((char)(i + 48)));
			inputs[i].setPreferredSize(new Dimension(50,50));
			inputs[i].addActionListener(e ->{
				currentSelection = e.getActionCommand().charAt(0);
				OptionPanel.appendText("User Clicked: " + e.getActionCommand());
				
				
			});;
			bg.add(inputs[i]);
			pnlNumbersNumbers.add(inputs[i]);
		}
	}
	
	public char getNumberSelected() {
		return currentSelection;
	}
}