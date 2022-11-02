/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a line between two points in a 2D space
 */
public class Line {

    private Point start; //starting point of the line
    private Point end; //ending point of the line

    /**
     * constructor.
     *
     * @param start start point
     * @param end end point
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * constructor.
     *
     * @param x1 x value of start point
     * @param y1 y value of start point
     * @param x2 x value of end point
     * @param y2 y value of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line (distance between the points).
     *
     * @return length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        //check if intersection point exists
        if (this.intersectionWith(other) != null) {
            return true;
        }

        return false;
    }

    /**
     * returns the intersection point of the lines if it exists, otherwise null.
     *
     * @param other the other line
     * @return the intersection point (null if there isn't)
     */
    public Point intersectionWith(Line other) {

        //return null if the lines are the same line
        if (this.equals(other)) {
            return null;
        }

        //represents this line as a1*x + b1*y = c1
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.end.getX() + b1 * this.end.getY();

        //represents other line as a2*x + b2*y = c2
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * other.end().getX() + b2 * other.end.getY();

        //the determinant of these values
        double det = a1 * b2 - (a2 * b1);

        //if the determinant is 0 then the lines are parallel
        if (det == 0) {

            //check if they have 1 intersection points
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
            }
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                    return this.start;
            }
            return null;
        } else {

            //finds the intersection point
            double finalX = (b2 * c1 - (b1 * c2)) / det;
            double finalY = (a1 * c2 - (a2 * c1)) / det;
            Point finalPoint = new Point(finalX, finalY);

            // checks if the point is on the lines by checking the distance between
            // the intersection and the middle of each line
            if (finalPoint.distance(this.middle()) > this.length() / 2
                    || finalPoint.distance(other.middle()) > other.length() / 2) {
                return null;
            } else {
                return finalPoint;
            }
        }
    }

    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param other other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {

        //check start-start end-end
        if (other.start().equals(this.start) && other.end().equals(this.end)) {
            return true;
        }

        //check start-end start-end
        if (other.start().equals(this.end) && other.end().equals(this.start)) {
            return true;
        }

        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle
     * @return closest intersection point to the start of the line (if it exists)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // Create a list of the intersection points
        java.util.List<Point> list = rect.intersectionPoints(this);

        // If there are no points - return null
        if (list.isEmpty()) {
            return null;
        }

        // If there is one point then it is the closest
        if (list.size() == 1) {
            return list.get(0);
        }

        // Else there are more than 1 points
        Point closest = list.get(0);
        for (Point p : list) {
            if (p.distance(this.start) < closest.distance(this.start)) {
                closest = p;
            }
        }

        return closest;

    }

    /**
     * Return true if the point is on the line, false otherwise.
     * In basic geometric, sum of two sides of a triangle are always bigger than the 3rd,
     * if they are equal then its just a line, not a triangle.
     *
     * @param p the point to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean isOnLine(Point p) {
        if (p.distance(this.start) + p.distance(this.end) == length()) {
            return true;
        }

        return false;
    }
}
