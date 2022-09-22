package week02;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[]args){
        //using scanner
        Scanner myobj = new Scanner(System.in);
        //clear standard input
        // myobj.nextLine();
        //start new input
        System.out.print("Enter name :");
        // string input
        String name = myobj.nextLine();
        //numerical input
        System.out.print("Enter age :");
        int age = myobj.nextInt();
        System.out.print("Enter salary:");
        double salary = myobj.nextDouble();

        //output to standard output
        System.out.print("Name :"+ name);
        System.out.print("Age :"+ age);
        System.out.print("Salary:" + salary);
    }
}
