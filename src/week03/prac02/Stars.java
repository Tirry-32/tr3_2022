package week03.prac02;

public interface Stars {
    static void stars(int numberOfStars) {
        for (int i = 0; i < numberOfStars; i++) {
            System.out.print('*');
        }
        System.out.println();  // output carriage return after the *'s
    }
}
