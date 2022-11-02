import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Shows the end screen.
 */
public class EndScreen implements Animation {

    private Counter score;

    /**
     * Constructor.
     *
     * @param s the score
     */
    public EndScreen(Counter s) {
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        String message = "Game over. Your score is " + score.getValue();
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, message, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
