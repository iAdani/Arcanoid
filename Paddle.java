import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * A paddle controlled by the player.
 */
public class Paddle implements Sprite, Collidable {

    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int BOR_SIZE = 5; // Border width
    private static final double DEFAULT_DX = 6; // Puddle moving speed
    private static final double DEF_SPEED = 4.5; // Default speed for the balls


    private Rectangle rectangle;
    private Color color;
    private GUI gui;
    private double dx;



    private KeyboardSensor keyboard;
    private GameLevel gameLevel;
    private List<HitListener> hitListeners;


    /**
     * Constructor.
     *
     * @param rect the rectangle
     */
    public Paddle(Rectangle rect) {
        this.rectangle = rect;
        this.color = Color.GRAY;
        dx = DEFAULT_DX;
    }

    /**
     * Set a GUI.
     *
     * @param g new GUI
     */
    public void setGui(GUI g) {
        this.gui = g;
    }

    /**
     * Set a new velocity.
     *
     * @param d new dx
     */
    public void setSpeed(double d) {
        this.dx = d;
    }

    /**
     * Get the keyboard key pressed.
     * @return keyboard key
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rectangle.getUpperLeft().getX();
        sideHit();

        if (x > BOR_SIZE) {
            this.rectangle.setUpperLeft(new Point(x - dx, this.rectangle.getUpperLeft().getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(BOR_SIZE, this.rectangle.getUpperLeft().getY()));
        }

    }

    /**
     * Moving the paddle to the right.
     */
    public void moveRight() {
        double x = this.rectangle.getUpperLeft().getX(); // This rectangle x location
        double borderX = WINDOW_WIDTH - BOR_SIZE - rectangle.getWidth(); // The game border x location
        sideHit();

        if (x < borderX) {
            this.rectangle.setUpperLeft(new Point(x + dx, this.rectangle.getUpperLeft().getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(borderX, this.rectangle.getUpperLeft().getY()));
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double width = this.rectangle.getWidth(); // The paddle width
        double leftX = this.rectangle.getUpperLeft().getX(); //The left x location of the paddle
        double rightX = this.rectangle.getUpperRight().getX(); //The right x location of the paddle

        if (collisionPoint.getX() >= leftX) {
            // Return the new Velocity for this collision point
            if (collisionPoint.getX() <= leftX + (width / 5)) {
                return Velocity.fromAngleAndSpeed(300, DEF_SPEED);
            }
            if (collisionPoint.getX() <= leftX + (2 * width / 5)) {
                return Velocity.fromAngleAndSpeed(330, DEF_SPEED);
            }
            if (collisionPoint.getX() <= leftX + (3 * width / 5)) {
                return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            if (collisionPoint.getX() <= leftX + (4 * width / 5)) {
                return Velocity.fromAngleAndSpeed(30, DEF_SPEED);
            }
            return Velocity.fromAngleAndSpeed(60, DEF_SPEED);
        }

        return currentVelocity;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Point startP = this.rectangle.getUpperLeft();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        d.fillRectangle((int) startP.getX(), (int) startP.getY(), width, height);
    }

    /**
     * Returns the center point of the top side of the paddle.
     *
     * @return center point of the top side of the paddle
     */
    public Point getCenter() {
        Line topSide = new Line(this.rectangle.getUpperLeft(), this.rectangle.getUpperRight());
        return topSide.middle();
    }

    @Override
    public void timePassed() {

        keyboard = gui.getKeyboardSensor();

        // If right or left keys are pressed, move to this side
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.gameLevel = g;
    }

    /**
     * Moving the ball if it hits the paddle side.
     */
    public void sideHit() {
        List<Ball> balls = gameLevel.getBalls();
        Point upLeft = this.rectangle.getUpperLeft();
        Point upRight = this.rectangle.getUpperRight();

        for (Ball b : balls) {
            double velX = b.getVelocity().getDx();

            if (b.getTrajectory().end().getY() > upLeft.getY()) {
                if (b.getTrajectory().end().getX() + 2 >= upLeft.getX() - dx
                        && b.getTrajectory().end().getX() - 2 <= upRight.getX() + dx) {
                    if (b.getX() - 1 <= upLeft.getX()) {
                        b.setCenter(new Point(b.getX() - (dx + velX + 1), b.getY()));
                    }
                    if (b.getX() + 1 >= upRight.getX()) {
                        b.setCenter(new Point(b.getX() + (dx + velX + 1), b.getY()));
                    }
                }
            }
        }
    }
}
