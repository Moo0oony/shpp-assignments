package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This program asks users for their training minutes per day.
 * Then it calculates and informs users whether it is enough for maintaining cardiovascular health and normal blood pressure.
 */
public class Assignment3Part1 extends TextProgram {

    int cardioDays, pressureDays = 0;

    public void run() {
        askAndCountMinutes();
        concludeCardiovascularHealth();
        concludeBloodPressure();
    }

    /**
     * Asks users to input how many minutes they have been training every day during the week.
     * Then, it calculates how many days of required training time was kept.
     */
    private void askAndCountMinutes() {
        for (int i = 1; i <= 7; i++) {
            int minutes = readInt("How many minutes did you do on day " + i + "? ");
            if (minutes >= 30) {
                cardioDays++;
            }
            if (minutes >= 40) {
                pressureDays++;
            }
        }
    }

    /**
     * Calculates and informs users whether they kept a required training time (5 days of 30+ minutes of training)
     * for maintaining their cardiovascular health.
     */
    private void concludeCardiovascularHealth() {
        println("Cardiovascular health:");
        if (cardioDays < 5) {
            println("  You needed to train hard for at least " + (5 - cardioDays) + " more day(s) a week!");
        } else {
            println("  Great job! You've done enough exercise for cardiovascular health.");
        }
    }

    /**
     * Calculates and informs users whether they kept a required training time (3 days of 40+ minutes of training)
     * for maintaining normal blood pressure.
     */
    private void concludeBloodPressure() {
        println("Blood pressure:");
        if (pressureDays < 3) {
            println("  You needed to train hard for at least " + (3 - pressureDays) + " more day(s) a week!");
        } else {
            println("  Great job! You've done enough exercise to keep a low blood pressure.");
        }
    }
}
