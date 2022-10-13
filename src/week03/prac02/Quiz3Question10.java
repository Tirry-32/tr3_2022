package week03.prac02;

public class Quiz3Question10 {
    public static void main(String[] args) {
        try {
            String s1 = "2";
            String s2 = "3";
            int n1, n2;
            int sum ;
            n1 = Integer.parseInt(s1);
            n2 = Integer.parseInt(s2);
            sum = n1 + n2;   // (If an exception occurs, we don't get to this point.)
            System.out.println("The sum is " + sum);
        }
        catch ( NumberFormatException e ) {
            System.out.println("Error!  Unable to convert strings to integers.");
        }
    }
}
