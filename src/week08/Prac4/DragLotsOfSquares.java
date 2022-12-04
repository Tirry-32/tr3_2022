package week08.Prac4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;


/**
 An application that enables clicking to add squares to a canvas. The user's clicked location serves as the
 square's centre. Every square is the same size (100-by-100). They are transparent to 50% and have arbitrary
 colours. The user can drag a square by right-clicking or shift-clicking it. A square gets removed from the
 list of squares when it is dragged off the canvas.
 */

public class DragLotsOfSquares extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     The information required to draw a square, including the colour of the square and its center's
     coordinates, is included in an object of type SquareData.
     */
    private static class SquareData {
        double x,y;  // Location of center of square.  The size is always 100-by-100.
        Color color; // The color of the square
    }


    private ArrayList<SquareData> squares;  // Info for all squares in the picture.

    private Canvas canvas;  // The canvas where the sqaures are drawn.


    /*
       The start method sets up the GUI.  It adds mouse event handlers to
       the canvas to implement adding and dragging squares.
     */
    public void start(Stage stage) {

        squares = new ArrayList<SquareData>();

        canvas = new Canvas(640,480);
        draw(); // Will just fill canvas with background color.

        canvas.setOnMousePressed( e -> mousePressed(e) );
        canvas.setOnMouseDragged( e -> mouseDragged(e) );
        canvas.setOnMouseReleased( e -> mouseReleased(e) );

        Pane root = new Pane(canvas);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Left-click to add a square. Right-click to drag.");
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Draw the canvas, showing all squares in their current positions.
     */
    private void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.rgb(230,255,230)); // light green
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        g.setLineWidth(2);
        g.setStroke(Color.BLACK);
        for ( SquareData squareData: squares ) {
            g.setFill( squareData.color );
            g.fillRect( squareData.x - 50, squareData.y - 50, 100, 100);
            g.strokeRect( squareData.x - 50, squareData.y - 50, 100, 100);
        }
    }

    private boolean dragging;      // Set to true when a drag is in progress.

    private SquareData draggedSquare;  // When a drag is in progress, this is
    // the square that is being dragged.

    private double offsetX, offsetY;  // Offset of mouse-click coordinates from the
    //   center of the square that is being dragged.

    /**
     When a user clicks their mouse on the canvas, something happens.
     If there is a mouse-over square, dragging it begins with a shift-click or right-click.
     A new square will be added using the mouse point as its centre with subsequent clicks.
     Only when the user shift-clicks or right-clicks a square does a drag operation start.
     */
    public void mousePressed(MouseEvent evt) {

        if (dragging)  // Exit if a drag is already in progress.
            return;

        double x = evt.getX();  // Location where user clicked.
        double y = evt.getY();

        if (evt.isShiftDown() || evt.getButton() == MouseButton.SECONDARY) {
            // If user shift-clicked a square, start dragging it.


            for (int i = squares.size() - 1; i >= 0; i--) {
                SquareData squareData = squares.get(i);
                double cx = squareData.x; // (cx,cy) is the center of the square
                double cy = squareData.y;
                if ( x >= cx - 50 && x <= cx + 50 && y >= cy - 50 && y <= cy + 50) {
                    dragging = true;
                    draggedSquare = squareData;
                    offsetX = x - cx;
                    offsetY = y - cy;
                    break;  // stop as soon as we find  square containing (x,y)
                }
            }
        }
        else { // Add a new square with center at (x,y)
            SquareData squareData = new SquareData();
            squareData.x = x;
            squareData.y = y;
            /**
             * Pick a colour at random for the square. Pick a random alpha value between 0.5 and 1.0
             * and a random RGB component between 0.0 and 1.0. This will result in a square that is
             * just a little bit transparent.
             */
            squareData.color = Color.color(
                    Math.random(), Math.random(), Math.random(), 0.5 + 0.5*Math.random() );
            squares.add( squareData );
            draw();  // Redraw the whole picture to show the new square.
            //  (Could have just drawn it here instead!)
        }
    }


    /**
     When the user lets off of the mouse button, dragging ends.
     The square is removed from the list of squares if the user completely removed
     it off the canvas. (Since that won't change the appearance of the image, the canvas is not redrawn.)
     */
    public void mouseReleased(MouseEvent evt) {
        if ( ! dragging )
            return;

        /* To test if the square has moved completely off the canvas,
         * test that its center is at least 50 pixels outside the canvas. */

        if (draggedSquare.x > canvas.getWidth() + 50
                || draggedSquare.x < -50
                || draggedSquare.y > canvas.getHeight() + 50
                || draggedSquare.y < -50) {
            // Square is completely off the canvas, so remove it!
            squares.remove(draggedSquare);
            // For testing, to make sure square is actually deleted:
            System.out.println("Removed square; list size = " + squares.size());
        }

        dragging = false;  // drag operation has ended.
        draggedSquare = null;
    }


    /**
     When a user drags the mouse, react. Exit if a square is not being moved.
     In any other case, move the dragged square so that it is in line with where
     the mouse pointer is. Keep in mind that the square's centre is now situated
     in the same relative location to the mouse as it was when the user first dragged it.
     */
    public void mouseDragged(MouseEvent evt) {
        if ( ! dragging )
            return;
        double x = evt.getX();
        double y = evt.getY();
        draggedSquare.x = x - offsetX;
        draggedSquare.y = y - offsetY;
        draw();  // Redraw picture to show square in new positions.
    }
}
