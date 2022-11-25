package assignment;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import textio.TextIO;


public class RainfallVisualiser extends Application {
    /**
     * Draws a picture.  The parameters width and height give the size
     * of the drawing area, in pixels.
     */
    /**
     *
     * Author : Tirivashe Ushamba
     * Version: 1.0
     * Description:....
     */
    //------ End of Implementation details ------
    final String FILE_PATH = "resources/sample_analysed.csv";
    static final double margin = 20;
    public void drawPicture(GraphicsContext g, int width, int height) {


        // draw a rectangle
        //g.setFill(Color.YELLOWGREEN);
        //g.fillRect(100, 100, 200, 100);

        // draw an oval
        //g.fillOval(400, 400, 100, 100); // a circle
        //g.setStroke(Color.BLACK);
        //g.strokeOval(400, 400, 100, 100);

        double maxMonthlyRainfall = getMaxMonthlyRainfall(FILE_PATH);
        System.out.println("Max = " + maxMonthlyRainfall);

        // TODO: draw the x-axis and y-axis
        Point2D originPoint = new Point2D(margin, height - margin);

        // draw y-axis
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);
        g.strokeLine(originPoint.getX(), margin,
                originPoint.getX(), originPoint.getY());
        // draw x-axis
        g.strokeLine(originPoint.getX(), originPoint.getY(), width - margin, originPoint.getY());

        // TODO: draw the monthly totals as a bar chart
        double xAxisLength = width - 2.0 * margin;
        double yAxisLength = height - 2.0 * margin;
        double barWidth = xAxisLength / getNumOfRecords(FILE_PATH);
        double currentPointX = originPoint.getX();
        int numOfRecords = (int) getNumOfRecords(FILE_PATH);
        ;

        //testing: draw a bar with monthlyRainfall 702.40
        double barHeight = maxMonthlyRainfall* yAxisLength + 1;
        currentPointX += 30 * barWidth;
        double currentPointY = originPoint.getY() - barHeight;
        g.setFill(Color.RED);

        System.out.println("barWidth = " + barWidth);

        // set the starting point X-value

        //read and ignore the first line
        TextIO.readFile(FILE_PATH);
        String line = TextIO.getln();
        System.out.println(line);
        int asd = 0;

        verticalline(g,height , barWidth, numOfRecords);

        while (!TextIO.eof()){
            TextIO.getln();

            asd++;
            //set fill color alternately: red - blue - red -blue ...
            //get a record
            String[] record = TextIO.getln().trim().split(",");
            //get monthly total
            double monthlyTotal = Double.parseDouble(record[2]);
            // get barHeight/ Hint: need to scale down with YAxisHeight and maxMonthlyTotal (?)
            barHeight = (monthlyTotal/maxMonthlyRainfall)* yAxisLength;
            currentPointY = originPoint.getY()- barHeight;
            //draw a rectangle: fillRect
            if (asd%2 == 0){
                g.setFill(Color.RED);

            } else {
                g.setFill(Color.GREEN);
            }
            g.fillRect(currentPointX,currentPointY,barWidth,barHeight);
            // continue to the next point
            currentPointX += barWidth;
        }
    } // end drawPicture()


    double getMaxMonthlyRainfall(String filePath){
        TextIO.readFile(filePath);
        //read the first line and ignore
        String line = TextIO.getln();

        double maxMonthlyRainfall = 0.0;
        while (!TextIO.eof()){
            String[] record = TextIO.getln().trim().split(",");
            if (maxMonthlyRainfall < Double.parseDouble(record[2])){
                maxMonthlyRainfall = Double.parseDouble(record[2]);
            }
        }
        return maxMonthlyRainfall;
    }

    long getNumOfRecords(String filePath){
        TextIO.readFile(FILE_PATH);
        TextIO.getln();
        int numOfRecords = 0;
        while (!TextIO.eof()){
            TextIO.getln();
            numOfRecords++;
        }
        return numOfRecords;
    }

    private static void verticalline(GraphicsContext g, double height, double barWidth, int numOfRecords){
        for (int i = 0; i < numOfRecords; i++){
            g.strokeLine(margin+(barWidth*i), margin,margin+(barWidth*i),height-margin);
        }
    }
    //------ Implementation details: DO NOT EDIT THIS ------
    @Override
    public void start(Stage stage) {
        int width = 218 * 6 + 40;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
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
