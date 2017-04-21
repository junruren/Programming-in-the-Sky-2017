package tic_tac_toe;

/**
 * Represents a board with 9 slots for the TicTacToe game
 * 
 * @author Zhimin Lin
 * @version 1.0
 * @since 2017-04-18
 *
 */
public class Board {
    private char[][] board;
    private static final int NUM_ROW = 3;
    private static final int NUM_COL = 3;
    private static final int BOARD_SIZE = 5;
  
    /*
     * Constructor. Initialize the array representing the board
     */
    public Board(){
    	board = new char[NUM_ROW][NUM_COL];
    }
  
    /**
     * Print the board with all the symbols on it.
     */
    public void printBoard(){
	    int row = 0, col = 0, gridRow = 0;
	    boolean isRow = true;
	    
	    while (row < BOARD_SIZE){
		    col = 0;
		    
		    if (!isRow){
		    	System.out.println("---------");
		    }
		    
		    while (isRow && col < NUM_ROW){
			    System.out.print(board[gridRow][col]);
			    if (col != 2){
				    System.out.print(" | ");
			    }
			    else{
				    System.out.println("");
				    gridRow++;
			    }
			    col++;
		    }
		    row++;
		    isRow = !isRow;
	    }
    }
    
    /**
     * Clear out all the symbols on the board by assigning
     * a space character to all slots
     */
    public void clearBoard(){
    	int row, col;
    	//TODO: Topic 7 & 9, write a nested for loop to set all elements in board to ' '
    }
    
	/**
	 * Check if a slot on the board is occupied
	 * 
	 * @param row The row of the slot
	 * @param col The column of the slot
	 * @return true if the slot is occupied, false otherwise
	 */
    public boolean slotIsEmpty(int row, int col){
    	return board[row][col] == ' ';
    }
    
    /**
     * Return the symbol in the designated slot.
     * 
     * @param row The row of the slot
     * @param col The column of the slot
     * @return the symbol in the slot specified
     */
    public char getSlot(int row, int col){
    	return board[row][col];
    }
    
    /**
     * Set the symbol in the specified slot to the given symbol.
     * 
     * @param row The row of the slot
     * @param col The column of the slot
     * @param symbol The symbol to be put into the slot
     */
    public void setSlot(int row, int col, char symbol){
    	board[row][col] = symbol;
    }
}
