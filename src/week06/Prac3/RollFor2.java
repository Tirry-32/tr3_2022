package week06.Prac3;

public class RollFor2 {public static void main(String[] args) {

    PairOfDice dice;  // A variable that will refer to the dice.
    int rollCount;    // Number of times the dice have been rolled.

    dice = new PairOfDice();  // Create the PairOfDice object.
    rollCount = 0;

    /* Roll the dice until they come up snake eyes. */

    do {
        dice.roll();
        System.out.println("The dice come up " + dice );
        rollCount++;
    } while (dice.getTotal() != 2);

    /* Report the number of rolls. */

    System.out.println("\nIt took " + rollCount + " rolls to get a 2.");

    /* Now, generate an exception. */

    System.out.println();
    System.out.println("This program will now crash with an error");
    System.out.println("when it tries to set the value of a die to 42.");
    System.out.println();

    dice.setDie1(42);
    System.out.println(dice);  // This statement will not be executed!

}
}
