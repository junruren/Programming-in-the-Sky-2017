
package tic_tac_toe;

import java.util.Scanner;


/**
* A class that implements a simple TicTacToe game
* @version 2.0
* @author  Zhimin Lin
* @since   2017-04-18
*/ 

public class TicTacToe {
	
	private static final int NUM_COL = 3;
	private static final int NUM_ROW = 3;
	private static final int MAX_NUM_TRY = 2;

	//An Scanner object that reads in user's choice
	private static Scanner input = new Scanner(System.in);
	
	private Board board;
	private Dice dice;
	private char humanSymbol, compSymbol;
	private String AIName;


	//Object of Stats class to maintain statistics
	private static Stats stat = new Stats();
	
	/*
	 * Constructor. Initialize the board, the dice and the AI's name
	 */
	public TicTacToe(){
		board = new Board();
		dice = new Dice();
		AIName = "Thomas";
	}
	
	/**
	 * Set the symbols that user and the computer will be using in the game.
	 * 
	 * @param symbol The symbol chosen by the user
	 */
	public void setSymbol(char symbol){
		humanSymbol = symbol;
		if (humanSymbol == 'X'){
			compSymbol = 'O';
		}
		else{
			compSymbol = 'X';
		}
	}
	
	/**
	 * Set the AI's name.
	 * 
	 * @param name The name for the AI
	 */
	public void setAIName(String name){
		AIName = name;
	}
	
	/**
	 * Return the AI's name.
	 * 
	 * @return the AI's name
	 */
	public String getAIName(){
		return AIName;
	}
	
	/**
	 * Return the board for the game.
	 * 
	 * @return the board for the game
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Return the symbol for the user.
	 * 
	 * @return the symbol for the user
	 */
	public char getHumanSymbol(){
		return humanSymbol;
	}

	
	/** Determines if the player with the specified token wins
	 * 
	 * @param symbol: Specifies whether the player is X or O
	 * @return true if player has won, false otherwise
	 */
	public boolean isWon(char symbol) {
		int row, col;
		  
	    for (row = 0; row < NUM_ROW; row++){ //horizontal
	    	for (col = 0; col < NUM_COL; col++){
	    		if (board.getSlot(row, col) != symbol){
	    			break;
	    		}
	    		if (col == NUM_COL - 1){
	    			return true;
	    		}
	    	}
	    }

	    //Vertical
	    for (col = 0; col < NUM_COL; col++){
	    	for (row = 0; row < NUM_ROW; row++){
	    		if (board.getSlot(row, col) != symbol){
	    			break;
	    		}
	    		if (row == NUM_COL - 1){
	    			return true;
	    		}
	    	}
	    }
	    
	    //Diagonal, top-left to bottom-right
	    for (row = 0; row < NUM_ROW; row++){
	    	col = row;
	    	if (board.getSlot(row, col) != symbol){
	    		break;
	    	}
	    	if (row == NUM_ROW - 1){
	            return true;
	    	}
	    }
	    
	    //Diagonal, top-right to bottom-left
	    for (row = 0, col = NUM_COL - 1; col >= 0; row++, col--){
	    	if (board.getSlot(row, col) != symbol){
	    		break;
	    	}
	    	if (col == 0){
	    		return true;
	    	}
	    }
	    return false;
	  }


	/**
	 * Randomly decide if the computer or the user will start the game
	 * @return if computer start the game returns 0; if user starts the
	 * game return 1
	 */
	public int whoStarts(){
		int choice = -1;
		if (dice.toss() % 2 == 0){
			choice = 0;
		}
		else{
			choice = 1;
		}
		return choice;
	}
	  
	/** takes care of the human's move
	 * 1. Prompt for a cell, then column
	 * 2. Puts a symbol (X or O) on the board
	 * 3. Prints the updated board
	 * 4. If a human wins: prints, updates stats and returns true
	 * 5. If not a win yet, returns false 
	 */
	public boolean humanTurn(){
		
		System.out.print("\n\nEnter your move: (row column): " );
		int row = input.nextInt();  //Read the input for row number
		int col = input.nextInt();  //Read the input for column number
		  
		//Keep prompting for another choice until the chosen slot is empty
		while(!board.slotIsEmpty(row, col)){  
			System.out.println("Sorry that cell "
	  		                   + "is already occupied. Please try again.");
			  
			System.out.print("\n\nEnter your move: (row column): " );
			row = input.nextInt();
			col = input.nextInt();
		}
		  
		board.setSlot(row, col, humanSymbol);  //Put the symbol on the board
		  
		board.printBoard();
		  
		//Print corresponding message and increment statistic once win.
		if (isWon(humanSymbol)){
			System.out.println("You Won!!!");
			stat.incrementUserWins();
		}
		else{
			return false;
		}
		return true;
	} 
	  
