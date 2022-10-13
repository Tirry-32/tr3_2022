package week03.prac02;

public class Quiz3Question15 {
    public static void main(String[] args){
        double max;    // maximum value from the array
        double min;    // minimum value from the array
        int i;         // for-loop variable

        double[] raceTimes = new double[20];
        max = min = raceTimes[0];  // start with both equal to the first element
        for ( i = 1; i < raceTimes.length; i++ ) {
            if ( raceTimes[i] > max )
                max = raceTimes[i];
            if ( raceTimes[i] < min ) {
                min = raceTimes[i];
            }
        }
        // the range of the array, max - min
        double range = max - min;
        System.out.println("The range is " + range);
    }
}
