/**
 * Rajpreet Judge & Raven Anklam
 * CSE 223
 * Nick Macias
 * Programming Assignment 4
 * 
 * This PA is called the dots game to give you a very short description of that the game does.
 * Two players play against eachother and try to make boxes if a person creates a box they get 
 * another turn there are 64 total boxes make try to make atleast 33 to win. You can restart mid
 * way through if you see there is no possible way for you to win. The game will update your score
 * as you play. Please enter your names and 
 */


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Dots extends JFrame {

	private JPanel contentPane;
	JTextField name1;
	JTextField name2;
	String player1name = "1", player2name = "2";
	int xCor, yCor;
	Board panel;
	Box boxes;
	boolean playerTurn;
	private JLabel lblPlayerScore_1;
	int ScoreP1 = 0, ScoreP2 = 0;
	private JTextField textField;
	private JTextField textField_1;
	int retval, retUpdateScore1 = 0, retUpdateScore2 = 0;
	protected boolean start;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dots frame = new Dots();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Dots() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		boxes = new Box();
		panel = new Board();
/**
 * Here we listen for mouse clicks and if a click happens we check its bounds
 * to see if its a click inside of our gameboard.	
 */
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(start) { //we only start the mouse events if the start button is pressed, "start" is a boolean set to false if the start button isnt pressed
					if((e.getX() < 400) && (e.getY() < 400) && (e.getX() > 0) && (e.getY() > 0)) { //checking our x and y bounds
						xCor=e.getX();yCor=e.getY(); //getting and storing if they and within bounds
						panel.saveCoord(xCor, yCor); //saving those x and y in our board method
						panel.setCell(); //we set the cells in the board method
						panel.gameplay(); //now we call out gameplay method which will check the click and set the corresponding side to true
						panel.updateNames(); //we update all possible boxes associated to the person who made them
			  		
			  		
						retval = panel.gameOver(); //we check if the game is over
			  		 
						if(retval == 1) { //if the score adds to 64 then the game is over a return value of 1 is passed by the gameOver method
							System.out.println("Gameover"); 
							ScoreP1 = panel.updatePlayer1Score(); //we save the player 1 score
							ScoreP2 = panel.updatePlayer2Score(); //and save player 2 score
							if(ScoreP1 > ScoreP2) { //check whose is high
								showMessageDialog(null,"Player 1 Wins!!!!!!!!"); //display a popup box showing winner		
								setVisible(false);
								dispose();
								System.exit(0); //exit the program
							}
							showMessageDialog(null,"Player 2 Wins!!!!!!!!"); //same for player 2		
							setVisible(false);
							dispose();
							System.exit(0);
						}
				
			  		
			  		
						retUpdateScore1 = panel.updatePlayer1Score(); //we dynamically update the player scores as boxes are created  
			  			retUpdateScore2 = panel.updatePlayer2Score(); //score updates for player 2
			  			textField.setText(Integer.toString(retUpdateScore1)); //set the text in the corresponding text fields on the JFrame
			  			textField_1.setText(Integer.toString(retUpdateScore2)); //setting the text score for player 2
			  			
			  			panel.repaint(); //call the repaint method
					}
					System.out.println("X = " + xCor + " Y = " + yCor);
				}
			}
		});
		panel.setBounds(30, 27, 410, 410);
		contentPane.add(panel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
/**
 * this is the part of the code where the start/restart button gets its functionality 
 */
			public void actionPerformed(ActionEvent arg0) {					
				if(btnStart.getText().equals("Start")) { //we check to see if the button's text is start becuase the button was pressed	
					start = true; //if its showed "start" in the text field of the button, change the boolean from false to true because the start button was pressed
					btnStart.setText("Restart"); //change the text to restart 
				}
				else { //if the button was pressed again
					btnStart.setText("Start"); //change the text to Start
					panel.resetGame(); //call the resetGame method that will reset everything
					retUpdateScore1 = 0; //update scores to 0
			  		retUpdateScore2 = 0;
			  		textField.setText(Integer.toString(retUpdateScore1)); // change the display scores to 0
			  		textField_1.setText(Integer.toString(retUpdateScore2));	
			  		start = false; //change the start boolean to false
					panel.repaint(); //call the repaint method
				}
			}
		});
		btnStart.setBounds(583, 12, 117, 25);
		contentPane.add(btnStart);
		
		name1 = new JTextField();
		name1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(player1name == null) return;
				player1name = name1.getText();
				panel.saveParent1(player1name);
				panel.repaint();
			}
		});
		name1.setText("Enter Player 1");
		name1.setBounds(586, 87, 114, 19);
		contentPane.add(name1);
		name1.setColumns(10);
		
		name2 = new JTextField();
		name2.addKeyListener(new KeyAdapter() {		
			@Override
			public void keyTyped(KeyEvent e) {
				if(player2name == null) return;
				player2name = name2.getText();
				panel.saveParent2(player2name);
				panel.repaint();
			}
		});
		name2.setText("Enter Player 2");
		name2.setBounds(586, 134, 114, 19);
		contentPane.add(name2);
		name2.setColumns(10);
		
		JLabel lblPlayerScore = new JLabel("Player 1 Score");
		lblPlayerScore.setBounds(581, 165, 122, 15);
		contentPane.add(lblPlayerScore);
		
		lblPlayerScore_1 = new JLabel("Player 2 Score");
		lblPlayerScore_1.setBounds(581, 212, 114, 15);
		contentPane.add(lblPlayerScore_1);
		
		textField = new JTextField();
		textField.setBounds(581, 181, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(581, 226, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(593, 67, 70, 15);
		contentPane.add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setBounds(593, 115, 70, 15);
		contentPane.add(lblPlayer_1);
		
		JLabel label = new JLabel("<-Clicks below this have no affect ");
		label.setBounds(448, 415, 252, 15);
		contentPane.add(label);
	}
}
