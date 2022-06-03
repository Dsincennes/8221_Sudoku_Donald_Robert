package sudoku;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class OptionPanel extends JPanel {
//

    private JComboBox<String> diff;
    private JLabel diffLabel;
    private static JTextArea textArea;
    private JScrollPane scroll;
    private AboutPanel helpPanel;
    private JButton jb;
    
    public OptionPanel() {
        setBackground(Color.LIGHT_GRAY);


        Dimension dim = getPreferredSize();
        dim.width = 200;
        setPreferredSize(dim);
        
        jb = new JButton(new ImageIcon("sudoku_logo.png"));
        jb.setBounds(50, 50, 90, 20);
        
        diffLabel = new JLabel("Difficulty");
        String difficulty[]={"easy","medium","hard"};  
        diff = new JComboBox<String>(difficulty);
        diff.setBounds(50, 50, 90, 20);

        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Verdana", Font.BOLD, 10));
        textArea.setEditable(false);
        
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
        
        //////////////////// Fourth Row ////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = .1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 3, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(jb, gc);

		//Load
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpPanel = new AboutPanel();
			}
		});
      
    }
    
    public static void appendText(String text) {
        textArea.append(text);
        textArea.append("\n");
    }
    
	public class AboutPanel extends JPanel	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1791487245110440767L;
		JFrame frame;// Creating object of JFrame
		JLabel image = new JLabel(new ImageIcon("sudoku_about.png")); // Loading image
		JButton close = new JButton("Close");
		JLabel about = new JLabel("<HTML><U><B>How To Play</B></U></HTML>", SwingConstants.CENTER);

		/**
		 * Constructor that loads entire splash frame
		 */
		public AboutPanel()
		{
			frame = new JFrame(); // Create jframe object
			frame.getContentPane().setLayout( new BorderLayout());// set layout to null
			frame.setUndecorated(true);// no title bar
			frame.setSize(800, 600);// Frame size
			frame.setLocationRelativeTo(null);// frame location center
			frame.getContentPane().setBackground(Color.lightGray);
			image.setSize(600, 400); // image size
			close.setPreferredSize(new Dimension(50,50));
			about.setFont(new Font("Serif", Font.BOLD, 36));
			frame.add(about, BorderLayout.NORTH);
			frame.add(close, BorderLayout.SOUTH);
			frame.add(image, BorderLayout.CENTER); // add image to frame
			frame.setVisible(true);// show frame	
			close.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
		}

		/**
		 * Class handles splash screen loading for 5 seconds only
		 * @author Donald Sincennes & Robert Jackson
		 *
		 */
		public class ResourceLoader extends SwingWorker<Object, Object> {

			/**
			 * Overridden method to handle showing splash screen for 5 seconds then hiding it.s
			 */
			@Override
			protected Object doInBackground() throws Exception {
				Thread.sleep(1000);
				image.setVisible(false);
				
				
				Thread.sleep(5000); // wait 5 seconds
				frame.setVisible(false); // close splash
				return null;
			}
			
		}

	}
}

