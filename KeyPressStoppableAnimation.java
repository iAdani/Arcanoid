import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Decorator for the pause/win/end screens.
 * Waits for a specific key to be pressed.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation animation;
    private KeyboardSensor keySensor;
    private String key;
    private Boolean alreadyPressed;
    private Boolean running;

    /**
     * Constructor.
     *
     * @param sensor key sensor
     * @param key the key to wait for
     * @param anim the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor , String key, Animation anim) {
    this.animation = anim;
    this.keySensor = sensor;
    this.key = key;
    this.running = true;
    this.alreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keySensor.isPressed(key)) {
            if (this.alreadyPressed) {
                this.alreadyPressed = false;
            } else {
                this.running = false;
            }
        }
    }
    @Override
    public boolean shouldStop() {
        return !running;
    }
}
