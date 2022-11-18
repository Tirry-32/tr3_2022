package assignment;

import javafx.application.Application;
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
    public void drawPicture(GraphicsContext g, int width, int height) {
        // testing file input
        //read the first line and ignore it
        String line = TextIO.getln();
        System.out.println(line);
        //read second line
        // option1 : var record = TextIO.getln().trim().split(",");
        String[] record = TextIO.getln().trim().split(";");
//        for (int i = 0 ; i < record.length; i++){
//            System.out.println(record[i]);
//        }
//        System.out.println(Arrays.toString(record));
        for (String item:record){
            System.out.println(item);
        }
        // end of file testing

        // draw rectangle
        g.setFill(Color.YELLOWGREEN);
        g.fillRect(100,100,200,100);

        // draw oval
        g.fillOval(400,400,100,100);
        g.setStroke(Color.BLACK);
        g.strokeOval(400,400,100,100);
        // TODO: draw the x-axis and y-axis
        // TODO: draw the monthly totals as a bar chart
    } // end drawPicture()



    @Override

    //------ Implementation details: DO NOT EDIT THIS ------
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
//        System.out.print("Enter path: ");
//        var path = TextIO.getln();

        var path = "resources/sample_analysed.csv";
        TextIO.readFile(path);
        launch();
    }
}
