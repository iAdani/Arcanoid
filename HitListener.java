/**
 * @author Guy Adani
 * ID 208642884
 *
 * implementing objects will be notified when there was a hit
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that got hit
     * @param hitter the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
