package week06.Prac3;

public class DiceRollStats2 {
    static final int NUMBER_OF_EXPERIMENTS = 10000;

    private static PairOfDice dice = new PairOfDice();
    // A single pair of dice, which will be used for all
    // the experiments.


    public static void main(String[] args) {

        System.out.println("Dice Total   Avg # of Rolls   Stand. Deviation   Max # of Rolls");
        System.out.println("----------   --------------   ----------------   --------------");

        for ( int total = 2;  total <= 12;  total++ ) {
            StatCalc stats;  // An object that will compute the statistics.
            stats = new StatCalc();
            for ( int i = 0; i < NUMBER_OF_EXPERIMENTS; i++ ) {
                // Do the experiment of counting the number of rolls
                // required to roll the desired total, and enter the
                // number of rolls into stats' dataset.
                stats.enter( rollFor(total) );
            }
            System.out.printf("%6d", total);
            System.out.printf("%18.3f", stats.getMean());
            System.out.printf("%19.3f", stats.getStandardDeviation());
            System.out.printf("%14.3f", stats.getMax());
            System.out.println();
        }

    } // end main

    static int rollFor( int N ) {
        int rollCt = 0;  // Number of rolls made.
        do {
            dice.roll();
            rollCt++;
        } while ( dice.getTotal() != N );
        return rollCt;
    }

}
