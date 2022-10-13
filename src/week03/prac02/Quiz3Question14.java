package week03.prac02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz3Question14 {
    public static void main(String[] args){
        List<Integer> numbers = new ArrayList<>(
                Arrays.asList(23,433,1,3,2,34,1,3,4,2,4,2,42,42,42,3234,13,4,1)
        );
        int count42;  // The number of 42s in the array
        count42 = 0;
        int i;  // loop control variable
        for (i = 0; i < numbers.size(); i++ )
            if (numbers.get(i) == 42) {
                count42++;
            }
        System.out.println("There were " + count42 + " 42s in the array.");
    }
}
