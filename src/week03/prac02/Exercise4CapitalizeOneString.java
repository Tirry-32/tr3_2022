package week03.prac02;

import textio.TextIO;

public class Exercise4CapitalizeOneString {
    public static void main(String[] args) {
        String input_line;  // Input line sting from the user.
        System.out.println("Please enter a line of text.");
        input_line = TextIO.getln();
        System.out.println();
        System.out.println("Capitalized version:");
        printCapitalized( input_line );
    }

    static void printCapitalized( String str ) {
        char char1;       // One of the characters in str.
        char char_before;   // The character that comes before char1 in the string.
        int i;         // A position in str, from 0 to str.length()-1.
        char_before = '.';  // Prime the loop with any non-letter character.
        for ( i = 0;  i < str.length();  i++ ) {
            char1 = str.charAt(i);
            if ( Character.isLetter(char1)  &&  ! Character.isLetter(char_before) )
                System.out.print( Character.toUpperCase(char1) );
            else
                System.out.print( char1 );
            char_before = char1;  // prevCh for next iteration is ch.
        }
        System.out.println();
    }

}
