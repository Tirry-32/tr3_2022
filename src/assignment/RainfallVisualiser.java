package assignment;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import textio.TextIO;

import static textio.TextIO.getln;


public class RainfallVisualiser extends Application {
    /**
     * Draws a picture.  The parameters width and height give the size
     * of the drawing area, in pixels.
     */
    /**
     *
     * Author : Tirivashe Ushamba
     * Version: 1.0
     * Description: The Program will show bar chat summarizing mothly rainfall from the rainfall analysis program .
     * The user should enter the name and path of the file to be shown on the bar chat
     */
    static final double margin = 50;
    final static Integer rotationVertical = 90;
    public void drawPicture(GraphicsContext g, int width, int height, String filePath) {



        //String filePath = getFIleName();
        double maxMonthlyRainfall = getMaxMonthlyRainfall(filePath);
        System.out.println("Max = " + maxMonthlyRainfall);

        // TODO: draw the x-axis and y-axis
        Point2D originPoint = new Point2D(margin, height - margin);

        // draw y-axis
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.4);
        g.strokeLine(originPoint.getX(), margin,
                originPoint.getX(), originPoint.getY());
        // draw x-axis
        g.strokeLine( originPoint.getX(), originPoint.getY(), width - margin, originPoint.getY());

        // TODO: draw the monthly totals as a bar chart

        double xAxisLength = width - 4.0 * margin;
        double yAxisLength = height - 4.0 * margin;
        double barWidth = xAxisLength / getNumOfRecords(filePath);
        double currentPointX = originPoint.getX();
        int numOfRecords = (int) getNumOfRecords(filePath);
        int numOfRows = (int)(yAxisLength/margin);
        int ratioOfVertivalLines = (int)(maxMonthlyRainfall/yAxisLength) +1;


        //testing: draw a bar with monthlyRainfall 702.40
        double barHeight = maxMonthlyRainfall* yAxisLength + 1;
        currentPointX += 30 * barWidth;
        double currentPointY = originPoint.getY() - barHeight;
        g.setFill(Color.RED);

        System.out.println("barWidth = " + barWidth);

        // set the starting point X-value

        //read and ignore the first line
        TextIO.readFile(filePath);
        String line = getln();
        System.out.println(line);
        double barColor = 360 * Math.random();
        /**
 * Drawing the vertical and horizontal grid lines to read the bar chat easily
 */
        verticalline(g,height , barWidth, numOfRecords);
        horizontalLine(g, width, ratioOfVertivalLines, height, numOfRows);

        /**
         * File name in the bar chat
         * Horizontal label showing the months
         * Vertical label name
         *
         */
        titleLabel(g, filePath, width);
        verticalUnitLabel(g, height);
        horizontalUnitLabel(g, width, height);



        while (!TextIO.eof()){


            barColor++;
            //set fill color alternately: red - blue - red -blue ...
            //get a record\
            line = getln().trim();
            String[] record = line.split(",");
            //get monthly total
            double monthlyTotal = Double.parseDouble(record[2]);
            // get barHeight/ Hint: need to scale down with YAxisHeight and maxMonthlyTotal (?)
            barHeight = (monthlyTotal/maxMonthlyRainfall)* yAxisLength;
            currentPointY = originPoint.getY()- barHeight;
            //draw a rectangle: fillRect
            g.setFill(Color.hsb(barColor, 4.0, 1.0));
            g.fillRect(currentPointX,currentPointY,barWidth,barHeight);
            // continue to the next point
            currentPointX += barWidth;
        }
        horizontalLabel(g, height, filePath, barWidth);
    } // end drawPicture()

    private String getFIleName() {
        System.out.print("Enter a filename: " );
        return getln();
    }

    private void horizontalLabel(GraphicsContext g, int height,String filePath ,double barWidth) {
        String previousYear = null;
        String currentYear;
        double gapBetweenLabel = 0;

        gapBetweenLabel += margin;

        g.setFont(Font.font("Times New Roman",5));

        //read file and ignore first line
        TextIO.readFile(filePath);
        getln();

        while (!TextIO.eof()){

            //and read again
            String line = getln().trim();

            //extract info...
            String[] record = line.split(",");

            currentYear = record[0];

            //draw X axis label (YY/01)
            if (!currentYear.equals(previousYear)) {
                String yearAndMonth = record[0] + "." + record[1];
                g.setTextAlign(TextAlignment.CENTER);
                g.setTextBaseline(VPos.TOP);
                g.setFill(Color.BLACK);
                g.fillText(yearAndMonth, gapBetweenLabel, height - margin);
            }
            previousYear = currentYear;

            gapBetweenLabel += barWidth;
        }
    }

    private void titleLabel(GraphicsContext g, String FILE_PATH, int width) {
        String[] fileName;
        String barGraphName = FILE_PATH;

        g.setFont(Font.font("Times New Roman",14));

        //Classified only file name
        if(barGraphName.contains("/")){
            fileName = FILE_PATH.split("/");
            barGraphName = fileName[1];
        }
        if(barGraphName.contains("_")){
            fileName = barGraphName.split("_");
            barGraphName = fileName[0];
        }

        //draw title label
        g.fillText(barGraphName, (double) width/2, (double) margin/4);
    }

    private void horizontalUnitLabel(GraphicsContext g, int width, int height) {
        g.setFont(Font.font("Calibre",20));

        //x axis unit label
        g.fillText(" Unit : Year.Month", (double) width/2, height- (double) (margin/1.2));
    }

    private void verticalUnitLabel(GraphicsContext g, int height) {
        g.setFont(Font.font("Calibre",15));

        // y axis unit label
        g.rotate(rotationVertical);
        g.fillText("Unit : millimeter(mm)", (double) height/2, (double)-margin/2);
        g.rotate(-rotationVertical);
    }

    private void horizontalLine(GraphicsContext g, int width, int ratioOfY, int height, int numOfRows) {
        //draw horizontal line
        for(int i = 0; i < numOfRows+1; i++){

            g.strokeLine(margin, margin + (margin*i), width-margin, margin + (margin*i));
            g.setTextAlign(TextAlignment.CENTER);
            g.setTextBaseline(VPos.TOP);

            // draw Y axis label
            String count = Integer.toString((int) (margin * i * ratioOfY));
            if(i > 0){
                g.rotate(rotationVertical);
                g.fillText(count, height - margin - (margin*i), -margin);
                g.rotate(-rotationVertical);
            }
        }
    }


    double getMaxMonthlyRainfall(String FILE_PATH){
        TextIO.readFile(FILE_PATH);
        //read the first line and ignore
        String line = getln();

        double maxMonthlyRainfall = 0.0;
        while (!TextIO.eof()){
            String[] record = getln().trim().split(",");
            if (maxMonthlyRainfall < Double.parseDouble(record[2])){
                maxMonthlyRainfall = Double.parseDouble(record[2]);
            }
        }
        return maxMonthlyRainfall;
    }

    long getNumOfRecords(String FILE_PATH){
        TextIO.readFile(FILE_PATH);
        getln();
        int numOfRecords = 0;
        while (!TextIO.eof()){
            getln();
            numOfRecords++;
        }
        return numOfRecords;
    }

    private static void verticalline(GraphicsContext g, double height, double barWidth, int numOfRecords){
        for (int i = 0; i < numOfRecords; i++){
            g.strokeLine(margin+(barWidth*i), margin,margin+(barWidth*i),height-margin);
        }
    }


    @Override
    public void start(Stage stage) {
        int width = 218 * 6 + 40;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        String filePath = getFIleName();
        drawPicture(canvas.getGraphicsContext2D(), width, height,filePath);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser");
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch();
    }
}
