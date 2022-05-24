import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	// Buttons on north toolbar
	private JButton startButton;

	private MainGame mg;

	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder()); // Border for north toolbar too differentiate from main space
		
		// Instantiate buttons
		startButton = new JButton("New Game"); 

		
		startButton.addActionListener(this); // adding listener for start button
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); // Setting where location of buttons appear

		// adding buttons to layout
		add(startButton);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource(); // getting source of button click
		if (clicked == startButton) { // if start is clicked
			System.out.println("User pressed Start"); // logging, verifying click works
			mg = new MainGame(); //TODO fix this, creates new window.
			mg.newGame();
		}
	}
}
