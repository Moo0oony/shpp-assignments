package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class makes exponentiation based on the user-inputted numbers.
 * It asks for two numbers -- the base and the exponent.
 * Which formula is used depends on whether the exponent is positive or negative.
 */
public class Assignment3Part3 extends TextProgram {

    public void run() {
        double base = readDouble("Enter the base of an exponent (use floating-point number please): ");
        int exponent = readInt("Enter the exponent (use integer please): ");
        println("The result of exponentiation: " + base + " ^ " + exponent + " = " + raiseToPower(base, exponent));
    }

    /**
     * Checks whether the exponent is positive or negative and chooses the relevant formula.
     * After checking, this method makes a calculation.
     * If the exponent is equal to 0, it returns 1.
     *
     * @param base     is a number, which is to be raised to the power of the exponent.
     * @param exponent is a number of times base is to be multiplied on itself.
     * @return a result of the relevant calculations.
     */
    private double raiseToPower(double base, int exponent) {
        if (exponent > 0) {
            return positiveExponentiation(base, exponent);
        } else if (exponent < 0) {
            return 1 / positiveExponentiation(base, -exponent);
        } else {
            return 1;
        }
    }

    /**
     * A formula of exponentiation with the positive exponent.
     * The base number is multiplied on itself a number of times, which is determined by the exponent.
     *
     * @param base     is a number, which is to be raised to the power of the exponent.
     * @param exponent is a number of times base is to be multiplied on itself.
     * @return a result of the respecting calculations.
     */
    private double positiveExponentiation(double base, int exponent) {
        double result = base;
        if (exponent > 0) {
            for (int i = 1; i < exponent; i++) {
                result *= base;
            }
        }
        return result;
    }
}