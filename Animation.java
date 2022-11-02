import biuoop.DrawSurface;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Anything that makes an animation.
 */
public interface Animation {

    /**
     * Do one frame of the animation.
     *
     * @param d the surface to draw
     */
    void doOneFrame(DrawSurface d);

    /**
     * notify if this animation should stop.
     *
     * @return if this animation should stop.
     */
    boolean shouldStop();
}
