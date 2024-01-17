/**
 * @author Kateryna Gurina 40188793
 * COMP249
 * Assignement 1
 * Due date 02/06/2023
 */
package Assignement_1;

import java.util.Scanner;

/**
 * PlayLadderAndSnake class is the game driver class for the Ladder and Snake board game.
 * It contains the main method which sets up the game by initializing the number of players,
 * setting up the board with the predefined snakes and ladders, and asking for the names of the players.
 * The class then decides who goes first by tossing a dice and starts the game loop.
 * In each turn of the game loop, a player rolls the dice and moves the corresponding number of steps on the board.
 * The first player to reach the end of the board wins the game.
 */
public class PlayLadderAndSnake {

	/**
	  * Main method that sets up the game, asks for the player names, decides who goes first,
	  * and starts the game loop until a player reaches the end of the board.
	  * @param args not used in this program
	  */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean intro = true;
		while(intro) {
		System.out.println("Enter the amount of players: ");
		int players = input.nextInt();
		Board gameBoard = new Board(players); 
		Board blankBoard = new Board();
		
		//setting the predefined snakes, to simplify, I'm setting then only to beginning and end index, but for the future could set the entire length so that the user would see them on the playing board
		//snakes are arrayList types so it will work for snakes of different length
		//for now the example board provided has too many of them overlapping, so it would be very confusiong where is what if i printed on the simple board the we have here
		int [] snake1 = {16,6}; 
		int [] snake2 = {48,3};
		int [] snake3 = {79,19};
		int [] snake4 = {64,60};
		int [] snake5 = {94,24};
		int [] snake6 = {93,68};
		int [] snake7 = {97,76};
		int [] snake8 = {98,78};
		
		int [] ladder1 = {1,38}; 
		int [] ladder2 = {4,14};
		int [] ladder3 = {9,31};
		int [] ladder4 = {28,84};
		int [] ladder5 = {36,44};
		int [] ladder6 = {21,42};
		int [] ladder7 = {51,67};
		int [] ladder8 = {71,91};
		int [] ladder9 = {80,100};
		
		gameBoard.setSnake(snake1);
		gameBoard.setSnake(snake2);
		gameBoard.setSnake(snake3);
		gameBoard.setSnake(snake4);
		gameBoard.setSnake(snake5);
		gameBoard.setSnake(snake6);
		gameBoard.setSnake(snake7);
		gameBoard.setSnake(snake8);
		gameBoard.setSnake(snake1);
		
		gameBoard.setLadder(ladder1);
		gameBoard.setLadder(ladder2);
		gameBoard.setLadder(ladder3);
		gameBoard.setLadder(ladder4);
		gameBoard.setLadder(ladder5);
		gameBoard.setLadder(ladder6);
		gameBoard.setLadder(ladder7);
		gameBoard.setLadder(ladder8);
		gameBoard.setLadder(ladder9);
		
		
		System.out.println("Enter the names of the players separated by a space, one word for one player: ");
		Player player1 = new Player();
		player1.setName(input.next().trim().toUpperCase());
		
		Player player2 = new Player();
		player2.setName(input.nextLine().trim().toUpperCase());
		
		System.out.println("\nNow let's decide who goes first!");
System.out.println("(N to exit the game completely)");
intro = askYN();
if (!intro) {
	System.out.println("Ok, bye!");
	break;
}
		
		  boolean player1goesfirst = false; 
		  boolean continueTossing = true;
		  int attempts = 0;
		  
		  
		  while(continueTossing) {
		  
			  player1.setDiceRoll(player1.flipDice());
			  player2.setDiceRoll(player2.flipDice());
			  
			  if(player1.getDiceRoll()>player2.getDiceRoll()) { 
				  player1goesfirst = true;
				  continueTossing = false; }
			  
			  else if(player1.getDiceRoll()<player2.getDiceRoll()) { 
				  player1goesfirst =false; 
				  continueTossing = false; } 
			  
			  else { 
				  continueTossing = true; }
			  attempts++;
		  }		  
		  
		  if(player1goesfirst) { 
			  System.out.println("\n"+player1.getName() + " got " + player1.getDiceRoll() + " and " + player2.getName() + " got "+ player2.getDiceRoll()); 
			  System.out.println(player1.getName()+" goes first!");
		  } 
		  
		  else { System.out.println(player1.getName() + " got " + player1.getDiceRoll() + " and " + player2.getName() + " got "+ player2.getDiceRoll()); 
		  System.out.println(player2.getName()+" goes first!");
		  }
		  System.out.println("And it only took " + attempts+ " try to decide.");
		  
 System.out.println("\nContinue?");
 System.out.println("(N to exit the game completely)");
 intro = askYN();
 if (!intro) {
		System.out.println("Ok, bye!");
		break;
	}
		
		System.out.println("\nPerfect ^^ Welcome to Ladders and Snakes!");
		System.out.println("Here is your playing board: ");
		gameBoard.printBoard();
		System.out.println("Wanna see where the snakes are or do you wanna keep it as a surprise?");
		if(askYN()){
			System.out.println("Here is where the Ladders and Snakes are: \n");
			System.out.println(gameBoard.laddersToString());
			System.out.println(gameBoard.snakesToString());}
		
//Starting the game		
		System.out.println("\nLets beging!");
		boolean wannaPlay = askYN();
		while(wannaPlay){
			boolean gameOn = true;
			while (gameOn){
	//first player move
				if(player1goesfirst&&gameOn) {
					boolean playerWon = player1.playerWinMove(player2, gameBoard, blankBoard);
					if(!playerWon) {
						System.out.println("\nContinue?");
						gameOn = askYN();
						player1goesfirst = false;}
					else {
						gameOn = false;
						break;}
				}	
				
	//second player move
				if(!player1goesfirst&&gameOn) {
					boolean playerWon = player2.playerWinMove(player1, gameBoard, blankBoard);
					if(!playerWon) {
						System.out.println("Continue?");
						gameOn = askYN();
						player1goesfirst = true;}
					else {
						gameOn = false;
						break;}
				}	
			}
		System.out.println("Would you like to play one more time with the same players?");
		wannaPlay = askYN();
		}

System.out.println("\nGo to the begining of the game?");
System.out.println("(N to exit the game completely)");
intro = askYN();
if (!intro) {
	System.out.println("Ok, bye!");
	break;
}
	}
}
	
	public static boolean askYN() {
        while (true) {
        	Scanner input = new Scanner(System.in);
            System.out.println("Y or N: ");
            String userIn = input.nextLine();
            if (userIn.equalsIgnoreCase("y")) return true;
            if (userIn.equalsIgnoreCase("n")) return false;
            System.out.println("Invalid input, please try again.");
        }
	}
}
