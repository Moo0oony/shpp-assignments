package com.shpp.p2p.cs.okhodakivskyi.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The Breakout game. User have to knock down the bricks from the top of the screen with a ball.
 * Ball navigates with a paddle and walls of an application.
 * Usually, there are 3 attempts that can be lost when player misses the ball with a paddle.
 * Counters below the paddle informs a user of the remaining attempts and bricks.
 * Game starts when user clicks on the paddle and gets control over it.
 */

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final Color PADDLE_COLOUR = Color.BLACK;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /** It's a bad idea to calculate brick width from APPLICATION_WIDTH */
    // private static final int BRICK_WIDTH =
    //        (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Controls the ball's speed and the duration of a pause that updates the ball's coordinates
     */
    private static final double BALL_SPEED = 3.0;
    private static final int PAUSE_TIME = 1000 / 120;

    /**
     * Specifies colours of the matrix bricks
     */
    private static final Color[] brickColour = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    /**
     * Defines fonts of the quantity attempts/bricks labels and win/lose labels
     */
    private static final Font FONT_INFORMATIONAL = new Font(Font.SANS_SERIF, Font.BOLD, 13);
    private static final Font FONT_RESULTING = new Font(Font.SANS_SERIF, Font.BOLD, 30);

    /**
     * Counts the number of bricks
     */
    private int numberOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;

    private GRect paddle;
    private GOval ball;
    private GObject selectedPaddle, collider;
    private GLabel attemptsLeft, bricksLeft, result;
    private double vx, vy;

    public void run() {
        addMouseListeners();
        putPaddle();
        makeMatrix();
        addInformationLabels();
        game();
    }

    /**
     * The main method combines the game's logic.
     * Game lasts until there are "lives" or bricks. The user lost a life when the ball reaches the bottom of the application.
     * The method puts a ball at the centre of the screen and launches it. This repeats until there are attempts or bricks.
     * If there are no attempts, it informs that the user has lost.
     * If there are no bricks left, it messages the user about his victory.
     * After defeat or victory, this method stops running the program.
     */
    private void game() {
        int turns = NTURNS;
        while (numberOfBricks > 0 || turns > 0) {
            brickCounterAndRemover(collider);
            attemptsLeft.setLabel("Attempts: " + turns);
            makeBall();
            launchBall(ball);
            turns--;
            if (numberOfBricks == 0) {
                isWin(true);
                break;
            }
            if (turns == 0) {
                isWin(false);
                break;
            }
        }
    }

    /**
     * Checks if the result is winning or not.
     * In any case, the method shows a label with the respective information.
     * At the same time, it removes the ball and paddle from the screen (this is made for aesthetic reasons).
     */
    private void isWin(boolean answer) {
        if (answer) {
            addWinLoseLabel();
            result.setLabel("You win!");
            result.setColor(Color.GREEN);
        } else {
            attemptsLeft.setLabel("Attempts: " + 0);
            addWinLoseLabel();
            result.setLabel("You lost ;-(");
            result.setColor(Color.BLUE);
        }
        remove(ball);
        remove(paddle);
    }

    /**
     * Makes the ball move according to the established rules of its speed and reflection from the objects.
     * The ball moves until it reaches the bottom border or if there are no bricks left.
     */
    private void launchBall(GOval ball) {
        waitForClick();
        setBallSpeed();
        while (ball.getY() < getHeight() && numberOfBricks != 0) {
            reflectionRules();
            ball.move(vx, vy);
            pause(PAUSE_TIME);
        }
    }

    /**
     * Controls directions of the ball.
     * Movement on X-coordinates receives a random value.
     * They can change in the opposite direction with a probability of 50%.
     * Movement on Y-coordinates is equal to the ball's speed.
     */
    public void setBallSpeed() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        vy = BALL_SPEED;
    }

    /**
     * Controls the changes of ball's behaviour when it touches an obstacle.
     * If it is a paddle or a top of the application -- it changes Y-coordinates to the opposite.
     * If it is a left or right border of the app - it changes X-coordinates to the opposite.
     * More information in the following method.
     */
    private void reflectionRules() {
        collider = getCollidingObject();
        if (collider == paddle) {
            vy = -Math.abs(vy);
        }
        if (ball.getX() >= getWidth() - 2 * BALL_RADIUS) {
            vx = -vx;
        }
        if (ball.getX() < 0) {
            vx = -vx;
        }
        if (ball.getY() <= 0) {
            vy = -vy;
        }
        brickCounterAndRemover(collider);
    }

    /**
     * Counts and removes bricks when the ball touches it.
     * The method checks if there is something with which the ball collides,
     * and checks if the object is NOT a paddle, labels of attempts and bricks.
     * Also, it extends the reflection rules -- changes the ball direction after it finds a brick.
     */
    private void brickCounterAndRemover(GObject collider) {
        if (collider != null && collider != paddle && collider != attemptsLeft && collider != bricksLeft) {
            if (ball.getX() + ball.getWidth() <= collider.getX() || ball.getX() >= (collider.getX() + collider.getWidth())) {
                vx = -vx;
            } else {
                vy = -vy;
            }
            remove(collider);
            //vy = -vy;
            numberOfBricks--;
            bricksLeft.setLabel("Bricks: " + numberOfBricks);
        }
    }

    /**
     * Defines the points of the "square-ball" that can be used as alerts of the ball's interaction with the objects.
     */
    private GObject getCollidingObject() {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        }
        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        }
        if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
            return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
        }
        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
        }
        return null;
    }

    /**
     * Makes a matrix of bricks that centred on the application windows depending on the actual app's window size.
     * Bricks width is too defined on the actual app's window size.
     */
    private void makeMatrix() {
        double brickWidth = (getWidth() - (NBRICKS_PER_ROW - 1.0) * BRICK_SEP) / NBRICKS_PER_ROW - BRICK_SEP;
        for (int rows = 1; rows <= NBRICK_ROWS; rows++) {
            for (int columns = 0; columns < NBRICKS_PER_ROW; columns++) {
                double brickX = getWidth() / 2.0
                        - (brickWidth + BRICK_SEP) * NBRICKS_PER_ROW / 2
                        + (brickWidth + BRICK_SEP) * columns;
                double brickY = BRICK_Y_OFFSET + (BRICK_SEP + BRICK_HEIGHT) * rows;
                makeBrick(brickX, brickY, brickWidth, BRICK_HEIGHT, chooseColour(rows));
            }
        }
    }

    /**
     * Uses an array of defined colours to colour the bricks.
     * Colours depend on the row number.
     */
    private Color chooseColour(int rows) {
        if (rows < 3) {
            return brickColour[0];
        } else if (rows < 5) {
            return brickColour[1];
        } else if (rows < 7) {
            return brickColour[2];
        } else if (rows < 9) {
            return brickColour[3];
        } else {
            return brickColour[4];
        }
    }

    /**
     * Adds two labels at the bottom of the app, which show the number of attempts and bricks left.
     */
    private void addInformationLabels() {
        attemptsLeft = new GLabel("Attempts: ", 20, getHeight() - 5);
        bricksLeft = new GLabel("Bricks: " + numberOfBricks, getWidth() / 5.0 * 4, getHeight() - 5);
        attemptsLeft.setFont(FONT_INFORMATIONAL);
        bricksLeft.setFont(FONT_INFORMATIONAL);
        add(bricksLeft);
        add(attemptsLeft);
    }

    /**
     * Add a label that informs user about the victory or defeat.
     */
    private void addWinLoseLabel() {
        result = new GLabel("Result", getWidth() / 3.0, getHeight() / 2.0);
        result.setFont(FONT_RESULTING);
        add(result);
    }

    /**
     * Draws the paddle - a rectangle used to reflect a ball to the top.
     */
    private void putPaddle() {
        double paddleX = getWidth() / 2.0 - PADDLE_WIDTH / 2.0;
        double paddleY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(PADDLE_COLOUR);
        paddle.setColor(PADDLE_COLOUR);
        add(paddle);
    }

    /**
     * Draws the ball - a sphere used to knock off bricks.
     */
    private void makeBall() {
        double ballX = getWidth() / 2.0 - BALL_RADIUS;
        double ballY = getHeight() / 2.0 - BALL_RADIUS;
        ball = new GOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    /**
     * Makes the bricks - parts of the matrix that are to be knocked off with the ball.
     */
    private void makeBrick(double brickX, double brickY, double brickWidth, double brickHeight, Color colour) {
        GRect brick = new GRect(brickX, brickY, brickWidth, brickHeight);
        brick.setFilled(true);
        brick.setFillColor(colour);
        brick.setColor(colour);
        add(brick);
    }

    /**
     * Selects the paddle in the cursor's position.
     */
    public void mousePressed(MouseEvent e) {
        selectedPaddle = getElementAt(e.getX(), e.getY());
    }

    /**
     * Controls the previously selected paddle with the user's mouse movements.
     * Sets the position of the cursor to the middle of the paddle.
     */
    public void mouseMoved(MouseEvent e) {
        if (selectedPaddle != null) {
            double newX = e.getX() - selectedPaddle.getWidth() / 2.0;
            if (newX >= 0 && newX < getWidth() - PADDLE_WIDTH) {
                paddle.setLocation(newX, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
            }
        }
    }
}