import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * Holds information of level 2.
 */
public final class Level2 implements LevelInformation {

    private static final int PADDLE_WIDTH = 650;
    private static final int PADDLE_SPEED = 3;
    private static final int BLOCK_WIDTH = 50; // Block width
    private static final int BLOCK_HEIGHT = 30; // Block height
    private static final Point FIRST_BLOCK_LOC = new Point(10, 170); // Block location
    private static final int BLOCK_NUM = 15; // number of blocks
    private static final int BALL_NUM = 10; // number of balls
    private static final Color[] COLORS = {new Color(150, 0, 0), new Color(185, 0, 0),
            new Color(220, 0, 0), new Color(255, 0, 0), new Color(255, 35, 35),
            new Color(255, 70, 70), new Color(255, 100, 100)}; // Array of colors for the blocks


    @Override
    public int numberOfBalls() {
        return BALL_NUM;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        for (int i = 1; i <= BALL_NUM; i++) {
            list.add(new Velocity(2, 5));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Level 2: Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        for (int i = 0; i < BLOCK_NUM; i++) {
            Point location = new Point(FIRST_BLOCK_LOC.getX() + (i * BLOCK_WIDTH), FIRST_BLOCK_LOC.getY());
            Block block = new Block(new Rectangle(location, BLOCK_WIDTH, BLOCK_HEIGHT));
            block.setColor(COLORS[(i * 2) % COLORS.length]);
            list.add(block);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCK_NUM;
    }
}
