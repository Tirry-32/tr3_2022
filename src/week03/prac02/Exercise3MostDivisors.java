package week03.prac02;

public class Exercise3MostDivisors {
    public static void main(String[] args) {
        int N;            // One of the integers whose divisors we have to count.
        int maxDivisors;  // Maximum number of divisors seen so far.
        int numWithMax;   // A value of N that had the given number of divisors.
        maxDivisors = 1;  // Start with the fact that 1 has 1 divisor.
        numWithMax = 1;
        for ( N = 2;  N <= 10000;  N++ ) {
            int D;  // A number to be tested to see if it's a divisor of N.
            int divisorCount;
            divisorCount = 0;
            for ( D = 1;  D <= N;  D++ ) {  // Count the divisors of N.
                if ( N % D == 0 )
                    divisorCount++;
            }
            if (divisorCount > maxDivisors) {
                maxDivisors = divisorCount;
                numWithMax = N;
            }
        }
        System.out.println("From integers between 1 and 10000,");
        System.out.println("The maximum number of divisors is " + maxDivisors);
        System.out.println("A number with " + maxDivisors + " divisors is " + numWithMax);
    }
}// end public class
