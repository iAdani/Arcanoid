import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * Shows the level name.
 */
public class LevelName implements Sprite {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int FACTOR = 60; // Border width

    private String name;

    /**
     * Constructor.
     *
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.name = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(WINDOW_WIDTH / 2 + (3 * FACTOR), FACTOR / 3 - 4, name, 15);
    }

    @Override
    public void timePassed() {
    }
}
