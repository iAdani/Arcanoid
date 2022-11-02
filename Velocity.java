/**
 * @author Guy Adani
 * ID 208642884
 *
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private static final int ROUND_VAL = 10; // Rounding value

    private double dx; //the speed on x axes
    private double dy; //the speed on y axes

    /**
     * constructor.
     *
     * @param dx speed on x axes
     * @param dy speed on y axes
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * return dx value.
     *
     * @return dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * return dy value.
     *
     * @return dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p point to apply
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * creates a new Velocity using angle and speed.
     *
     * @param angle the angle to move
     * @param speed the speed to move
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        //calculates the dx dy
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;

        return new Velocity(dx, dy);
    }

    /**
     * rounds a double to n numbers after the .
     *
     * @param value double to round
     * @return rounded number
     */
    public static double round(double value) {

        // multiply the number x10^rounding value then divide it to make it rounded
        long factor = (long) Math.pow(10, ROUND_VAL);
        value = value * factor;
        long output = Math.round(value);
        return (double) output / factor;
    }
}