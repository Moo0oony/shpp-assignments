package com.shpp.p2p.cs.okhodakivskyi.assignment1;

import com.shpp.karel.KarelTheRobot;

/* This class extends the existing basic Karel class with several methods.
 * By using it, Karel finds and puts a beeper on a central cell if the given maze width is odd
 * and one central cell if the width of the maze is even.
 * Firstly, Karel puts a beeper on the starting position, secondly -- runs to the eastern edge and puts beeper there.
 * Then Karel starts to move between beepers, pick them and put closer to the centre.
 * When two beepers left on the centre, Karel puts one of them.
 */
public class Assignment1Part3 extends KarelTheRobot {
    @Override
    public void run() throws Exception {
        putBeeper();
        putTheEasternBeeper();
        messingWithBeepers();
        pickBeeper();
    }

    private void putTheEasternBeeper() throws Exception {   //Puts a beeper on the far eastern cell
        while (frontIsClear()) {                            //Precondition: Karel stands on the starting position, facing east
            move();                                         //Result: Karel moves towards the eastern edge
        }                                                   //Turns around to the west, puts a beeper on the eastern edge cell
        if (frontIsBlocked()) {                             //and moves one step to the next cell on the west
            turnAround();
            putBeeper();
            move();
        }
    }

    private void messingWithBeepers() throws Exception {    //Runs between beepers, from east to west, collect them and puts one closer to the centre
        while (noBeepersPresent()) {                        //Precondition: Karel stand on the second cell from the eastern edge
            move();                                         //Result: Karel moves to the first beeper on its way.
            if (beepersPresent()) {                         //When reaches one - picks it, turns around,
                pickBeeper();                               //and makes one step to the neighbouring cell, where it puts a beeper.
                turnAround();                               //After it puts one - Karel repeats the above-mentioned actions
                move();                                     //until there are two beepers on the centre
                putBeeper();
                move();
            }
        }
    }

    private void turnAround() throws Exception {            //Turns around (180 degrees)
        turnLeft();
        turnLeft();
    }
}
