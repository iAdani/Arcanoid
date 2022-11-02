import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * This will count for 2 seconds from 3 to 1 before every level starts.
 */
public class CountdownAnimation implements Animation {

    private static final int WAIT_FOR = 650;
    private static final int LOCATION = 400;
    private static final int FONT_SIZE = 40;

    private SpriteCollection sprites;
    private boolean running;
    private Sleeper sleeper;
    private int counter;

    /**
     * Constructor.
     *
     * @param gameScreen the sprites collection
     */
    public CountdownAnimation(SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.running = true;
        this.counter = 3;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(LOCATION, LOCATION, "" + counter, FONT_SIZE);
        // If counter reaches 0 then end
        if (counter == 0) {
            this.running = false;
        }
        //if counter is 3 don't wait
        if (counter < 3) {
            this.sleeper = new Sleeper();
            sleeper.sleepFor(WAIT_FOR);
        }
        counter--;
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
}