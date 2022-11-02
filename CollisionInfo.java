/**
 * @author Guy Adani
 * ID 208642884
 *
 * Contains info about a collision
 */
public class CollisionInfo {

    private Point colPoint; // the point at which the collision occurs.
    private Collidable colObject; // the collidable object involved in the collision.

    /**
     * Constructor.
     *
     * @param colPoint the collision point
     * @param colObject the collided object
     */
    public CollisionInfo(Point colPoint, Collidable colObject) {
        this.colPoint = colPoint;
        this.colObject = colObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return colPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return colObject;
    }

}
