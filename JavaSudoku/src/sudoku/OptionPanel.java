package sudoku;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

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
    private static JTextArea textArea;
    private JScrollPane scroll;
    
    public OptionPanel() {
        setBackground(Color.LIGHT_GRAY);

        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);
        
        modeLabel = new JLabel("Mode");

//        bg.add(designButton);
//        bg.add(playButton);
//        add(saveButton);
//        add(loadButton);
//        add(startButton);
//        add(resetButton);
        
        diffLabel = new JLabel("Difficulty");
        String difficulty[]={"easy","medium","hard"};  
        diff = new JComboBox<String>(difficulty);
        diff.setBounds(50, 50, 90, 20);
        
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Verdana", Font.BOLD, 10));
        
        layoutComponents();

    }


    public void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //////////////////// First Row ////////////////////

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        add(diffLabel, gc);
        
        gc.gridy = 0;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(diff, gc);
        
        //////////////////// second Row ////////////////////

        gc.gridy++;

        gc.weightx = 0;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 5, 5, 5);
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        
        add(scroll, gc);
        
//        gc.gridx = 1;
//        gc.insets = new Insets(0, 0, 0, 0);
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        add(textArea, gc);
        
        //////////////////// Fourth Row ////////////////////

//        gc.gridy++;
//
//        gc.weightx = 1;
//        gc.weighty = 2.0;
//
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 30, 0, 5);
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        add(startButton, gc);
//        
//        gc.gridx = 1;
//        gc.insets = new Insets(0, 0, 0, 0);
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        add(resetButton, gc);
    }
    public static void appendText(String text) {
        textArea.append(text);
        textArea.append("\n");
    }
}