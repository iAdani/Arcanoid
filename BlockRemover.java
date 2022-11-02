/**
 * @author Guy Adani
 * ID 208642884
 *
 * A listener for removing blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel the game to remove the blocks from
     * @param remainingBlocks the counter from this game
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeCollidable(beingHit);
        gameLevel.removeSprite(beingHit);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
