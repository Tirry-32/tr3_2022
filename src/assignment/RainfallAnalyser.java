package assignment;

import textio.TextIO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RainfallAnalyser {
    /**
     * Author:Tirivashe Ushamba
     * Version: 1.0
     * Date:11-11-2022
     * Description:
     */
    // define constants
    private static final String OUTPUT_HEADER = "year,month,total,min,max";

    public static void main(String[] args) {

        System.out.println("Enter a filename: ");

        String filename = TextIO.getln();

        try {
            TextIO.readFile(filename);
            System.out.println("Reading successfully");

            //generate output file
            String outputFile = generateOutputFile(filename);

            // save data to the output file
            generateMonthlyStatistics(outputFile, filename);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }



    private static void generateMonthlyStatistics(String outputFile,String filename) {
        /**
         * Declare the variables to be used.
         */
        String[] newFile;
        int checkColumn;
        int year;
        byte currentMonth ;
        byte previousMonth = 1;
        float rainAmong;
        double totalMonthlyRainfall = 0.0;
        double min = Double.NEGATIVE_INFINITY;
        double max = Double.POSITIVE_INFINITY;


        try{
            Scanner scanner = new Scanner(new FileReader(filename));

            while (scanner.hasNextLine()){
                scanner.nextLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

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


        while (!TextIO.eof()) {
            //and read again
            line = TextIO.getln().trim();

            //extract info ....
            newFile = line.split(",");

            // Set value 0 for empty strings in the file
            String[] newData = Arrays.copyOf(newFile, checkColumn);
            for (int i = 0; i < checkColumn; i++){
                if(newData[i] == null || newData[i].equals("")){
                    newData[i] = "0";
                }
            }
            newFile = newData;



            //extract year, month, day, rainfall from records
            year = Integer.parseInt(newFile[2]);
            currentMonth = (byte) Integer.parseInt(newFile[3]);
            rainAmong = Integer.parseInt(newFile[5]);



            // verify that you are in a new month
            // then save the information to the output file

            /** otherwise update total monthlyrainfall, min and max**/
            totalMonthlyRainfall += rainAmong;
            if (rainAmong < min) {
                min = rainAmong;
            }
            if (rainAmong > max) {
                max = rainAmong;
            }

            if (currentMonth != previousMonth){
                saveMonthlyRecord(year, previousMonth, max, min, totalMonthlyRainfall);

                previousMonth = currentMonth;
                totalMonthlyRainfall = rainAmong;
                max = Double.NEGATIVE_INFINITY;
                min = Double.POSITIVE_INFINITY;

                if (rainAmong < min) {
                    min = rainAmong;
                }

                //check max rain among
                if (rainAmong > max) {
                    max = rainAmong;
                }
            }

        }
    }

    private static void saveMonthlyRecord(int year, byte previousMonth, double max, double min, double totalMonthlyRainfall) {

        // rounding  off numbers to 2 significant figures
        String yearString = Integer.toString(year);
        String monthString = Byte.toString(previousMonth);
        String maxString = String.format("%.02f", max);
        String minString = String.format("%.02f", min);
        String totalString = String.format("%.02f", totalMonthlyRainfall);

        //make one string
        String row = yearString + "," + monthString + "," + maxString + "," + minString + "," + totalString;

        //add row to _analysed file
        TextIO.putln(row);
    }
    private static String generateOutputFile(String filename) {
        String[] fileParts = filename.split("\\.");
        return fileParts[0] + "_analysed.csv";
    }
}
