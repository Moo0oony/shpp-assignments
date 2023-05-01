package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws a pyramid based on the given constants.
 * It starts building from the top but eventually stands on the "height - 2" or "ground + 2" (for aesthetic purposes).
 * Colours constants are for easier customisation.
 */
public class Assignment3Part4 extends WindowProgram {
    private static final double BRICK_WIDTH = 50.0;
    private static final double BRICK_HEIGHT = 25.0;
    private static final int BRICKS_IN_BASE = 12;
    private static final Color BRICKS_COLOUR = Color.GRAY;
    private static final Color BRICKS_BORDER_COLOUR = Color.CYAN;

    public void run() {
        makePyramid();
    }

    /**
     * Draws a pyramid from the rectangles.
     * Starts from the top and eventually reaches the "ground + 2". +2 is made to make a bottom border of bricks visible.
     * Coordinates modify every iteration.
     * X-coordinate changes to the left on half of the brick width (or to the whole row - brick width).
     * Y-coordinate changes to the bottom by adding a brick height to every i-iteration.
     */
    private void makePyramid() {
        double x = getWidth() / 2.0 - BRICK_WIDTH / 2;
        double y = getHeight() - BRICKS_IN_BASE * BRICK_HEIGHT - 2;
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            for (int j = 0; j < (i + 1); j++) {
                makeBrick(x + BRICK_WIDTH * j - BRICK_WIDTH * i / 2, y + BRICK_HEIGHT * i);
            }
        }
    }

    /**
     * Makes a rectangle and its parameters (size and colours) are controlled by constants.
     *
     * @param x sets rectangle x-coordinate.
     * @param y sets rectangle y-coordinate.
     */
    private void makeBrick(double x, double y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(BRICKS_BORDER_COLOUR);
        brick.setFillColor(BRICKS_COLOUR);
        add(brick);
    }
}
