/**
 * @author Guy Adani
 * ID 208642884
 *
 * A listener for removing balls.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param g the game
     * @param counter the remaining balls
     */
    public BallRemover(GameLevel g, Counter counter) {
        this.gameLevel = g;
        this.remainingBalls = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeBall(hitter);
        remainingBalls.decrease(1);
    }
}
