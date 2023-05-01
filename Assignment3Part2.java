package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class demonstrates the Collatz conjecture.
 * Firstly, it asks the user for a positive integer.
 * Then, it performs calculations until there is a 1.
 * During the calculations, it informs the user about the results.
 * When 1 is found, Jim Morrison says goodbye to you :)
 */
public class Assignment3Part2 extends TextProgram {
    int inputNumber;

    public void run() {
        inputPositiveInteger();
        showCollatzConjecture();
    }

    /**
     * Asks a user for a positive integer and continues to ask until a user enters one :)
     */
    private void inputPositiveInteger() {
        while (!(inputNumber > 0)) {
            inputNumber = readInt("Enter a positive integer number: ");
        }
    }

    /**
     * Makes calculations while the number is not equal to 1.
     * If the number is even, it divides number into two.
     * If odd -- it multiplies by three and adds one.
     * After each operation, it shows the initial number and subsequent result.
     * When 1 is found, the method reports the end of calculations.
     */
    private void showCollatzConjecture() {
        while (inputNumber != 1) {
            int updatedNumber;
            if (inputNumber % 2 == 0) {
                updatedNumber = inputNumber / 2;
                println(inputNumber + " is even so I take half: " + updatedNumber);
            } else {
                updatedNumber = inputNumber * 3 + 1;
                println(inputNumber + " is odd so I make 3n + 1: " + updatedNumber);
            }
            inputNumber = updatedNumber;
        }
        println("\nThis is the end, beautiful friend." +
                "\nThis is the end, my only friend, the end.");
    }
}
