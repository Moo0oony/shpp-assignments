package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* This class draws a flag of Ireland and puts a descriptive phrase below it.
 * Constants regulate the flag's size and set the exact colours of the Irish flag.
 */
public class Assignment2Part4 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 100;                                        // Control app's size.
    public static final int APPLICATION_HEIGHT = 100;

    private static final int HORIZONTAL_BORDERS = APPLICATION_WIDTH / 7;                    // Regulate spacing between a flag and app's borders.
    private static final int VERTICAL_BORDERS = APPLICATION_HEIGHT / 5;

    private static final int FLAG_WIDTH = APPLICATION_WIDTH - HORIZONTAL_BORDERS * 2;       // Regulate flag size.
    private static final int FLAG_HEIGHT = APPLICATION_HEIGHT - VERTICAL_BORDERS * 3;

    private static final Color PANTONE_347 = new Color(22, 155, 98);               // Sets exact colours of the Irish flag.
    private static final Color PANTONE_151 = new Color(255, 136, 62);

    public static final String FLAG_DESCRIPTION = "Flag of Ireland";                        // Sets the necessary phrase.
    public static final String FONT = "SansSerif-20";                                       // Sets phrase's font and font size.

    public void run() {
        makeIrishFlag();
        showFlagsDescription();
    }

    private void makeIrishFlag() {                                                          // Makes a flag from three stripes.
        int flagX = getWidth() / 2 - FLAG_WIDTH / 2;                                        // Variables specify coordinates of each stripe.
        int flagY = getHeight() / 2 - FLAG_HEIGHT / 2;                                      // Draws three equal to each other stripes.
        makeStripe(PANTONE_347, flagX, flagY);
        makeStripe(Color.WHITE, flagX + FLAG_WIDTH / 3, flagY);
        makeStripe(PANTONE_151, flagX + 2 * FLAG_WIDTH / 3, flagY);
    }

    private void showFlagsDescription() {                                                   // Puts a phrase "Flag of Ireland" below the flag.
        GLabel flagsName = new GLabel(FLAG_DESCRIPTION);                                    // The phrase always locates in the right-lower corner.
        flagsName.setFont(FONT);
        double labelX = getWidth() - flagsName.getWidth();
        double labelY = getHeight() - flagsName.getDescent();
        flagsName.setLocation(labelX, labelY);
        add(flagsName);
    }

    private void makeStripe(Color stripeColor, int flagX, int flagY) {                      // Draws a rectangle - a stripe of the flag.
        GRect stripe = new GRect(flagX, flagY, (double) FLAG_WIDTH / 3, FLAG_HEIGHT);   // Parameters specify its colour and coordinates.
        stripe.setColor(stripeColor);                                                       // Its width is equal to 1/3 of the whole flag width.
        stripe.setFillColor(stripeColor);                                                   // A rectangle can be filled with colour.
        stripe.setFilled(true);
        add(stripe);
    }
}