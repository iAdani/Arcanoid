/**
 * @author Guy Adani
 * ID 208642884
 *
 * notifying objects that there was a hit.
 */
public interface HitNotifier {

    /**
     * Add a new listener to hit events.
     *
     * @param hl the listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a listener from the list of listeners to hit events.
     *
     * @param hl the listener to remove
     */
    void removeHitListener(HitListener hl);
}
