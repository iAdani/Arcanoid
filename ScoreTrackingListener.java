/**
 * @author Guy Adani
 * ID 208642884
 *
 * A listener for the score.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
