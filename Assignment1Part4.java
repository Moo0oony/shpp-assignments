package com.shpp.p2p.cs.okhodakivskyi.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * This class extends the existing basic Karel class with several methods.
 * It makes a "checking" line from west to east, from which Karel will later make other lines from south to north.
 * Firstly, it puts a beeper on the first southwestern cell.
 * Then, the first main method makes a line of beepers by checking if is there a beeper on the cell.
 * If there is none -- makes a step to create a gap between beepers. On the following cell it places one.
 * After Karel reaches east, it turns left to the north and makes a line following the logic explained previously.
 * When Karel reaches north, the second main method turns Karel around and moves back to the southern edge.
 * Then, with the third main method, Karel makes one step to the western cell. After that, it makes another line in this cell.
 * It continues until Karel makes a line on the most western cells and turns back.
 */
public class Assignment1Part4 extends KarelTheRobot {
    @Override
    public void run() throws Exception {
        putBeeper();
        makeALine();
        turnLeft();
        while (frontIsClear()) {
            makeALine();
            goSouth();
            toTheWesternCell();
        }
    }

    private void makeALine() throws Exception {         //Makes a line of beepers until there is an obstacle
        while (frontIsClear()) {                        //Precondition: first use -- Karel is facing east on the starting position; further uses -- Karel facing north from the southern edge (after toTheWesternCell() method).
            if (noBeepersPresent()) {                   //Checks whether a beeper is present,
                move();                                 //firstly makes a move to make an empty cell between beepers
                putBeeper();                            //Puts beeper when the gap was previously made
            } else {
                move();                                 //or just moves if there is a beeper on the cell
            }
        }
    }

    private void goSouth() throws Exception {           //Turns back from the northern point to the southern
        turnAround();                                   //Precondition: Karel is staying on the northern edge, facing north after just made a line
        while (frontIsClear()) {                        //Turns around to the south and moves until an obstacle
            move();
        }
    }

    private void toTheWesternCell() throws Exception {  //Moves to the following cell to make another line of beepers.
        turnRight();                                    //Precondition: Karel just returned from north, facing south
        if (frontIsClear()) {                           //Result: Turns right after, if front is clear makes one step to the west
            move();
            turnRight();                                //and turns right
        }
    }

    private void turnAround() throws Exception {        //Turns around (180 degrees)
        turnLeft();
        turnLeft();
    }

    private void turnRight() throws Exception {         //Turns right (90 degrees)
        turnAround();
        turnLeft();
    }
}