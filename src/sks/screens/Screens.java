package sks.screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sks.controller.DataProcessor;
import sks.to.Word;

public class Screens extends JFrame {

	private static final long serialVersionUID = -7260092478604833008L;

	// Layer pane is used to show and hide different screens
	private JLayeredPane layeredPane;

	// the first landing screen
	private JPanel welcomePanel;

	// Data processor handle to fetch, store, keep track of questions & answers
	private DataProcessor controller = new DataProcessor();

	/**
	 * Constructor
	 */
	public Screens() {

		// Basic JFrame setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 75, 600, 750);

		// Initialize the Layered Pane and add it to the Jframe
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(300, 75, 600, 750);
		getContentPane().add(layeredPane);

		// Setup Welcome Screen which is the first screen
		setupWelcomeScreen();

	}

	/**
	 * Initialize the Screen. This method will be invoked from the GameLauncher
	 * class
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screens frame = new Screens();
					frame.setVisible(true);
					showWelcomeScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method to paint the welcome screen
	 */
	public void showWelcomeScreen() {
		this.layeredPane.removeAll();
		this.layeredPane.add(welcomePanel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}

	/**
	 * Method to handle the questions screen
	 * 
	 * @param word
	 */
	public void showQuestions(Word word) {

		// Initialize the question pane
		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(new Color(250, 251, 187));
		questionPanel.setBounds(0, 0, 586, 703);
		layeredPane.add(questionPanel);
		questionPanel.setLayout(null);

		// Add a heading label
		JLabel headingLabel = new JLabel(
				"!Questions " + controller.getQuestionIndex() + " of " + controller.getTotalNumberOfQuestions() + "!");
		headingLabel.setLabelFor(questionPanel);
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setFont(new Font("Kristen ITC", Font.BOLD, 40));
		headingLabel.setBounds(80, 50, 400, 150);
		questionPanel.add(headingLabel);

		// Show the scrambled word
		JLabel questionLabel = new JLabel(word.getScrambledWord());
		questionLabel.setLabelFor(questionPanel);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setFont(new Font("Kristen ITC", Font.BOLD, 36));
		questionLabel.setBounds(80, 200, 400, 150);
		questionPanel.add(questionLabel);

		// Initialize a text field to collect user data
		JTextField textField;
		textField = new JTextField();
		textField.setBounds(80, 300, 400, 150);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Kristen ITC", Font.BOLD, 36));
		questionPanel.add(textField);

		// A button to collect user action
		// After user enters the answer, expected to click this "Next" button
		// Notice the action listener that collects the text field user entered values
		// AND
		// Call the process question method
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processNextQuestion(textField.getText());
			}
		});
		btnNext.setBackground(new Color(74, 150, 100));
		btnNext.setFont(new Font("Tw Cen MT", Font.PLAIN, 36));
		btnNext.setBounds(80, 500, 405, 132);
		questionPanel.add(btnNext);

		// Remove all existing panes and add this question pane
		this.layeredPane.removeAll();
		this.layeredPane.add(questionPanel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();

	}

	/**
	 *  A screen to show the end score to users AND
	 *  provide them option to continue with the game or quit
	 *  
	 */
	public void showScoreScreen() {

		// Initialize a new pane
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(new Color(250, 251, 187));
		scorePanel.setBounds(0, 0, 586, 703);
		layeredPane.add(scorePanel);
		scorePanel.setLayout(null);

		// Use a label to display the score
		// the data processor instance keeps track of the score, so get it from it
		JLabel headingLabel = new JLabel("Your Score is #" + controller.getUserScore() + " !");
		headingLabel.setLabelFor(scorePanel);
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setFont(new Font("Kristen ITC", Font.BOLD, 24));
		headingLabel.setBounds(83, 54, 405, 132);
		scorePanel.add(headingLabel);

		// Provide user option to continue with a new game
		JButton btnNext = new JButton("Play Again!");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAgain();
			}
		});
		btnNext.setBackground(new Color(74, 150, 100));
		btnNext.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		btnNext.setBounds(50, 500, 200, 100);
		scorePanel.add(btnNext);

		
		// Provide user an option to exit the game
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		btnQuit.setBackground(new Color(74, 150, 100));
		btnQuit.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		btnQuit.setBounds(350, 500, 200, 100);
		scorePanel.add(btnQuit);

		this.layeredPane.removeAll();
		this.layeredPane.add(scorePanel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}
	
	/**
	 * a utility method to setup welcome screen widgets
	 */

	private void setupWelcomeScreen() {

		// Setup the welcome pane and add it to the layered pane
		welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(250, 251, 187));
		welcomePanel.setBounds(0, 0, 586, 703);
		layeredPane.add(welcomePanel);
		welcomePanel.setLayout(null);

		// Add a label with game name
		JLabel welcomeLabel = new JLabel("!Unscrambled!");
		welcomeLabel.setLabelFor(welcomePanel);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Kristen ITC", Font.BOLD, 51));
		welcomeLabel.setBounds(83, 54, 405, 132);
		welcomePanel.add(welcomeLabel);

		JButton btnlevel1 = new JButton("Level 1 : 5 letters");
		btnlevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeQuestions(1);
			}
		});
		btnlevel1.setBackground(new Color(74, 141, 253));
		btnlevel1.setFont(new Font("Tw Cen MT", Font.PLAIN, 46));
		btnlevel1.setBounds(102, 250, 386, 69);
		welcomePanel.add(btnlevel1);

		JButton btnlevel2 = new JButton("Level 2 : 7 letters");
		btnlevel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeQuestions(2);
			}
		});
		btnlevel2.setBackground(new Color(0, 128, 255));
		btnlevel2.setFont(new Font("Tw Cen MT", Font.PLAIN, 46));
		btnlevel2.setBounds(102, 386, 386, 69);
		welcomePanel.add(btnlevel2);

		JButton btnlevel3 = new JButton("Level 3 : 8 letters");
		btnlevel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeQuestions(3);
			}
		});
		btnlevel3.setBackground(new Color(74, 141, 253));
		btnlevel3.setFont(new Font("Tw Cen MT", Font.PLAIN, 46));
		btnlevel3.setBounds(102, 522, 386, 69);
		welcomePanel.add(btnlevel3);

	}

	private void initializeQuestions(int inputLevel) {
		controller.getQuestions(inputLevel);
		processNextQuestion("");
	}

	private void processNextQuestion(String answer) {

		if (controller.getQuestionIndex() > 0) {
			controller.setUserAnswer(answer);
		}

		Word word = controller.getNextQuestion();
		if (word != null) {
			showQuestions(word);
		} else {
			showScoreScreen();
		}
	}


	private void playAgain() {		
		// In case users want to play again, wipe clean the data processor
		// We do this by re-initializing with a new data processor object
		this.controller = new DataProcessor();
		this.showWelcomeScreen();
	}
	
	

	private void quit() {
		System.exit(0);
	}
}
