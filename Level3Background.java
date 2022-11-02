import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Making background of level 3.
 */
public final class Level3Background implements Sprite {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int WIN_SIZE = 15; // Windows size
    private static final int WIN_FACTOR = 22; // Factor for the windows
    private static final int BUILD_WIDTH = 120; // Building width
    private static final Point BUILD_LOC = new Point(120, 450); // Building location
    private static final int ANT_WIDTH = 10; // Antenna width
    private static final int ANT_HEIGHT = 150; // Antenna height
    private static final Color[] COLORS = {new Color(150, 0, 0), new Color(185, 0, 0),
            new Color(220, 0, 0), new Color(255, 0, 0), new Color(255, 35, 35),
            new Color(255, 70, 70), new Color(255, 100, 100)}; // Array of red colors
    private static final Color[] COLORS2 = {new Color(80, 80, 80), new Color(60, 60, 60),
            new Color(50, 50, 50)}; // Array of gray colors

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Making the building
        Point center = new Point(BUILD_LOC.getX() + (BUILD_WIDTH / 2), BUILD_LOC.getY());
        d.setColor(COLORS2[2]);
        d.fillRectangle((int) center.getX() - (ANT_WIDTH / 2), (int) BUILD_LOC.getY() - ANT_HEIGHT,
                ANT_WIDTH, WINDOW_HEIGHT);

        d.setColor(COLORS2[0]);
        d.fillRectangle((int) BUILD_LOC.getX() + (BUILD_WIDTH / 4), (int) BUILD_LOC.getY() - WIN_FACTOR,
                BUILD_WIDTH / 2, WINDOW_HEIGHT);

        d.setColor(COLORS2[1]);
        d.fillRectangle((int) BUILD_LOC.getX(), (int) BUILD_LOC.getY(), BUILD_WIDTH, WINDOW_HEIGHT);

        // Making windows
        for (int i = 1; i <= 5; i++) {
            d.setColor(COLORS[i - 1]);
            for (int j = 0; j < 3; j++) {
                d.fillRectangle((int) BUILD_LOC.getX() + WIN_FACTOR + (2 * j * WIN_SIZE),
                        (int) BUILD_LOC.getY() + (2 * i * WIN_SIZE), WIN_SIZE, WIN_SIZE);
            }
        }

        d.setColor(COLORS[5]);
        d.fillCircle((int) center.getX(), (int) center.getY() - ANT_HEIGHT + 1, WIN_SIZE - 2);

        d.setColor(COLORS[2]);
        d.fillCircle((int) center.getX(), (int) center.getY() - ANT_HEIGHT + 1, 7);

        d.setColor(Color.WHITE);
        d.fillCircle((int) center.getX(), (int) center.getY() - ANT_HEIGHT + 1, 3);

    }

    @Override
    public void timePassed() { }
}
