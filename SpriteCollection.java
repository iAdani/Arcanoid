import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * A collection of Sprites.
 */
public class SpriteCollection {

    private List<Sprite> collection; // Sprites collection

    /**
     * Default constructor.
     */
    public SpriteCollection() {
        this.collection = new ArrayList();
    }

    /**
     * Add new Sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        collection.add(s);
    }

    /**
     * Remove the Sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        collection.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Creating a new list so the program won't die
        List<Sprite> c = new ArrayList(collection);
        for (Sprite s : c) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d current surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : collection) {
            s.drawOn(d);
        }
    }
}
