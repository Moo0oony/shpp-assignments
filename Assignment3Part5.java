package com.shpp.p2p.cs.okhodakivskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;
import acm.util.RandomGenerator;

/**
 * This class simulates a game with coin flipping.
 * The game starts with $1 in a bank.
 * The Program continuously flips a coin. If it is "heads" -- winnings multiply.
 * The game ends when the amount of cash reaches the required sum to win (regulates with a constant).
 */
public class Assignment3Part5 extends TextProgram {
    private static final int REQUIRED_SUM_TO_WIN = 20;

    public void run() {
        println("It took " + gameAndCashCounter() + " games to earn " + REQUIRED_SUM_TO_WIN);
    }

    /**
     * Counts a number of games and cash until required sum to win is not reached.
     * Additionally, it informs about the total winnings.
     *
     * @return a number of games.
     */
    private int gameAndCashCounter() {
        int games = 0;
        int cash = 0;
        while (cash < REQUIRED_SUM_TO_WIN) {
            cash += playGame();
            println("Your total is $" + cash);
            games++;
        }
        return games;
    }

    /**
     * Actual algorithm of the game.
     * Starting bank is set to $1.
     * Flips a coin and if it is "heads" -- multiplies the winnings.
     *
     * @return a number of winnings.
     */
    private int playGame() {
        int bank = 1;
        while (flipCoin() == 1) {
            bank *= 2;
        }
        println("This game, you earned $" + bank);
        return bank;
    }

    /**
     * Imitates coin flipping.
     * 0 means "tails".
     * 1 means "heads".
     *
     * @return "heads" and "tails".
     */
    private int flipCoin() {
        RandomGenerator rg = RandomGenerator.getInstance();
        return rg.nextInt(0, 1);
    }
}