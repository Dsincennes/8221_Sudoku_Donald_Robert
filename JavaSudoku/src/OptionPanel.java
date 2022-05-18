import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionPanel extends JPanel {

	private JRadioButton b1;
	private JRadioButton b2;
	private JComboBox<String> diff;
	private JLabel diffLabel;
	private JLabel modeLabel;
	
	public OptionPanel() {
		setBackground(Color.LIGHT_GRAY);

		Dimension dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
		modeLabel = new JLabel("Mode");
		b1 = new JRadioButton("design");
		b2 = new JRadioButton("play");
		ButtonGroup bg = new ButtonGroup();
		bg.add(b1);
		bg.add(b2);
		
		diffLabel = new JLabel("Difficulty");
		String difficulty[]={"easy","medium","hard"};  
		diff = new JComboBox<String>(difficulty);
		diff.setBounds(50, 50, 90, 20);
		
		layoutComponents();

	}

	public void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//////////////////// First Row ////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(modeLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(b1, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(b2, gc);
		
		//////////////////// Second Row ////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(diffLabel, gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(diff, gc);
	}
}