package com.shpp.p2p.cs.okhodakivskyi.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * This class extends the existing basic Karel class with several methods.
 * The first method turns and moves Karel towards a beeper.
 * The second causes Karel to pick the beeper up and turns Karel around.
 * The third returns Karel back to the starting position and everyone is happy :)
 */
public class Assignment1Part1 extends KarelTheRobot {
    @Override
    public void run() throws Exception {
        reachTheBeeper();
        pickUpAndTurnAround();
        turnBack();
    }

    /* Turns Karel right and moves one step towards the centre of the "house",
     * turns Karel left in the direction of a beeper,
     * then directs Karel straight to a cell with a beeper
     */
    private void reachTheBeeper() throws Exception {
        turnRight();
        move();
        turnLeft();
        while (noBeepersPresent()) {
            move();
        }
    }

    /* Causes Karel to pick up the beeper and turns Karel around */
    private void pickUpAndTurnAround() throws Exception {
        pickBeeper();
        turnAround();
    }

    /* Moves Karel straight while there is no obstacle (towards the inner wall of the "house"),
     * after which Karel turns right and takes one step to the starting position
     */
    private void turnBack() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnRight();
        move();
    }

    /* Turns Karel around (180 degrees) */
    private void turnAround() throws Exception {
        for (var i = 0; i < 2; i++) {
            turnLeft();
        }
    }

    /* Turns Karel to the right (90 degrees) */
    private void turnRight() throws Exception {
        for (var i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}