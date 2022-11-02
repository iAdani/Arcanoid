/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a rectangle
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor - create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Set a new upper left location.
     *
     * @param p new location
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line to check
     * @return a (possibly empty) List of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        java.util.List<Point> interLine = new java.util.ArrayList<Point>();

        // Input the sides in array
        Line[] sides = {getTopSide(), getBottomSide(), getLeftSide(), getRightSide()};

        // Check intersection of the line with each side and add to the list
        for (Line side : sides) {
            if (side.isIntersecting(line)) {
                interLine.add(side.intersectionWith(line));
            }
        }

        return interLine;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper left point of the rectangle.
     *
     * @return upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Return the upper right point of the rectangle.
     *
     * @return upper right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Return the bottom left point of the rectangle.
     *
     * @return upper bottom left of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Return the bottom left point of the rectangle.
     *
     * @return upper bottom left of the rectangle
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Return a representation of the top side as a line.
     *
     * @return representation of the top side as a line.
     */
    public Line getTopSide() {
        return new Line(this.upperLeft, getUpperRight());
    }

    /**
     * Return a representation of the bottom side as a line.
     *
     * @return representation of the bottom side as a line.
     */
    public Line getBottomSide() {
        return new Line(getBottomLeft(), getBottomRight());

    }

    /**
     * Return a representation of the left side as a line.
     *
     * @return representation of the left side as a line.
     */
    public Line getLeftSide() {
        return new Line(this.upperLeft, getBottomLeft());

    }

    /**
     * Return a representation of the right side as a line.
     *
     * @return representation of the right side as a line.
     */
    public Line getRightSide() {
        return new Line(getUpperRight(), getBottomRight());

    }
}
