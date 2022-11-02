import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Holds information of levels.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls.
     *
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * Returns the initial velocity of each ball.
     *
     * @return initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle speed.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the paddle width.
     *
     * @return paddle width
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     * .
     * @return level name
     */
    String levelName();

    // a sprite with the background of the level

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
