package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import acm.graphics.GLabel;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws an animated matrix of random-coloured boxes.
 * Constants regulate the number of rows and columns of the matrix, their size, and spacing between the boxes.
 * Other constants regulate the maximum time of the animation and a pause needed to draw a square.
 * The first method adds a timer to the top-left corner of the app.
 * The second method draws squares that rotate with spacings between them.
 * Additionally, this method checks if the animation time does not exceed the maximum time.
 * and stops animation when time is exceeded (at 5.146 on my laptop).
 */
public class Assignment3Part6 extends WindowProgram {
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;
    private static final double BOX_SIZE = 40;
    private static final double BOX_SPACING = 10;
    private static final double SIZE_AND_SPACING = BOX_SIZE + BOX_SPACING;
    private static final int MAX_TIME = 5000;
    private static final int MOMENT = MAX_TIME / (NUM_ROWS * NUM_COLS);

    GLabel timeTracker;
    double x, y, timer = 0;

    public void run() {
        addLabel();
        drawAnimatedMatrix();
    }

    /**
     * Draws the animated matrix of boxes.
     * Colours of the boxes are randomised.
     * "Timer" checks the time to the end of the animation. "Moment" pauses time to draw one square.
     * x sets square x-position, y sets square y-position.
     */
    private void drawAnimatedMatrix() {
        while (timer < MAX_TIME) {
            for (int i = 0; i < NUM_ROWS; i++) {
                if (timer > MAX_TIME) {
                    break;
                }
                for (int j = 0; j < NUM_COLS; j++) {
                    timer += MOMENT;
                    timeTracker.setLabel("Time: " + (timer / 1000));
                    pause(MOMENT);
                    if (timer > MAX_TIME) {
                        break;
                    }
                    x = getWidth() / 2.0 - (SIZE_AND_SPACING) * NUM_COLS / 2 + (SIZE_AND_SPACING) * j;
                    y = getHeight() / 2.0 - (SIZE_AND_SPACING) * NUM_ROWS / 2 + (SIZE_AND_SPACING) * i;
                    Color color = RandomGenerator.getInstance().nextColor();
                    drawBox(x, y, color);
                }
            }
        }
    }

    /**
     * Draws a square, which size is specified by the constants.
     *
     * @param x     sets square x-position.
     * @param y     sets square y-position.
     * @param color sets colour to fill a square.
     */
    private void drawBox(double x, double y, Color color) {
        GRect b = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        b.setFilled(true);
        b.setColor(color);
        add(b);
    }

    /**
     * Adds a timer to the top-left corner.
     */
    private void addLabel() {
        timeTracker = new GLabel("Time: ", 25, 25);
        add(timeTracker);
    }
}