package week06.Prac3;

public class PairOfDice {
    private int die1;   // Number showing on the first die.
    private int die2;   // Number showing on the second die.


    public PairOfDice() {
        roll();  // Call the roll() method to roll the dice.
    }

    public void roll() {
        // Roll the dice by setting each die to be a random number between 1 and 6.
        die1 = (int)(Math.random()*6) + 1;
        die2 = (int)(Math.random()*6) + 1;
    }

    public int getDie1() {
        return die1; // Return the number showing on the first die.

    }

    /**
     * Set the value of the first die.  Throws an IllegalArgumentException
     * if the value is not in the range 1 to 6.
     */
    public void setDie1( int value ) {
        if ( value < 1 || value > 6 )
            throw new IllegalArgumentException("Illegal dice value " + value);
        die1 = value;
    }

    public int getDie2() {
        return die2;// Return the number showing on the second die.

    }

    /**
     * Set the value of the second die.  Throws an IllegalArgumentException
     * if the value is not in the range 1 to 6.
     */
    public void setDie2( int value ) {
        if ( value < 1 || value > 6 )
            throw new IllegalArgumentException("Illegal dice value " + value);
        die2 = value;
    }


    public int getTotal() {
        return die1 + die2;// Return the total showing on the two dice.
    }

    /**
     * Return a String representation of a pair of dice, where die1
     * and die2 are instance variables containing the numbers that are
     * showing on the two dice.
     */
    public String toString() {
        if (die1 == die2)
            return "double " + die1;
        else
            return die1 + " and " + die2;
    }
}
