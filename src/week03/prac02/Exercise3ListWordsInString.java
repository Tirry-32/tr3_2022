package week03.prac02;

import textio.TextIO;

public class Exercise3ListWordsInString {
    public static void main(String[] args) {
        String line;    // A line of text, typed in by the user.
        int i;          // Position in line, from 0 to line.length() - 1.
        char ch;        // One of the characters in line.
        boolean didCR;  // Set to true if the previous output was a carriage return.
        System.out.println("Enter a line of text.");
        System.out.print("->");
        line = TextIO.getln();
        System.out.println();
        didCR = true;
        for ( i = 0;  i < line.length();  i++ ) {
            ch = line.charAt(i);
            if ( Character.isLetter(ch) ) {
                System.out.print(ch);
                didCR = false;
            }
            else {
                if ( didCR == false ) {
                    System.out.println();
                    didCR = true;
                }
            }
        }
        System.out.println();
    }
}
