package week03.prac02;

public class Exercise4RollFor2 {
    public static void main(String[] args) {
        int numberOfRolls;  // Number of rolls to get a 2.
        numberOfRolls = rollFor(2);
        System.out.println("It took " + numberOfRolls + " rolls to get snake eyes.");
    }  // end main()

    public static int rollFor( int N ) {
        if ( N < 2 || N > 12 )
            throw new IllegalArgumentException("Impossible total for a pair of dice.");
        int die1, die2;  // Numbers between 1 and 6 representing the dice.
        int roll;        // Total showing on dice.
        int rollCt;      // Number of rolls made.
        rollCt = 0;
        do {
            die1 = (int)(Math.random()*6) + 1;
            die2 = (int)(Math.random()*6) + 1;
            roll = die1 + die2;
            rollCt++;
        } while ( roll != N );
        return rollCt;
    }
}
