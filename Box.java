import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Rajpreet Judge & Raven Anklam
 * CSE 223
 * Nick Macias
 * Programming Assignment 4
 * 
 * Very simple box class that simulates a box in memory
 * there are 4 sides each is a boolean
 * the each box will have an owner of type String
 * 
 */

public class Box extends JPanel {
	
	boolean leftSide, rightSide, topSide, botSide;
	public String owner;	
	
	/**
	 * very simple constructor just make all sides false because no lines are drawn and 
	 * make the owner an empty string
	 */
	
	public Box() {
		leftSide=rightSide=topSide=botSide=false;
		owner = "";
	}	
}
