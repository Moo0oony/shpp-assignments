package com.shpp.p2p.cs.okhodakivskyi.assignment2;

import com.shpp.cs.a.console.TextProgram;

/* This class takes three numbers and finds the roots of a quadratic equation.
 * First, it asks for three numbers from a user and checks if one of them is not equal to zero.
 * If not, it calculates a discriminant and, depending on the discriminant's value, it solves the equation.
 */
public class Assignment2Part1 extends TextProgram {

    double a = readDouble("Please enter a: ");                  // Asks for three numbers from a user.
    double b = readDouble("Please enter b: ");
    double c = readDouble("Please enter c: ");
    double discriminant;

    public void run() {
        if (a != 0) {                                               // Checks if the first number is not equal to zero.
            calculateDiscriminant();                                // If not -- it continues calculations.
            solveQuadraticEquation();
        } else {
            println("Value 'a' cannot be equal to 0");              // If yes -- it stops the program.
        }
    }

    private void solveQuadraticEquation() {                         // Checks the value of a discriminant
        if (discriminant > 0) {                                     // and chooses an appropriate formula.
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            println("There are two roots: " + x1 + " and " + x2);
        } else if (discriminant == 0) {
            double x1 = -b / (2 * a);
            println("There is one root: " + x1);
        } else if (discriminant < 0) {
            println("There are no real roots");
        }
    }

    private void calculateDiscriminant() {                          // Finds a discriminant.
        discriminant = Math.pow(b, 2) - 4 * a * c;
    }
}