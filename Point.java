/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a point in a 2D space
 */
public class Point {

    private static final double EPSILON = 0.00001; // epsilon for rounding, used in equals()

    private double x; //x-axis value of the point
    private double y; //y-axis value of the point

    /**
     * constructor.
     *
     * @param x x value to set
     * @param y y value to set
     */
    public Point(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance between this point and another one.
     *
     * @param other the other point
     * @return distance between the points
     */
    public double distance(Point other) {
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt((this.x - x2) * (this.x - x2) + ((this.y - y2) * (this.y - y2)));
    }

    /**
     * check if this point equals other point (have the same x,y values).
     *
     * @param other the other point
     * @return true if they equal, false otherwise
     */
    public boolean equals(Point other) {
        if (epsilonRound(this.x, other.getX()) && epsilonRound(this.y, other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * return the x value.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * return the y value.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * set a new x value.
     *
     * @param nx new x
     */
    public void setX(double nx) {
        this.x = nx;
    }

    /**
     * set a new y value.
     *
     * @param ny new y
     */
    public void setY(double ny) {
        this.y = ny;
    }

    /**
     * return true if the numbers are closer than epsilon, false otherwise.
     *
     * @param a first number
     * @param b second number
     * @return true if the numbers are closer than epsilon, false otherwise
     */
    public boolean epsilonRound(double a, double b) {
        return (Math.abs(a - b) <= EPSILON);
    }
}
