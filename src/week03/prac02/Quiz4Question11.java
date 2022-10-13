package week03.prac02;

public class Quiz4Question11 {
    static int countChars( String str, char searchChar ) {
        // Count the number of times searchChar occurs in
        // str and return the result.
        int i;     // A position in the string, str.
        char ch;   // A character in the string.
        int count; // Number of times searchChar has been found in str.
        count = 0;
        for ( i = 0;  i < str.length();  i++ ) {
            ch = str.charAt(i);  // Get the i-th character in str.
            if ( ch == searchChar )
                count++;
        }
        return count;
    }
}
