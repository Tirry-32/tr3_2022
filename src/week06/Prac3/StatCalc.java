package week06.Prac3;

public class StatCalc {
    private int count;   // Number of numbers that have been entered.
    private double sum;  // The sum of all the items that have been entered.
    private double squareSum;  // The sum of the squares of all the items.
    private double max = Double.NEGATIVE_INFINITY;  // Largest item seen.
    private double min = Double.POSITIVE_INFINITY;  // Smallest item seen.

    public void enter(double num) {
        count++;
        sum += num;
        squareSum += num*num;
        if (num > max)
            max = num;
        if (num < min)
            min = num;
    }

    public int getCount() {
        return count;// Return the number of items that have been entered into the dataset.
    }

    public double getSum() {
        return sum;// Return the sum of all the numbers that have been entered.

    }

    public double getMean() {
        return sum / count;//Return the average of all the items that have been entered.
    }

    public double getStandardDeviation() {
        double mean = getMean();
        return Math.sqrt( squareSum/count - mean*mean );//Return the standard deviation of all the items that have been entered.
    }
    public double getMin() {
        return min; //  Return the smallest item that has been entered.

    }

    public double getMax() {
        return max;//Return the largest item that has been entered.

    }

}
