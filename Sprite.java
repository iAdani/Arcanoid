import biuoop.DrawSurface;

/**
 * @author Guy Adani
 * ID 208642884
 *
 *  Sprite is a game object that can be drawn to the screen.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d current surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
