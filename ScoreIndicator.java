import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * The score view.
 */
public class ScoreIndicator implements Sprite {

    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int SCORE_SIZE = 20; // Border width

    private Counter score;

    /**
     * Constructor.
     *
     * @param counter the score counter
     */
    public ScoreIndicator(Counter counter) {
        this.score = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, WINDOW_WIDTH, SCORE_SIZE);
        d.setColor(Color.RED);
        d.drawText(WINDOW_WIDTH / 2 - SCORE_SIZE, SCORE_SIZE - 4, "Score: " + score.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }
}
