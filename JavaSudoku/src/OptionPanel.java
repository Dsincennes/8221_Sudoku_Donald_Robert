import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionPanel extends JPanel {
//
	private JRadioButton designButton;
	private JRadioButton playButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton startButton;
	private JButton resetButton;
	private JComboBox<String> diff;
	private JLabel diffLabel;
	private JLabel modeLabel;
	
	public OptionPanel() {
		setBackground(Color.LIGHT_GRAY);

		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		modeLabel = new JLabel("Mode");
//		designButton = new JRadioButton("design");
//		playButton = new JRadioButton("play");
//		saveButton = new JButton("Save Game");
//		loadButton = new JButton("Load Game");
//		startButton = new JButton("Start Game");
//		resetButton = new JButton("Reset Game");
		
		ButtonGroup bg = new ButtonGroup();
//		bg.add(designButton);
//		bg.add(playButton);
//		add(saveButton);
//		add(loadButton);
//		add(startButton);
//		add(resetButton);
		
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

//		gc.gridx = 0;
//		gc.fill = GridBagConstraints.NONE;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.insets = new Insets(0, 0, 0, 5);
//		add(modeLabel, gc);
//
//		gc.gridx = 1;
//		gc.gridy = 0;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(designButton, gc);
//		
//		gc.gridx = 2;
//		gc.gridy = 0;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(playButton, gc);
		
		//////////////////// Second Row ////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(diffLabel, gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(diff, gc);
		
		//////////////////// Third Row ////////////////////

//		gc.gridy++;
//
//		gc.weightx = 1;
//		gc.weighty = 0.1;
//
//		gc.gridx = 0;
//		gc.insets = new Insets(0, 30, 0, 5);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(saveButton, gc);
//		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(loadButton, gc);
		
		//////////////////// Fourth Row ////////////////////

//		gc.gridy++;
//
//		gc.weightx = 1;
//		gc.weighty = 2.0;
//
//		gc.gridx = 0;
//		gc.insets = new Insets(0, 30, 0, 5);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(startButton, gc);
//		
//		gc.gridx = 1;
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		add(resetButton, gc);
	}
}
