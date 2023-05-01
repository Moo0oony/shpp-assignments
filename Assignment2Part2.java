package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/* This class draws four black circles on the application corners and puts a white rectangle on each circle.
 * First, it calculates the diameter of the future circles depending on the canvas' width and height.
 * Second, it draws four circles on each corner of the application.
 * Lastly, it puts a white quadrangle on these circles.
 */
public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 500;                                         // Constants create app's window
    public static final int APPLICATION_HEIGHT = 500;                                        // of the given size.
    double diameter;

    public void run() {
        calculateDiameter();
        makeFourCircles();
        makeRectangle();
    }

    private void makeFourCircles() {                                                         // Draws four identical circles on each corner.
        makeCircle(0, getHeight() - diameter);                                         // They equally touch the application boundaries.
        makeCircle((double) getWidth() - diameter, 0);
        makeCircle((double) getWidth() - diameter, getHeight() - diameter);
        makeCircle(0, 0);
    }

    private void makeRectangle() {                                                           // Draws a white rectangle,
        double r = diameter / 2.0;                                                           // which starts from
        GRect rect = new GRect(r, r, getWidth() - diameter, getHeight() - diameter); // and stretches to the centres of the circles.
        rect.setFilled(true);
        rect.setFillColor(Color.WHITE);
        rect.setColor(Color.WHITE);
        add(rect);
    }

    private void makeCircle(double x, double y) {                                            // Draws a black circle
        GOval ring = new GOval(x, y, diameter, diameter);                                    // and puts it by the given parameters.
        ring.setFilled(true);
        ring.setColor(Color.black);
        add(ring);
    }

    private void calculateDiameter() {                                                       // Calculates a diameter
        if (getWidth() < getHeight()) {                                                      // depending on the width and height of the app.
            diameter = getWidth() / 3.0;
        } else {
            diameter = getHeight() / 3.0;
        }
    }
}