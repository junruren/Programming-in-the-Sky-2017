package tic_tac_toe;

/**
* A class that implements a simple statistic tracking array
* @version 1.0
* @author  Zhimin Lin
* @since   2017-01-10
*/ 

public class Stats {

	//private array of size 3
	private int[] Stat = new int[3];

	/**
	 * creates an empty array 
	 */
	public Stats()
	{
	}

	/**
	 * Creates an array with specified values
	 * for JUnit testers
	 * @param first: assigned to the first cell
	 * @param second: assigned to the second cell
	 * @param third: assigned to the third cell
	 */
	public Stats(int first, int second, int third)
	{
		Stat[0] = first;
		Stat[1] = second;
		Stat[2] = third;
	}

	/**
	 * Calculates the number of games played
	 * @return The total number of played games
	 */
	public int getTotalGames() {
		return Stat[0]+Stat[1]+Stat[2];
	}

	/**
	 * Increments the number of games won by a user
	 */
	public void incrementUserWins() {
		Stat[0]++;
	}

	/**
	 * Increments the number of games won by a computer
	 */
	public void incrementComputerWins() {
		Stat[1]++;
	}

	/**
	 * Increments the number of ties between a user and a computer
	 */
	public void incrementTies() {
		Stat[2]++;
	}


	/**
	 *	 
	 * @param choice: depending on the value of choice 
	 * the corresponding average (percent) is returned:
	 * if choice is 0, return the average for a user
	 * if choice is 1, return the average for a computer
	 * if choice is 2, return the average for ties
	 * otherwise return -1
	 * @return percentage of won games or ties, depending on the 
	 * parameter choice.
	 */
	public int averageGames(int choice)
	{
		double ave;

		if (choice == 0){
			ave = (double)Stat[0] / getTotalGames();
		}
		else if (choice == 1){
			ave = (double)Stat[1] / getTotalGames();
		}
		else if (choice == 2){
			ave = (double)Stat[2] / getTotalGames();
		}
		else{
			ave = -1;
		}

		ave = ave * 100;
	
		int average = (int)ave;
	
		return average;
	}

	/**
	 * Prints the statistics message
	 */
	public void printStats()
	{
		System.out.println("  I won: "+averageGames(1) + "%   You won: " + averageGames(0)+ "%    We tied: "+ averageGames(2)+"%\n");
	}

	/**
	 * Resets the statistic array if the player wants to clear stats again
	 */
	public void reset() {
		Stat[0]=0;
		Stat[1]=0;
		Stat[2]=0;
	}

}

