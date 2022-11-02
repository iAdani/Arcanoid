import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Holds information of level 4.
 */
public final class Level4 implements LevelInformation {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_SPEED = 6;
    private static final int BLOCK_WIDTH = 50; // Block width
    private static final int BLOCK_HEIGHT = 30; // Block height
    private static final int BLOCK_NUM = 75; // number of blocks
    private static final int BALL_NUM = 3; // number of balls
    private static final Color[] COLORS = {new Color(150, 0, 0), new Color(185, 0, 0),
            new Color(220, 0, 0), new Color(255, 0, 0),
            new Color(255, 35, 35), new Color(255, 70, 70)}; // Array of colors for the blocks
    private static final int FIRST_HEIGHT = 120; // first blocks row height
    private static final int BLOCK_FACTOR = 20; // Factor for blocks
    private static final int ROWS_NUMBER = 5; // number of block rows
    private static final int BOR_SIZE = 5; // Border width

    @Override
    public int numberOfBalls() {
        return BALL_NUM;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        for (int i = 0; i < BALL_NUM; i++) {
            list.add(new Velocity(4, -4));
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
        return "Level 4: Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Level4Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int rowNumber = 0;
        while (rowNumber < ROWS_NUMBER) {
            for (int i = WINDOW_WIDTH - BOR_SIZE - BLOCK_WIDTH - BLOCK_FACTOR; i > BOR_SIZE; i -= BLOCK_WIDTH) {
                Point start = new Point(i, FIRST_HEIGHT + (rowNumber * BLOCK_HEIGHT));
                Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT));
                block.setColor(COLORS[(COLORS.length - rowNumber + i) % 6]);
                list.add(block);
            }
            rowNumber++;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCK_NUM;
    }
}
