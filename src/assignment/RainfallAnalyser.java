package assignment;

import textio.TextIO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RainfallAnalyser {
    /**
     * Author:Tirivashe Ushamba
     * Version: 1.0
     * Date:11-11-2022
     * Description:
     */
    // define constants
    private static String OUTPUT_HEADER = "year,month,total,min,max";

    public static void main(String[] args) {
        System.out.println("Enter a filename: ");
        String filename = TextIO.getln();
        try {
            TextIO.readFile(filename);
            System.out.println("reading successfully");
            //generate output file
            String outputFile = generateOutputFile(filename);

            // save data to the output file
            generateMonthlyStatistics(outputFile, filename);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    private static void generateMonthlyStatistics(String outputFile,String filename) {
        /**
         * Declare the variables to be used.
         */
        float min = Float.NEGATIVE_INFINITY;
        float max = Float.POSITIVE_INFINITY;
        int year = 0;
        byte currentmonth = 1;
        byte previousmonth = 1;
        double totalmonthnlyRainfall = 0.0;
        double minmonthlyrainFall = Double.MAX_VALUE;
        double maxmonthlyRainfall = Double.MIN_VALUE;
        int lineCount = -1;
        int month;
        int day;
        int countNumOfRain = 0;
        float totalOfNum = 0;
        float rainAmong;
        float average;
        int checkColumn;

        try{
            Scanner scanner = new Scanner(new FileReader(filename));

            while (scanner.hasNextLine()){
                scanner.nextLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //testing : read second line
        int numOfRecords = 0;

        String[] record;
        String[][] newFile;
        int rowNum = 0;

        //read the first line and ignore it
        String line = TextIO.getln();
        checkColumn = line.split(",").length;

        // for testing only - it is not satisfied the requirements
        TextIO.writeFile(outputFile);

        // Output information for the fist row in the output file.
        TextIO.putln(OUTPUT_HEADER);

        /**
         * If the file to be analysed is empty .
         * Catch the error and abort reading the file.
         */
        if(TextIO.eof()){
            System.out.println("Empty file. Aborted");
            return;
        }
        //--
        newFile = new String[lineCount][8];
        // start extract the records
        //--

        while (!TextIO.eof()) {
            line = TextIO.getln().trim();
            numOfRecords++;

            //extract info ....
            record = line.split(",");
            //and read again
            if (record.length == 5) {
                String[] newRecord = Arrays.copyOf(record, record.length + 3);
                for (int i = 0; i < 3; i++) {
                    newRecord[record.length + i] = "0";
                }
                record = newRecord;
            } else if (record[6].equals("")) {
                record[6] = "0";
            }
            //copy array for add at 2D array
            System.arraycopy(record, 0, newFile[rowNum], 0, 8);
            rowNum++;
            // line = TextIO.getln().trim();
            record = line.split(",");
            //extract year, month, day, rainfall from records
            year = Integer.parseInt(newFile[0][2]);
            month = Integer.parseInt(newFile[0][3]);
            day = Integer.parseInt(newFile[0][4]);
            // verify that you are in a new month
            // then save the information to the output file
            // otherwise update total monthlyrainfall, min and max
            for (String[] strings : newFile) {

                // String array value to Integer or Float
                int rowOfYear = Integer.parseInt(strings[2]);
                int rowOfMonth = Integer.parseInt(strings[3]);
                int rowOfDay = Integer.parseInt(strings[4]);
                float rowOfRain = Float.parseFloat(strings[5]);

                //Group value by year and month
                if (rowOfYear == year && rowOfMonth == month && rowOfDay == day) {
                    totalOfNum += rowOfRain;   //sum rain among
                    if (rowOfRain < min) {
                        min = rowOfRain;      //check min rain among

                    }

                    //check max rain among
                    if (rowOfRain > max) {
                        max = rowOfRain;
                    }

                    //check rain
                    if (rowOfRain != 0) {
                        countNumOfRain++;
                    }
                    day++;

                    //Group value by year and month when month change
                } else if (rowOfYear == year && rowOfMonth == (month + 1) && rowOfDay != day) {

                    //total rain
                    rainAmong = totalOfNum;
                    //average rain among
                    average = totalOfNum / countNumOfRain;

                    //Float or Integer value to String
                    String rainAmongString = Float.toString(rainAmong);
                    String averageString = Float.toString(average);
                    String maxString = Float.toString(max);
                    String minString = Float.toString(min);
                    String yearString = Integer.toString(year);
                    String monthString = Integer.toString(month);

                    //Make a list of row
                    List<String> rowList = Arrays.asList(yearString, monthString, rainAmongString, averageString, minString, maxString);
                    String rowListString = String.join(",", rowList);

                    //test rowList
                    System.out.println(rowList);

                    //Put row in analysed file
                    TextIO.putln(rowListString);

                    //reset count rain day
                    countNumOfRain = 0;
                    //reset day
                    day = 2;
                    //add month
                    month++;
                    //reset min value
                    max = Float.NEGATIVE_INFINITY;
                    //reset max value
                    min = Float.POSITIVE_INFINITY;

                    //sum rain among
                    totalOfNum = rowOfRain;

                    //check min rain among
                    if (rowOfRain < min) {
                        min = rowOfRain;
                    }

                    //check max rain among
                    if (rowOfRain > max) {
                        max = rowOfRain;
                    }

                    //check rain
                    if (rowOfRain != 0) {
                        countNumOfRain++;
                        // update the current month as well

                    }
                }
            }
            //add Last row
            //total rain
            rainAmong = totalOfNum;
            //average rain among
            average = totalOfNum / countNumOfRain;

            //Float or Integer value to String
            String rainAmongString = Float.toString(rainAmong);
            String averageString = Float.toString(average);
            String maxString = Float.toString(max);
            String minString = Float.toString(min);
            String yearString = Integer.toString(year);
            String monthString = Integer.toString(month);

            //Make a list of row
            List<String> rowList = Arrays.asList(yearString, monthString, rainAmongString, averageString, minString, maxString);
            String rowListString = String.join(",", rowList);

            //test rowList
            System.out.println(rowList);

            //Put row in analysed file
            TextIO.putln(rowListString);


                }
    }
    private static String generateOutputFile(String filename) {
        System.out.println("filename = "+ filename);
        String[] fileParts = filename.trim().split("\\.");
        // testing only
        // String outputFile = fileParts[0] + "_analaysed.csv";
        //System.out.println(Arrays.toString(fileParts));
        return fileParts[0] + "_analysed.csv";
    }
}
