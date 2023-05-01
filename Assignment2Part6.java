package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/* This class draws a caterpillar with the length defined by a user.
 * This caterpillar consists of circles of the same diameter. The diameter depends on the size of the application window.
 * Odd parts of the caterpillar are lower than even parts.
 */
public class Assignment2Part6 extends WindowProgram {
    public static final int LENGTH_OF_THE_CATERPILLAR = 6;      // Controls a number of circles -- parts of the caterpillar. Can be changed by a user.
    double diameter;

    public void run() {
        calculateDiameter();
        makeCaterpillar();
    }

    private void makeCaterpillar() {                            // Makes a sequence of circles similar to a caterpillar.
        double circleX = 0;                                     // Caterpillar's size is defined by the respective constant.
        double circleY = diameter / 3.0;                        // It draws a circle by defined coordinates.
        double step = diameter / 3.0;                           // Every iteration circles change coordinates --
        for (int i = 0; i < LENGTH_OF_THE_CATERPILLAR; i++) {   // next circle is drawn on the 1/3 part of the previous circle (a step),
            makeCircle(circleX, circleY);                       // changing its Y-coordinates up and down.
            circleX += diameter / 1.7;
            if (i % 2 == 1) {
                circleY += step;
            } else {
                circleY -= step;
            }
        }
    }

    private void makeCircle(double x, double y) {               // Makes a circle with a red border and green body.
        GOval circle = new GOval(x, y, diameter, diameter);     // Parameters should specify circle's coordinates.
        circle.setFilled(true);
        circle.setColor(Color.RED);
        circle.setFillColor(Color.GREEN);
        add(circle);
    }

    private void calculateDiameter() {                          // Calculates diameter of the future circle
        if (getWidth() < getHeight()) {                         // using the actual dimensions of the window.
            diameter = getWidth() / 4.0;
        } else {
            diameter = getHeight() / 4.0;
        }
    }
}