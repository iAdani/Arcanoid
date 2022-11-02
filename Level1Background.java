import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * Making background of level 1.
 */
public final class Level1Background implements Sprite {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int CIRCLE_SIZE = 40; // Circle size
    private static final int LINE_SIZE = 100; // Line size
    private static final int SAFETY = 32; // Line safety zone

    private Block b;

    /**
     * Constructor.
     *
     * @param block the block in level 1
     */
    public Level1Background(Block block) {
        this.b = block;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Making the circles
        for (int i = 0; i < 3; i++) {
            d.setColor(new Color(255 / (i + 1), 0, 0));
            d.drawCircle((int) b.getCenter().getX(), (int) b.getCenter().getY(), CIRCLE_SIZE * (i + 1));
        }

        // Making the lines
        int cx = (int) b.getCenter().getX();
        int cy = (int) b.getCenter().getY();
        d.setColor(Color.RED);
        d.drawLine(cx, cy + SAFETY, cx, cy + LINE_SIZE + SAFETY);
        d.drawLine(cx, cy - SAFETY, cx, cy - LINE_SIZE - SAFETY);
        d.drawLine(cx + SAFETY, cy, cx + LINE_SIZE + SAFETY, cy);
        d.drawLine(cx - SAFETY, cy, cx - LINE_SIZE - SAFETY, cy);

        // Making the text
        d.setColor(Color.RED);
        d.drawText(cx + LINE_SIZE + CIRCLE_SIZE, cy, "HIT ME!", 50);


    }

    @Override
    public void timePassed() {
    }
}
