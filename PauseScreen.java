import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * Shows the pause screen.
 */
public class PauseScreen implements Animation {

    /**
     * Constructor.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
