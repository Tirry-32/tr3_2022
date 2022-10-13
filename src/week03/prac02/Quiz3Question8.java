package week03.prac02;

import textio.TextIO;

public class Quiz3Question8 {
    public static void main(String[] args){

        System.out.print("Enter a number");
        int a_number = TextIO.getlnInt();
        if (a_number % 2 == 0  ){
            System.out.println("Even number");
        }
        else {
            System.out.println("Odd Number ");
        }


    }
}
