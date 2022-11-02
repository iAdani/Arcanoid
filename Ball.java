import biuoop.DrawSurface;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a ball (circle) on a surface
 */
public class Ball implements Sprite {

    private static final int DEFAULT_VELOCITY = 4;
    private static final double EPSILON = 2;

    private java.awt.Color color; //the color of the ball
    private int radius; //the size of the ball
    private Point center; //the center location of the ball
    private Velocity velocity = new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY); //velocity of the ball
    private GameEnvironment envi;
    private GameLevel gameLevel;

    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_HEIGHT = 200;


    /**
     * constructor.
     *
     * @param center ball center
     * @param r ball radius
     * @param color ball color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param x center x value
     * @param y center y value
     * @param r ball radius
     * @param color ball color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * constructor.
     *
     * @param x center x value
     * @param y center y value
     * @param r ball radius
     * @param color ball color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * return the x value of the center.
     *
     * @return the x value of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return the y value of the center.
     *
     * @return the y value of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * return the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the surface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the velocity of the ball.
     *
     * @param v velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball.
     *
     * @param dx dx of the velocity
     * @param dy dy of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * set a new ball center.
     *
     * @param p the new center
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * set a new ball size.
     *
     * @param r the new size
     */
    public void setSize(int r) {
        this.radius = r;
    }

    /**
     * return the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Moving this ball one step ahead.
     */
    public void moveOneStep() {

        // If there are no collisions, move ahead
        if (envi.getClosestCollision(this.getTrajectory()) == null) {
            this.center = this.getTrajectory().end();
        }

        // Else move close to the collision point, depending on the paddle movement
        CollisionInfo info = envi.getClosestCollision(this.getTrajectory());

        if (info != null) {
            // Finding a closer point using epsilon
            double newX = (info.collisionPoint().getX() * EPSILON + center.getX()) / (EPSILON + 1);
            double newY = (info.collisionPoint().getY() * EPSILON + center.getY()) / (EPSILON + 1);

            // Move to this point
            this.center = new Point(newX, newY);

            // Set new velocity
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
    }


    /**
     * Return a line starting at current location, and ending
     * where the velocity will take the ball if no collisions will occur.
     *
     * @return line from current location to the next step location
     */
    public Line getTrajectory() {

        // Compute the next center x and y values
        double nextX = this.center.getX() + velocity.getDx();
        double nextY = this.center.getY() + velocity.getDy();

        return new Line(center, new Point(nextX, nextY));
    }

    /**
     * Add this ball to the game.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Sets a game for the ball.
     *
     * @param g the game
     */
    public void setGame(GameLevel g) {
        this.gameLevel = g;
        this.envi = g.getGameEnvi();
    }
}
