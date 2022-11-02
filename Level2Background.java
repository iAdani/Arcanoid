import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Making background of level 2.
 */
public final class Level2Background implements Sprite {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int CIRCLE_SIZE = 35; // Circle size
    private static final int CIRCLE_DISTANCE = 6; // Distance between circles
    private static final int LINE_NUM = 100; // Line size
    private static final Point MOON_LOC = new Point(80, 80); // Window width
    private static final Color[] COLORS = {new Color(150, 0, 0), new Color(185, 0, 0),
            new Color(220, 0, 0), new Color(255, 0, 0), new Color(255, 35, 35),
            new Color(255, 70, 70), new Color(255, 100, 100)}; // Array of colors for the moon
    private static final Point FIRST_BLOCK_LOC = new Point(10, 170); // Block location


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Making the lines
        d.setColor(COLORS[0]);
        for (int i = 0; i <= LINE_NUM; i++) {
            d.setColor(COLORS[i % COLORS.length]);
            d.drawLine((int) MOON_LOC.getX(), (int) MOON_LOC.getY(),
                    (int) FIRST_BLOCK_LOC.getX() + (i * 7), (int) FIRST_BLOCK_LOC.getY());
        }

        // Making the moon
        for (int i = COLORS.length - 1; i >= 0; i--) {
            d.setColor(COLORS[i]);
            d.fillCircle((int) MOON_LOC.getX(), (int) MOON_LOC.getY(), CIRCLE_SIZE + (CIRCLE_DISTANCE * i));
        }

    }

    @Override
    public void timePassed() {

    }
}
