package week02;

import textio.TextIO;

public class FirstNameLastName {
    public static void main(String[] args) {

        String input;
        int space;
        String firstName;
        String lastName;

        System.out.println();
        System.out.println("Please enter your first name and last name, separated by a space.");
        System.out.print("? ");
        input = TextIO.getln();

        space = input.indexOf(' ');
        firstName = input.substring(0, space);
        lastName = input.substring(space + 1);

        System.out.println("Your first name is " + firstName + ", which has "
                + firstName.length() + " characters.");
        System.out.println("Your last name is " + lastName + ", which has "
                + lastName.length() + " characters.");
        System.out.println("Your initials are " + firstName.charAt(0) + lastName.charAt(0));

    }
    }
