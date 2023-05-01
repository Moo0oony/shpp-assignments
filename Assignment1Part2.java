package com.shpp.p2p.cs.okhodakivskyi.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * This class extends the existing basic Karel class with several methods.
 * By using it, Karel makes vertical towers through 4 cells, while moving east.
 * First, it makes tower on the south-western edge, checking the presence of beepers.
 * Second, while there are no obstacles, it moves to the east, makes a tower and returns back to south
 * It stops when there is no way to east
 */
public class Assignment1Part2 extends KarelTheRobot {
    @Override
    public void run() throws Exception {
        towerMaker();
        while (frontIsClear()) {
            runKarelRun();
            towerMaker();
        }
    }

    private void towerMaker() throws Exception {        //Makes a tower by checking if beepers are there .
        turnLeft();                                     //Precondition: Karel looks to the east and stands on the column,
        while (frontIsClear()) {                        //where a tower has to be made.
            if (noBeepersPresent()) {                   //Karel turns left to the north and started checking the presence of beepers/
                putBeeper();                            //If there is no beeper on the cell - it puts it, else - moves further.
                move();                                 //While near the northern wall, it puts or ignores a cell by using the towerFinisher() method
            } else {                                    //After that, Karel moves back towards the southern wall using the turnBack() method
                move();
            }
        }
        towerFinisher();
        turnBack();
    }

    private void runKarelRun() throws Exception {       //Moves Karel 4 steps towards the next tower
        for (var i = 0; i < 4; i++) {                   //Precondition: Karel looks to the east after it just made a tower and returned from the northern end
            move();                                     //Result: Karel stands on the cell where another tower is going to be made
        }
    }

    private void towerFinisher() throws Exception {     //Checks if the tower is completed and if not, completes it
        if (frontIsBlocked() && noBeepersPresent()) {   //Precondition: Karel stands on the most northern cell of a tower facing north
            putBeeper();                                //Result: if there is no beeper, it puts one.
        }
    }

    private void turnBack() throws Exception {          //Moves Karel to the southern end of a column
        turnAround();                                   //Precondition: Karel stands directing to the northern wall
        while (frontIsClear()) {                        //Result: Karel turns around and moves to the southern edge.
            move();                                     //After Karel finished its journey, it turns left (to the east)
        }
        turnLeft();
    }

    private void turnAround() throws Exception {        //Turns Karel 180 degrees
        turnLeft();
        turnLeft();
    }
}
