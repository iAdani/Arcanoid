import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * a collection of objects a Ball can collide with.
 */
public class GameEnvironment {

    private List<Collidable> collection; //the objects the ball can collide with

    /**
     * Default construction.
     */
    public GameEnvironment() {
        this.collection = new ArrayList();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        collection.add(c);
    }

    /**
     * Remove the collidable from the environment.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        collection.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the ball
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        Collidable closestC = null; // Will be the collided object
        Point closestP = null; // Will be the collided point

        // Check if each Collidabe collide with the ball
        for (Collidable c : collection) {

            Rectangle r = c.getCollisionRectangle(); // The rectangle of current Collidable

            // Get intersection points if they exist
            List<Point> interPoints = r.intersectionPoints(trajectory);

            // If this Collidable collide with the trajectory, check if it is closest
            if (!interPoints.isEmpty()) {
                Point p = trajectory.closestIntersectionToStartOfLine(r);
                if (closestP == null || p.distance(trajectory.start()) < closestP.distance(trajectory.start())) {
                    closestP = p;
                    closestC = c;
                }
            }
        }

            // If there were no intersections return null
            if (closestC == null) {
                return null;
            }
            // Else return the collision info
            return new CollisionInfo(closestP, closestC);
    }

    /**
     * draw the ball on the surface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        for (Collidable c : collection) {
            surface.setColor(Color.MAGENTA);
            int startX = (int) c.getCollisionRectangle().getUpperLeft().getX();
            int startY = (int) c.getCollisionRectangle().getUpperLeft().getY();
            int width = (int) c.getCollisionRectangle().getWidth();
            int height = (int) c.getCollisionRectangle().getHeight();

            surface.fillRectangle(startX, startY, width, height);
        }
    }
}
