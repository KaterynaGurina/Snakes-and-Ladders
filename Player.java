/**
 * @author Kateryna Gurina 40188793
 * COMP249
 * Assignement 1
 * Due date 02/06/2023
 */
package Assignement_1;
/**
 * Player class represents a possible player
 */

public class Player {
	private String name;
	private int diceRoll = 0;
	private int position = 0;
	private int oldPosition = 0;
	private static int numPlayers = 1; //when the num players reaches 2 either display a message that this game shouldnt have more then 2 players or stop the call completely 
	
/** 
 * Player default constructor
 */
	public Player() {
		this.name = "Player "+ numPlayers;
		this.diceRoll = 0;
		this.position = 0;
		this.oldPosition = 0;
		numPlayers++; 
		}
	
/**
 * Player parameterized constructed
 * @param name the name of the player
 */
	public Player(String name) {
		
		this.name = name;
		this.diceRoll = 0;
		this.position = 0;
		this.oldPosition = 0;
		numPlayers++;
		}
	
/**
 * Getter method for the attribute name
 * @return the value of the name 
 */
	public String getName() {
		return name;}
/**
* Getter method for the attribute position
* @return the value of the position 
*/
	public int getPosition() {
		return position;}
/**
* Getter method for the attribute oldPosition
* @return the value of the oldPosition 
*/
	public int getOldPosition() {
		return oldPosition;}
/**
* Getter method for the attribute diceRoll
* @return the value of the diceRoll 
*/
	public int getDiceRoll() {
		return diceRoll;}

	
	/**
	 * Setter method for attribute name
	 * @param name the value to set for the "name" instance variable
	 */
	public void setName(String name) {
		this.name = name;}
	/**
	 * Setter method for attribute position
	 * @param position the value to set for the "position" instance variable
	 */
	public void setPosition(int position) {
		this.position = position;}
	/**
	 * Setter method for attribute oldPosition
	 * @param oldPosition the value to set for the "oldPosition" instance variable
	 */
	public void setOldPosition(int oldPosition) {
		this.position = oldPosition;}
	/**
	 * Setter method for attribute diceRoll
	 * @param diceRoll the value to set for the "diceRoll" instance variable
	 */
	public void setDiceRoll(int diceRoll) {
		this.diceRoll = diceRoll;}
	

	/**
	 *Returns a string representation of the object
	 *@return a string representation of the object
	 */
	public String toString() {
		return "Player "+ this.name + " is now at position " + position;}
	

	/**
	 * Flips a dice and returns a random number between 1 and 6.
	 * @return a random integer between 1 and 6, inclusive.
	 */
	public int flipDice() {
		return (int)(Math.random()*6)+1;
	}
	
	/**
	 * makes a move for an object that calls it
	 * @param playerThatDoesntMoove the player that doesn't make a move
	 * @param gameBoard the current game board
	 * @param blankBoard the board without players, but that has unchanged cells that are used as a reference to restore the cell when a player leaves it 
	 * @return thisWon a boolean indicating whether the current player has won or not
	 */
	public boolean playerWinMove(Player playerThatDoesntMoove, Board gameBoard, Board blankBoard) {
		boolean thisWon = false;
		System.out.println("\n" + this.name + "'s turn.");
		
		//Rolling the dice
		this.diceRoll = this.flipDice();
		
		//Setting the new position
		this.oldPosition = this.position;
		this.position = this.position + this.diceRoll;
		
		//Checking 3 possibilities: 
		//if the player overshot 100
		
		if (this.position > 100) {
			this.position = 100-(this.position-100);
			System.out.println(this.name + " rolled " + this.diceRoll + " ...too much! Go back " + (100-this.position) + " spaces!");
			
			if (this.position == playerThatDoesntMoove.position) {
				System.out.println("Your position is the same as " + playerThatDoesntMoove.name + "'s... Sneaky! You get to kick them off the board!");
				gameBoard.setBoardPiece(this.position, this.name.substring(0,1));
				gameBoard.setBoardPiece(playerThatDoesntMoove.position, blankBoard.getBoardPiece(playerThatDoesntMoove.position));
				gameBoard.printBoard();
				playerThatDoesntMoove.setPosition(0);
				return thisWon = false;
			}
			else {
				gameBoard.setBoardPiece(this.position, this.name.substring(0,1));
				if (this.oldPosition != 0){
					gameBoard.setBoardPiece(this.oldPosition, blankBoard.getBoardPiece(this.oldPosition));}
				gameBoard.printBoard();
				return thisWon = false;
			}
		}
		
		//if the position is still under 100 
		else if (this.position < 100){
			System.out.println(this.name + " rolled " + this.diceRoll + ". Now they move to the position " + this.position);
			
			//Checking if hit a ladder or a snake a changing the position accordingly
			for (int i = 0; i<gameBoard.getSnakes().size(); i++) {
				if (this.position == gameBoard.getSnakes().get(i)[0]) {
					this.position = gameBoard.getSnakes().get(i)[1];
					System.out.println("Oh no! The snake ate you! ;( Gotta go down to position " + this.position);
				}
				else if (this.position == gameBoard.getLadders().get(i)[0]) {
					this.position = gameBoard.getLadders().get(i)[1];
					System.out.println("Oh what a luck! Yout can climb a ladder to the position " + this.position);
				}
			}
			
			//Checking if got the same position as the other player
			//the only print that erases the player at position 0 off the board 
			if (this.position == playerThatDoesntMoove.position) {
				System.out.println("Your position is the same as " + playerThatDoesntMoove.name + "'s... Sneaky! You get to kick them off the board!");
				gameBoard.setBoardPiece(this.position, this.name.substring(0,1));
				if (this.oldPosition != 0){
					gameBoard.setBoardPiece(this.oldPosition, blankBoard.getBoardPiece(this.oldPosition));}
				
				gameBoard.setBoardPiece(playerThatDoesntMoove.position, blankBoard.getBoardPiece(playerThatDoesntMoove.position));
				if (playerThatDoesntMoove.oldPosition != 0){
					gameBoard.setBoardPiece(playerThatDoesntMoove.oldPosition, blankBoard.getBoardPiece(playerThatDoesntMoove.oldPosition));}
				gameBoard.printBoard();
				playerThatDoesntMoove.setPosition(0);
			}
			
			else {
				gameBoard.setBoardPiece(this.position, this.name.substring(0,1));
				if (this.oldPosition != 0){
				gameBoard.setBoardPiece(this.oldPosition, blankBoard.getBoardPiece(this.oldPosition));}
				gameBoard.printBoard();
			}
			if(this.position == 100) {
				System.out.println("******"+ this.name + " WINS!******");
				return thisWon = true;
			}
			else return thisWon = false;
		}
		//the last option is if the player is exactly at 100 an so the won!			
		else{
			System.out.println("******"+ this.name + " WINS!******");
			gameBoard.setBoardPiece(this.position, this.name.substring(0,1));
			if (this.oldPosition != 0){
				gameBoard.setBoardPiece(this.oldPosition, blankBoard.getBoardPiece(this.oldPosition));}
			gameBoard.printBoard();
			return thisWon = true;
		}
	}
}
