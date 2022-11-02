/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a collidabe object.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint the collision point
     * @param currentVelocity the velocity
     * @param hitter the hitting ball
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
