package week03.prac02;

public class Quiz3Question9 {
    public static void main(String[] args){
        int x ;
        int y;
        // The do while loop ensures that we get different values of x and y
        do {
            x = (int)(10*Math.random() + 1);
            y = (int)(10*Math.random() + 1);

        }
        while (x == y );
        System.out.println("Value 1: "+ x);
        System.out.println("Value 2: " + y);
    }
}
