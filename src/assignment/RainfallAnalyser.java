package assignment;

import textio.TextIO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RainfallAnalyser {
    /**
     * Author:Tirivashe Ushamba
     * Version: Alpha release
     * Date:27-11-2022
     * Description:This Program will analyse the given data files to produce the year,month,total Ranafall , mininmum
     * Rainfall and the maximum rainfall from the given file .
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
        double rainAmong;
        double totalMonthlyRainfall = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;


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


        while (!TextIO.eof()){

            //and read again
            line = TextIO.getln().trim();

            //extract info...
            newFile = line.split(",");

            //add miss data to 0
            String[] newRecord = Arrays.copyOf(newFile, checkColumn);
            for (int i = 0; i < checkColumn; i++){
                if(newRecord[i] == null || newRecord[i].equals("")){
                    newRecord[i] = "0";
                }
            }
            newFile = newRecord;

            //update year, month, rain among
            year = Integer.parseInt(newFile[2]);
            currentMonth = Byte.parseByte(newFile[3]);
            rainAmong = Double.parseDouble(newFile[5]);

            //add rain among to total rain among
            totalMonthlyRainfall += rainAmong;

            //check min rain among
            if (rainAmong < min) {
                min = rainAmong;
            }

            //check max rain among
            if (rainAmong > max) {
                max = rainAmong;
            }

            //save each month data and reset max, min
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
