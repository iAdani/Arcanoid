import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Shows the win screen.
 */
public class WinScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Constructor.
     *
     * @param s the score
     */
    public WinScreen(Counter s) {
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        String message = "You Win! Your score is " + score.getValue();
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, message, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}