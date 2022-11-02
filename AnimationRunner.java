import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * The looping code.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param g the gui
     * @param fps frames per second
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
    }

    /**
     * Running the animation.
     *
     * @param animation animation to run
     */
    public void run(Animation animation) {

        this.sleeper = new Sleeper();

        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);


            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
