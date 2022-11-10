package week06.Prac3;

import textio.TextIO;

public class AdditionQuizWithObjects {
    private static AdditionQuestion[] questions;  // The questions for the quiz
    private static int[] userAnswers;   // The user's answers to the ten questions.

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to the addition quiz!");
        System.out.println();
        createQuiz();
        administerQuiz();
        gradeQuiz();
    }

    private static void createQuiz() {
        questions = new AdditionQuestion[10];
        for ( int i = 0; i < 10; i++ ) {
            questions[i] = new AdditionQuestion();
        }
    }

    private static void administerQuiz() {
        userAnswers = new int[10];
        for (int i = 0; i < 10; i++) {
            int questionNum = i + 1;
            System.out.printf("Question %2d:  %s ", //Asks the user each of the ten quiz questions and gets the user's answers
                    questionNum, questions[i].getQuestion());//The answers are stored in an array, which is created in this subroutine.

            userAnswers[i] = TextIO.getlnInt();
        }
    }

    private static void gradeQuiz() {
        System.out.println();
        System.out.println("Here are the correct answers:");
        int numberCorrect = 0;
        int grade;
        for (int i = 0; i < 10; i++) {
            int questionNum = i + 1;
            System.out.printf("   Question %2d:  %s  Correct answer is %d  ",
                    questionNum, questions[i].getQuestion(), questions[i].getCorrectAnswer());
            if ( userAnswers[i] == questions[i].getCorrectAnswer() ) {
                System.out.println("You were CORRECT.");
                numberCorrect++;
            }
            else {
                System.out.println("You said " + userAnswers[i] + ", which is INCORRECT.");
            }
        }
        grade = numberCorrect * 10;
        System.out.println();
        System.out.println("You got " + numberCorrect + " questions correct.");
        System.out.println("Your grade on the quiz is " + grade);
        System.out.println();
    }

}
