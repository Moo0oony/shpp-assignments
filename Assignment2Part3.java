package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* This class draws two paws of the cartoon creature.
 * The number of given constants regulate their size as well as app's window size.
 */
public class Assignment2Part3 extends WindowProgram {

    private static final double FIRST_TOE_OFFSET_X = 0;                     // Constants control the relative positions of the
    private static final double FIRST_TOE_OFFSET_Y = 20;                    // three toes to the upper-left corner of the pawprint.
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    private static final double HEEL_OFFSET_X = 20;                         // The position of the heel relative to the
    private static final double HEEL_OFFSET_Y = 40;                         // upper-left corner of the pawprint.

    private static final double TOE_WIDTH = 20;                             // Each toe is an oval with this width and height.
    private static final double TOE_HEIGHT = 30;

    private static final double HEEL_WIDTH = 40;                            // The heel is an oval with this width and height.
    private static final double HEEL_HEIGHT = 60;

    public static final int APPLICATION_WIDTH = 270;                        // These constants create app's window
    public static final int APPLICATION_HEIGHT = 220;                       // of the given size.

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        drawToe(x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y);
        drawToe(x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y);
        drawToe(x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y);
        drawHeel(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y);
    }

    private void drawToe(double x, double y) {                              // Draws a small black "toe" (an oval) with the given constants.
        GOval toe = new GOval(x, y, TOE_WIDTH, TOE_HEIGHT);                 // Method's parameters specify its placing.
        toe.setFilled(true);                                                // Its size is specified by the abovementioned constants.
        toe.setColor(Color.BLACK);
        add(toe);
    }

    private void drawHeel(double x, double y) {                             // Draws a black "heel" (an oval), which is bigger than a "toe".
        GOval heel = new GOval(x, y, HEEL_WIDTH, HEEL_HEIGHT);              // Method's parameters specify its placing.
        heel.setFilled(true);                                               // Its size is specified by the abovementioned constants.
        heel.setColor(Color.BLACK);
        add(heel);
    }
}