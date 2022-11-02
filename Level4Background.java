import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Making background of level 3.
 */
public final class Level4Background implements Sprite {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int CIRCLE_SIZE = 50; // Circle size
    private static final int CIRCLE_LOC = 90; // Distance between circles

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        d.setColor(new Color(255, 255, 77));
        d.fillCircle(CIRCLE_LOC, CIRCLE_LOC, CIRCLE_SIZE);
        d.setColor(Color.BLACK);
        d.fillCircle(CIRCLE_LOC + (CIRCLE_SIZE / 2), CIRCLE_LOC - 10, CIRCLE_SIZE);


        d.setColor(Color.RED);
        d.drawText(300, 200, "Did you notice that I like red?", 20);


    }

    @Override
    public void timePassed() {

    }
}
