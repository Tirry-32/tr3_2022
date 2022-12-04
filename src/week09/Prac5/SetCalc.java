package week09.Prac5;

import textio.TextIO;

import java.util.TreeSet;

public class SetCalc {
    public static void main(String[] args) {

        System.out.println("This program will compute union, intersection,");
        System.out.println("and set difference of sets of integers.");
        System.out.println("");
        System.out.println("");
        System.out.println("Enter set computations (press return to end):");

        while (true) {
            char ch;
            System.out.print("\n? ");
            TextIO.skipBlanks();
            if (TextIO.peek() == '\n') {
                // The input line is empty.
                // Exit the loop and end the program.
                break;
            }
            try {
                calc(); // Reads and processes one line of input.
            }
            catch (IllegalArgumentException e) {
                // An error was found in the input line.
                System.out.println("Error in input: " + e.getMessage());
            }
            TextIO.getln();  // Read and discard the rest of the line.
            // If there was no error, the only thing that
            // is discarded is the end-of-line character.
        }

    } // end main()

    private static void calc() {

        TreeSet<Integer> A, B;  // The two sets of integers.
        char op;                // The operator, +, *, or -.

        A = readSet(); // Read the first set.

        TextIO.skipBlanks();
        if (TextIO.peek() != '*' && TextIO.peek() != '+'
                && TextIO.peek() != '-')
            throw new IllegalArgumentException(
                    "Expected *, +, or  - after first set.");
        op = TextIO.getAnyChar(); // Read the operator.

        B = readSet(); // Read the second set.

        TextIO.skipBlanks();
        if (TextIO.peek() != '\n')
            throw new IllegalArgumentException("Extra unexpected input.");

        // Perform the operation.  This modifies set A to represent
        // the answer.  Display the answer by printing out A. The
        // output format for a set of integers is the same as the
        // input format used by this program.

        if (op == '+')
            A.addAll(B);     // Union.
        else if (op == '*')
            A.retainAll(B);  // Intersection.
        else
            A.removeAll(B);  // Set difference.

        System.out.print("Value:  " + A);

    } // end calc()

    private static TreeSet<Integer> readSet() {

        TreeSet<Integer> set = new TreeSet<Integer>();  // The set that will be read.

        TextIO.skipBlanks();
        if (TextIO.peek() != '[')
            throw new IllegalArgumentException("Expected '[' at start of set.");
        TextIO.getAnyChar(); // Read the '['.

        TextIO.skipBlanks();
        if (TextIO.peek() == ']') {
            // The set has no integers.  This is the empty set, which
            // is legal.  Return the value.
            TextIO.getAnyChar(); // Read the ']'.
            return set;
        }

        while (true) {
            // Read the next integer and add it to the set.
            TextIO.skipBlanks();
            if (! Character.isDigit(TextIO.peek()))
                throw new IllegalArgumentException("Expected an integer.");
            int n = TextIO.getInt(); // Read the integer.
            set.add( Integer.valueOf(n) );  // (Could have just said set.add(n)!)
            TextIO.skipBlanks();
            if (TextIO.peek() == ']')
                break;  // ']' marks the end of the set.
            else if (TextIO.peek() == ',')
                TextIO.getAnyChar(); // Read a comma and continue.
            else
                throw new IllegalArgumentException("Expected ',' or ']'.");
        }

        TextIO.getAnyChar(); // Read the ']' that ended the set.

        return set;

    } // end readSet()

}
