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

import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	
	int xClick, yClick, col, row; //xClick and yClick are variables that store the position of my click. col and row store the column and row
	int right, left, top, bot; //theses are used to determine how close the click is to all 4 sides of a box. We choose the smallest one
	String name1 = "E", name2 = "2"; // if you dont choose names I set them to player 1 = "E" and player 2 = "2"
	Box[][] myBox = new Box[8][8]; //create my 2d array of boxes
	boolean turn; // true means player 1 turn and false is player 2
	
	/**
	 * Constructor for the board class
	 * the constructor goes through the 2d array and
	 * sets each index for the array to a new box
	 */
	
	public Board() {
		
		turn = true;
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				myBox[i][j] = new Box();
			}
		}
		
	}
	
	/**
	 * This is a very fun class basically because all the drawing/painting happens here
	 * We will initially draw our gameboard which is a 9x9 set of dots and then we has a 
	 * pair of nested for loops that iterate through the 2d array and see which side of the box is true, 
	 * if all sides are true not only does the method draw the lines the player who drew that box
	 * will have his letter placed inside it
	 */
	
	public void paint(Graphics g) {
		super.paint(g);
		gameBoard(g);
			
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				
				int row = i;
				int col = j;
				
				if(myBox[i][j].leftSide == true) { 
					g.drawLine((col*50), (row*50), (col*50), (row*50) + 50); //draws left side lines
					//System.out.println("left");
				}
				if(myBox[i][j].rightSide == true) { 
					g.drawLine((col*50) + 50,(row*50),(col*50) + 50,(row*50) + 50); // draws right side lines
					//System.out.println("right");
				}
				if(myBox[i][j].topSide == true) { 
					g.drawLine((col*50),(row*50),(col*50) + 50,(row*50)); //draws top side lines
					//System.out.println("top");
				}
				if(myBox[i][j].botSide == true) { 
					g.drawLine((col*50),(row*50) + 50,(col*50) + 50,(row*50) + 50); // draws bot lines
					//System.out.println("bot");
					
				}
				if((myBox[i][j].leftSide == true) && (myBox[i][j].rightSide == true) && (myBox[i][j].botSide == true) && (myBox[i][j].topSide == true)) {
					//System.out.println("i = " + i + "j = " + j);
					//System.out.println("owner = " + myBox[i][j].owner);
					g.drawString(myBox[i][j].owner, (col*50) + 25, (row*50) + 25);
					
				}
			}
		}
		
		return;
		
	}
	
	/**
	 * this is the method that is called from the paint method to draw 
	 * our gameboard. a pair of nested for loops that paint dots at 50 pixels apart.
	 * @param g
	 */
	
	public void gameBoard(Graphics g) {
		
		int x, y;
		
		for(x = 0; x <= 450; x+=50) {
			for(y = 0; y <= 450; y+=50) {
				g.fillOval(x, y, 5, 5);
			}
		}
		return;
	}
	
	/**
	 * THIS METHOD IS USED FOR UPDATING THE PLAYER 1'S SCORE
	 * IT IS CALLED AFTER EACH MOUSE EVENT. THE METHOD WILL 
	 * ITERATE THROUGH EACH INDEX IN THE ARRAY AND COUNT HOW MANY BOX
	 * NAMES MATCH THE PLAYER 1 NAME AND UPDATE THE SCORE ACCORDINGLY
	 * @return
	 */
	
	public int updatePlayer1Score() {
		
		int player1Score = 0;
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {		
				int row = i;
				int col = j;
				if(myBox[row][col].owner.equals(name1)) {
					player1Score++;
				}				
			}
		}
		return player1Score;
	}
	
	/**
	 * THIS METHOD IS USED FOR UPDATING THE PLAYER 2'S SCORE
	 * IT IS CALLED AFTER EACH MOUSE EVENT. THE METHOD WILL 
	 * ITERATE THROUGH EACH INDEX IN THE ARRAY AND COUNT HOW MANY BOX
	 * NAMES MATCH THE PLAYER 2 NAME AND UPDATE THE SCORE ACCORDINGLY
	 * @return 
	 * returns a string version of the number
	 */
	
	public int gameOver() { 
		int play1 = 0, play2 = 0; //creating two integer variables so I can compare both players score
		
		for(int i = 0; i <= 7; i++) { 
			for(int j = 0; j <= 7; j++) {		
				int row = i;
				int col = j;
				if(myBox[row][col].owner.equals(name2)) { //checking if the box that this location in the array has owner of player 2
					play2++; //if so increment player 2's counter
				}		
				if(myBox[row][col].owner.equals(name1)) { //if the name is equal to player 1
					play1++; //increment counter for player 1
				}				
			}
		}
		if((play1 + play2) == 64) { //check to see if they add up to 64 boxes 
			return 1; //return a 1 if thats the case
		}
		 
		return 0; //else we just return a 0
		
	}
	
	/**
	 * This method is very similar to the gameOver method and exactly Identical to the updatePlayer1Score
	 * We go through each index and see if the player 2 has their name in that box.
	 * If so we increment a counter
	 */
	
	public int updatePlayer2Score() {
		
		int player2Score = 0;
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {		
				int row = i;
				int col = j;
				if(myBox[row][col].owner.equals(name2)) {
					player2Score++;
				}				
			}
		}
		return player2Score;
	}
	
	/**	
	 *updateNames method assigns names based on turns 
	 *we index through the array and check the name the box is equal to the players name 
	 */
	
	public void updateNames() {
		
		boolean boxMade = false; //false means all 4 sides are not true and the other player should go next, if true then the current player should keep their turn
		//System.out.println("before loop boxMade = " + boxMade);
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				
				if((myBox[i][j].leftSide == true) && (myBox[i][j].rightSide == true) && (myBox[i][j].topSide == true) && (myBox[i][j].botSide == true) && (myBox[i][j].owner.isEmpty())) { //checking if all sides are true and box's name field is empty
					
					if(turn == true) { //meaning if its player 1's turn and they are the ones who completed the box
						if(myBox[i][j].owner.equals(""))
							boxMade = true;
							myBox[i][j].owner = name1; //setting the name of the owner of that box to player 1
					}
					else {
						if(myBox[i][j].owner.equals(""))
							boxMade = true; //stating we made a box so we dont change player turns
							myBox[i][j].owner = name2; //setting the name of the owner to player 2
					}
				}	
				
			}
		}
		//System.out.println("out of loop boxMade = " + boxMade);
		if(boxMade == false) { //all sides of a box were made boxMade = true
			if(turn == true) //change turns if box was not made
				turn = false;
			else
				turn = true;
		}
		System.out.println("turn = " + turn);
		return;
	}
	
	/**
	 * This method will reset the game if the restart button is pressed.
	 * It contains a pair of nested for loops that iterate through and set everything back to false
	 * and empty
	 */
	public void resetGame() {
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				myBox[i][j].leftSide = false;
				myBox[i][j].rightSide = false;
				myBox[i][j].topSide = false;
				myBox[i][j].botSide = false;
				myBox[i][j].owner = " ";
			}
		}
		return;
	}
	
	/**
	 * This is the main driver of the game
	 * Here we call a method called closeCLick that calculates the distances from all sides
	 * then we check for the smallest number and set that side true and the 
	 * corresponding boxes other side to true aswell
	 */
	public void gameplay() {
			
		closeClick();
		
		/*System.out.println("left = " + left);
		System.out.println("right = " + right);
		System.out.println("top = " + top);
		System.out.println("bot = " + bot);*/
			
		if((left < right) && (left < top) && (left < bot)) { //here we check for smallest side being left
			
			Box b = myBox[row][col]; // create an object of type box 
			
			if(!b.leftSide) { //check to see if that side isnt already true
				b.leftSide = true; //if its false set it to be true
				
				if((col - 1) != -1) // and then set the box to its left side's right wall true also
					myBox[row][col - 1].rightSide = true;
			}
		
		}
		
		if((right < left) && (right < top) && (right < bot)) {//here we check the right side for smallest side 
			
			Box b = myBox[row][col];// create an object of type box equal to that row, col
		
			if(!b.rightSide) {//check to see if that side isnt already true
				b.rightSide = true;//if its false set it to be true
				if((col + 1) <= 7) //set then box to its right if possible left side true
					myBox[row][col + 1].leftSide = true;
			}
		
		}
		
		if((top < left) && (top < right) && (top < bot)) { //here we check the top side for smallest side 
			
			Box b = myBox[row][col]; // create an object of type box equal to that row, col
			
			if(!b.topSide) { //check to see if that side isnt already true
				b.topSide = true; //if its false set it to be true
				
				if((row - 1) != -1) //set then box to its top if possible bot side true
					myBox[row - 1][col].botSide = true;
			}
			
		}
		
		if((bot < left) && (bot < right) && (bot < top)) { //here we check the bot side for smallest side 
		
			Box b = myBox[row][col]; // create an object of type box equal to that row, col
			
			if(!b.botSide) { //check to see if that side isnt already true
				b.botSide = true; //if its false set it to be true
				 
				if((row + 1) <= 7) //set then box to its top if possible bot side true
					myBox[row + 1][col].topSide = true;
			}
			
		}
		
		return;
	}
	
	/**
	 * We save the String passed to this method but we Only save the leading Letter/Character and we append a space to it.
	 * @param player1name this is a string passed by entering name into the text field
	 */
	
	public void saveParent1(String player1name) {
		
		if(player1name == null) //check for null
			return;
		
		if(player1name.length() > 0) //if bigger than 0
			name1 = player1name.charAt(0) + ""; //then we save that into the name1 variable for player1 name
		
		//System.out.println(name1);
		return;
	}
	
	/**
	 * We save the String passed to this method but we Only save the leading Letter/Character and we append a space to it.
	 * @param player2name this is a string passed by entering name into the text field
	 */
	
	public void saveParent2(String player2name) {
		
		if(player2name == null) //check for null
			return;
		
		if(player2name.length() > 0) //if the length is bigger than 0
			name2 = player2name.charAt(0) + ""; //then we save that into the name1 variable for player1 name
	
		if(name1.equals(name2)) { //here we check to see if the names are the Same
			name2 = "2"; //if they are the same we change player 2's name two "2"
			showMessageDialog(null,"Name same as Player 1 Change it yourself otherwise I have set it to \"2\""); //display a popup box with a message indicating they should change the name
		}
		//System.out.println(name2);
		return;
	}
	
	/**
	 * This method performs all the calculations for determining whether the click is closet to switch side
	 * it stores those numbers into variables our other methods can see and use based on the task at hand
	 */
	
	public void closeClick() {
		left = xClick - (col*50); //subtracting the xClick Coordinate by the col times 50. This finds the distance from the left side
		if(left < 0) //if negative 
			left *= -1; //make it positive
		right = xClick - ((col*50)+50); //subtracting the xClick Coordinate by the (col times 50) + 50. this finds the distance from the right side
		if(right < 0) //if negative
			right *= -1; //make it positive
		top = (row*50) - yClick; //subtracting the yClick Coordinate by the row times 50. This finds the distance from the top side
		if(top < 0) //if negative
			top *= -1; //make it positive
		bot = ((row*50)+50)- yClick; //subtracting the yClick Coordinate by the (row times 50) + 50. This finds the distance from the bot side
		if(bot < 0) //if negative
			bot *= -1; //make it positive
		return;
	}	
	
	/**
	 * setting my the rows and cols of the click
	 */
	public void setCell() {
		if((xClick/50) < 8) 
			col = xClick/50;
		if((yClick/50) < 8)
			row = yClick/50;
		return;
	}
	
	
	//saving the coordinates of the click
	public void saveCoord(int xCoord, int yCoord) {
		xClick = xCoord;
		yClick = yCoord;
		return;
	}
	
	
	//getter for the row
	public int getRow() {
		return row;
	}
	
	
	//getter for the col 
	public int getCol() {
		return col;
	}
	
	//returning the X coordinate of the click subtracted by the col number times 50
	public int lineCoordX() {
		return(xClick -(col*50));			
	}
	
	
	//returning the y coordinate of the click subtracted by the row number times 50
	public int lineCoordY() {
		return(yClick -(row*50));	
	}

	
}