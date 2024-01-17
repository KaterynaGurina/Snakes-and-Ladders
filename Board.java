/**
 * @author Kateryna Gurina 40188793
 * COMP249
 * Assignement 1
 * Due date 02/06/2023
 */
package Assignement_1;
import java.util.*;

/**
 * Board class represent a board
 *
 */
public class Board {
	private String [][] board;
	private ArrayList<int[]> snakes;
	private ArrayList<int[]> ladders;
	private int numPlayers;
	
	/**
	 * The default constructor that generates the board 10 by 10
	 * The board is initialized with numbers 100 to 1, in descending order.
	 * The number of players is set to 2.
	 * Snakes and ladders ArrayLists are initialized as empty.
	 */
	public Board() {
		String [][] board = new String[10][10]; 
		int a  = 100;
		for (int i=0; i<10; i++) { 
			if (i%2==0) { 
				for (int j = 0; j<10; j++) {
					board[i][j] = Integer.toString(a--);}}
			else { 
				for (int j = 0; j<10; j++){
					board[i][9-j] = Integer.toString(a--);}}
		}
		this.board = board;
		this.snakes = new ArrayList<int[]>();
		this.ladders = new ArrayList<int[]>();
		this.numPlayers = 2;
	}
	
	/**
	 * Constructs a new Board instance with a specified number of players.
	 * @param num The number of players
	 */
	public Board(int num) {
		String [][] board = new String[10][10]; 
		int a  = 100;
		for (int i=0; i<10; i++) { 
			if (i%2==0) { 
				for (int j = 0; j<10; j++) {
					board[i][j] = Integer.toString(a--);}}
			else { 
				for (int j = 0; j<10; j++){
					board[i][9-j] = Integer.toString(a--);}}
		}
		this.board = board;
		this.snakes = new ArrayList<int[]>();
		this.ladders = new ArrayList<int[]>();
		if (num<2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");//idk how to terminate the programm here 
			System.exit(a);}
		else if (num>2) {
			System.out.println("Initialization was attempted for "+num+" players; However, this is only expected in the extended version the game. Value will be set to 2");
			this.numPlayers = 2;}
		else
			this.numPlayers = 2;
		
	}
// could write a constructed that sates random snakes and ladders
	
	/**
	*Converts a 1-100 position to a 2D integer array
	*@param position the 1-100 position to be converted
	*@return a 2D integer array representing the position in the board
	*/	
	public int [] convertPosition(int position){
		int [] positionArr = new int [2];
		
		if (position%10==0)
			positionArr[0] = 10 - position/10;
		else
			positionArr[0] = 9 - position/10; 
		
		if (positionArr[0] %2 ==0) {
			if (position%10==0)
				positionArr[1] = 0;
			else positionArr[1] = 10 - position%10;}
		else {
			if (position%10==0)
				positionArr[1] = 0;
			else positionArr[1] = position%10-1;}
		return positionArr;	
	}
	

	/**
	 * getter gets the character stored in the particular position on the board
	 * @param position the 1-100 position to be converted to a int [][] representation
	 * @return the character stored in the that position on the board
	 */
	public String getBoardPiece(int position){
		int [] positionArr = convertPosition(position);
		return board[positionArr[0]][positionArr[1]];
	}
		
	/**
	 * setter takes the int position 1-100 and the value to store in that position AS A STRING and sets the character stored in that particular position on the board to this string
	 * @param position the position on the board as an integer from 1 to 100
	 * @param a the value to store at the given position as a string
	 */
	public void setBoardPiece(int position, String a) {
		int [] positionArr = convertPosition(position);
		this.board[positionArr[0]][positionArr[1]] = a;
	}
		
	/**
	 * setter that sets a snake on the board. It takes an array of int positions the snake, adds it to the array list snakes
	 * it could also loops through the snake array and sets the board positions to 'S' using the setBoardPiece method.
	 * @param snake and int array 
	 */
	public void setSnake (int [] snake) {
		this.snakes.add(Arrays.copyOf(snake, snake.length));
		//for (int i = 0; i<snake.length; i++) {
		//setBoardPiece(snake[i], "S");	
		//}
	}
	 
	/**
	 * Similar working setter for ladders
	 * @param ladder An array of integer positions representing the ladder
	 */
	public void setLadder (int [] ladder) {
		this.ladders.add(Arrays.copyOf(ladder, ladder.length));
		//for (int i = 0; i<ladder.length; i++) {
		//setBoardPiece(ladder[i], "L");	
		//}
	}
	

	/**
	 * getter for the snakes field
	 * @return the ArrayList of int arrays representing the snakes
	 */
	public ArrayList<int[]> getSnakes (){
		return snakes;
	}
	
	public ArrayList<int[]> getLadders (){
		return ladders;
	}
	
	/**
	 * This method prints the board, including the grid lines and the positions on the board.
	 */
	public void printBoard() {
		System.out.println("------------------------------------------------------------------------------");
		for (int i = 0; i<10; i++) {
		System.out.println(Arrays.toString(board[i]).replace("], ", "\n").replace("[[", " ").replace("]]", " ").replace("[", " ").replace(",", " \t| ") .replace("],","\n").replace(",","\t| ").replaceAll("[\\[\\]]", " "));
		System.out.println("------------------------------------------------------------------------------");
		}
	}
	
	/**
	 * toString method for the snakes
	 * @return the string representation of the list of snakes
	 */
	public String snakesToString() {
		String snakesStr = new String("");
		for (int i = 0; i<this.snakes.size();i++ ) {
			snakesStr+=("\n" + (i+1) +": ");
			for (int j = 0; j<this.snakes.get(i).length; j++) {
				snakesStr = snakesStr + this.snakes.get(i)[j] + " ";
			}
		}
		return "The snakes are..." + snakesStr;
	}

		
	/**
	 * toString method for the ladders
	 * @return the string representation of the list of ladders
	 */
	public String laddersToString() {
		String laddersStr = new String("");
		for (int i = 0; i<this.ladders.size();i++ ) {
			laddersStr+=("\n" + (i+1) +": ");
			for (int j = 0; j<this.ladders.get(i).length; j++) {
				laddersStr = laddersStr + this.ladders.get(i)[j] + " ";
			}
		}
		return "The ladders are..." + laddersStr;
	}	
}

