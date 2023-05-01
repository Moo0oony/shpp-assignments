package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* This class draws a matrix of black boxes.
 * Constants regulate number of rows and columns of the matrix, and size and spacing between the boxes.
 * The main method draws black squares that rotates with spacings between them.
 */
public class Assignment2Part5 extends WindowProgram {

    private static final int NUM_ROWS = 5;                          // The number of rows and columns in the grid, respectively.
    private static final int NUM_COLS = 6;
    private static final double BOX_SIZE = 40;                      // The width and height of each box.
    private static final double BOX_SPACING = 10;                   // The horizontal and vertical spacing between the boxes.

    public void run() {
        makeBarcelonaQuarters();
    }

    private void makeBarcelonaQuarters() {                          // The main method, which makes a matrix with boxes,
        for (int i = 0; i < NUM_ROWS; i++) {                        // similar to the quarters of Barcelona.
            for (int j = 0; j < NUM_COLS; j++) {                    // These two loops make rows and a columns.
                double quarterX = (double) getWidth() / 2           // Every box rotates with the spacing between boxes.
                        - (BOX_SIZE + BOX_SPACING) * NUM_COLS / 2   // Every iteration two variables specify coordinates of each box.
                        + (BOX_SIZE + BOX_SPACING) * j;
                double quarterY = (double) getHeight() / 2
                        - (BOX_SIZE + BOX_SPACING) * NUM_ROWS / 2
                        + (BOX_SIZE + BOX_SPACING) * i;
                makeRectangle(quarterX, quarterY);
            }
        }
    }

    private void makeRectangle(double x, double y) {                // Draws a black square, which size specified with the constants.
        GRect rectangle = new GRect(x, y, BOX_SIZE, BOX_SIZE);      // Parameters specify its coordinates.
        rectangle.setFilled(true);
        rectangle.setColor(Color.BLACK);
        add(rectangle);
    }
}