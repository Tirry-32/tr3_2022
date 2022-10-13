package week03.prac02;

public class Quiz4Question13 {
        static double average( double[] numbers, int N ) {
            if ( N < 1 ) {
                throw new IllegalArgumentException("Can't find an average of " +
                        N + " numbers.");
            }
            if ( N > numbers.length ) {
                throw new IllegalArgumentException( N +
                        " is more than the length of the array." );
            }
            double sum = 0;  // the sum of the N numbers
            for ( int i = 0; i < N; i++ ) {
                sum = sum + numbers[i];  // add the i-th number to the sum
            }
            return sum/N;  // Return the average as the value of the function.
        }
    }


