package sudoku;

import javax.swing.SwingUtilities;

/**
 * CET - CS Academic Level 4
 * 
 * 
 * File Name: Main.java 
 * Assessment: Assignment 1.2
 * Student Name:  Donald Sincennes & Robert Jackson 
 * Student Number: 041011305 & 040627795 
 * Course: CST8221 - Java Application Programming
 * 
 * @JavaVersion v13
 * @author Donald Sincennes & Robert Jackson
 * @version 0.1
 */

/**
 * Class: Main.java
 * Purpose: To invoke a new runnable thread of our application.
 * 
 * @author Donald Sincennes & Robert Jackson
 * @version 0.1
 * 
 */
public class Main {


	/**
	 * 
	 * Method name: main
	 * Purpose: To launch a new thread of our application, it is our entry point.
	 * 
	 * @param args of type String[], used for command line flags. (Not presently used)
	 */
	public static void main(String[] args) {
		// Invokes a new threat of our class.
		SwingUtilities.invokeLater(() -> {
			new GameController();
		});
	}

} // End of class main.
