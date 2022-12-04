package week08.Prac4;

import java.math.BigInteger;
import java.util.Scanner;

public class BigThreeN {


    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");


    public static void main(String[] args) {

        Scanner scanner = new Scanner( System.in );  // for reading user's input.

        String line;   // A line of input from the user.

        BigInteger N;  // The starting point for the 3N+1 sequence,
        //   as specified by the user.

        System.out.println("This program will print 3N+1 sequences for positive starting values");
        System.out.println("that you enter.  There is no pre-set limit on the number of");
        System.out.println("digits in the numbers that you enter.  The program will end when");
        System.out.println("you enter an empty line.");

        while (true) {
            System.out.println();
            System.out.println("Enter the starting value, or press return to end.");
            System.out.print("? ");
            line = scanner.nextLine().trim();
            if (line.length() == 0)
                break;
            try {
                N = new BigInteger(line);
                if (N.signum() == 1)
                    printThreeNSequence(N);
                else
                    System.out.println("Error:  The starting value cannot be less than or equal to zero.");
            }
            catch (NumberFormatException e) {
                System.out.println("Error:  \"" + line + "\" is not a legal integer.");
            }
        }

        System.out.println();
        System.out.println("Bye for now!");

    }  // end main()


    /**
     Count the terms in the 3N+1 sequence by printing it out starting at N. It is assumed that
     N is bigger than zero and that it is not null.
     */
    private static void printThreeNSequence(BigInteger N) {

        assert N != null && N.signum() == 1 : "Parameter value is illegal.";

        int numberOfTerms;  // The number of terms in the sequence.

        System.out.println("The 3N+1 sequence starting with " + N + " is:");

        // Print N as the first term of the sequence
        System.out.println(N.toString());
        numberOfTerms = 1;

        // Compute and print the next term
        while (!N.equals(ONE)) {
            if (N.testBit(0) == false) {
                // N is even.  Divide N by 2.
                N = N.divide(TWO);
            } else {
                // N is odd.  Multiply N by 3, then add 1.
                N = N.multiply(THREE);
                N = N.add(ONE);
            }
            System.out.println(N.toString());
            numberOfTerms++;
        }


        System.out.println("There were " + numberOfTerms + " terms in the sequence.");

    }
}