	/** takes care of the computer's move
	 * 1. Finds the most reasonable slot on the board for the next step
	 * 2. Puts a symbol (X or O) on the board
	 * 3. Prints the updated board
	 * 4. If a comp wins: prints, updates stats and returns true
	 * 5. If not a win yet, returns false
	 */
	public boolean compTurn() {
		  
		int row = 0, col = 0;
		int rowTemp = 0, colTemp = 0;
		boolean moved = false;
		int numTry = 0;
		int lastEmptyRow = 0, lastEmptyCol = 0;
		
		//Loop through the board to check if there is any
			//way for computer or user to win immediately
		for(int i = 0; i < NUM_ROW; i++){
			for (int j = 0; j < NUM_COL; j++){
				if (board.slotIsEmpty(i, j)){
					lastEmptyRow = i;
					lastEmptyCol = j;
					 //Assume a move in this slot by the computer
					board.setSlot(i, j, compSymbol);
					if (isWon(compSymbol)){
						row = i;
						col = j;
						moved = true;
						i = NUM_ROW;  //To break the outer loop
						break;  
					}
					//Assume a step in this slot by player
					board.setSlot(i, j, humanSymbol);  
					
					if (isWon(board.getSlot(i, j))){
						rowTemp = i;
						colTemp = j;
					}
					board.setSlot(i, j, ' ');  //Reset the slot to empty
				}
			}
		}
		
		//If a block is applicable, use the slot identified to block
		if (!moved && rowTemp != 0 || colTemp != 0){
			row = rowTemp;
			col = colTemp;
			moved = true;
		}
		//If not, see if the center slot and the four corners are empty
		else if (!moved && board.slotIsEmpty(1, 1)){  
			row = 1;
			col = 1;
		}
		else if (!moved){
			while(!board.slotIsEmpty(row, col) && numTry < MAX_NUM_TRY){
				int randomRow = dice.toss();
				int randomCol = dice.toss();
				if (randomRow <= 3){
					row = 0;
				}
				else{
					row = NUM_ROW - 1;
				}
			
				if (randomCol > 3){
					col = 0;
				}
				else{
					col = NUM_COL - 1;
				}
				numTry++;
			}
			//If finds no empty slot after max number of tries, choose the 
				//last empty slot
			if (numTry == MAX_NUM_TRY){
				row = lastEmptyRow;
				col = lastEmptyCol;
			}
		}
		    
		board.setSlot(row, col, compSymbol);  //Put the symbol on the board
		    
		System.out.println("\n\nMy Move is: "+row+" "+ col);
		  
		board.printBoard();
		    
		//Print corresponding message and increment statistic once win.
		if (isWon(compSymbol)){
			System.out.println("I Won");
			stat.incrementComputerWins();
		}
		else{
			return false;
		}
	    return true;	
	}
	  
	/** If human goes first:
	 * We have 9 moves in total (max). 8 moves will be in a loop
	 * and the last human move is outside of the loop:
	 * 1. human goes first
	 * 2. If the returned value is true (human won), then boolean flag=true
	 *    and we break out of the loop. done indicates that the game is over.
	 * 3. If the game is not over, then it is computer's turn. 
	 * 4. If the returned value is true (comp won), then boolean flag=true
	 *    and we break out of the loop. done indicates that the game is over
	 * 5. Repeat the two steps above 3 more times. 
	 * 6. If the done is still false, then a human performs one more move and
	 * we check if the move led to the win or tie.    
	 */
	public void humanFirst(){
		boolean done=false;

		for (int i = 0; i < 4; i++) {	
			if (humanTurn()) {
				done = true;
				break;
			}
			if (compTurn()){
				done = true;
				break;
			}
		}  //end of for loop;
		
		//If the game is not settled in eight moves, the user 
		//is prompted to play the last step.
		if (!done){
		   	if (!humanTurn()) {
		   		System.out.println("\n\nA tie!");
		   		stat.incrementTies();
		   	}
		}
	}
	  
	/**
	 * Same logic as above, only the first computer's move happens before
	 * the loop. We do not need to check for winning combination here, since
	 * comp can't win after one move. 
	 * After the loop we check if the game is done. If not, report a tie and
	 * update statistics.
	 */
	public void compFirst(){
		boolean done = false;
		  
		//Keep the game running unless one player has win
		for (int i = 0; i < 4; i++) {	
			if (compTurn()) {
				done = true;
				break;
			}
			if (humanTurn()){
				done = true;
				break;
			}
		}
		
		//If the game is not settled in eight moves, the computer
		//plays the last step.
		if(!done){
			if (!compTurn()){
				System.out.println("\n\nA tie!");
				stat.incrementTies();
			}
		}
	}
	
	/**
	 * Execute the game until user choose to quit
	 * @param args: array for command line arguments
	 */
	public static void main(String[] args) {
		
		TicTacToe game = new TicTacToe();
		
		System.out.println("Welcome to TicTacToe!");
		System.out.format("This is %s, the Master of TicTacToe!\n", 
				game.getAIName());
		
		
		// input from the user, if he wants to play another game
		String playAgain=""; 

		// input from the user, if he wants to clear stats
		String clearStats=""; 
		
		System.out.println("Please Choose your symbol: ");
		String userChar = input.next();
		game.setSymbol(userChar.toCharArray()[0]);
		
		do {      //play until 'n' is pressed
			game.getBoard().clearBoard();   //clear the baord

			//Generate Random Assignment, determines who goes first;
			int move = game.whoStarts();
			if (move == 0) {
				System.out.format("\nI start first. I choose X and you get %c",
						game.getHumanSymbol());
				game.compFirst();
			}
			else{ 
				System.out.format("\nYou start first. You get %c and I get 0", 
						game.getHumanSymbol());
				game.humanFirst(); 
			}
		
			//Print statistics and ask if a user wants to repeat a game
			// If user enters 'y', ask to clear statistics
		      	//If user enters 'y', clear statistics and restart the game
		      	//If user enters 'n', continue without clearing
			//If user enters 'n', quit the game
			stat.printStats();
		
			System.out.println("\n\nPlay again?");
		
			playAgain = input.next();
			
			if (playAgain.charAt(0) != 'n'){  
				System.out.println("\nClear statistics?");
				clearStats = input.next();
				if (clearStats.charAt(0) == 'y'){
					stat.reset();
				}
			}

		} while(playAgain.charAt(0)!='n'); //done with the outer loop
	    
	    System.out.println("\nBye, see you later!");
	}
}