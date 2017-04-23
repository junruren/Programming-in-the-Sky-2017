package tic_tac_toe;

/**
 * Represents a dice by generating random numbers between 1 and 6.
 *
 * @author Zhimin Lin
 * @version 1.0
 * @since 2017-04-14
 *
 */
public class Dice_Sample {
    private static final int MAX = 6;

    /**
     * Simulate a toss of the dice.
     *
     * @return a random integer between 1 and 6
     */
    public int toss(){
        int result = (int)(Math.random() * MAX + 1);
        return result;
    }

    /**
     * Simulate a toss of the dice. Print the result
     * to standard output
     */
    public void tossDice(){
        int result = (int)(Math.random() * MAX + 1);
        System.out.println(result);
    }
}
