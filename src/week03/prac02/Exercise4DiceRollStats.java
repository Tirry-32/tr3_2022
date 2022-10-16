package week03.prac02;

public class Exercise4DiceRollStats {
    public static final int NUMBER_OF_EXPERIMENTS = 10000;

    public static void main(String[] args) {
        double average;  // The average number of rolls to get a given total.
        System.out.println("Total On Dice     Average Number of Rolls");
        System.out.println("-------------     -----------------------");
        for ( int dice = 2;  dice <= 12;  dice++ ) {
            average = getAverageRollCount( dice );
            System.out.printf("%10d%22.4f\n", dice, average);
            // Use 10 spaces to output dice, and use 22 spaces to output
            // average, with 4 digits after the decimal.
        }
    }

    public static double getAverageRollCount( int roll ) {
        int rollCountThisExperiment;  // Number of rolls in one experiment.
        int rollTotal;  // Total number of rolls in all the experiments.
        double averageRollCount;  // Average number of rolls per experiment.
        rollTotal = 0;
        for ( int i = 0;  i < NUMBER_OF_EXPERIMENTS;  i++ ) {
            rollCountThisExperiment = rollFor( roll );
            rollTotal += rollCountThisExperiment;
        }
        averageRollCount = ((double)rollTotal) / NUMBER_OF_EXPERIMENTS;
        return averageRollCount;
    }

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
