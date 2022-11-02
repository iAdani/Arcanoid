import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Holds information of level 1.
 */
public final class Level1 implements LevelInformation {

    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 5;
    private static final int BLOCK_WIDTH = 50; // Block width
    private static final int BLOCK_HEIGHT = 30; // Block height
    private static final Point BLOCK_LOC = new Point(380, 150); // Block location

    /**
     * Constructor.
     */
    public Level1() { }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<Velocity>();
        list.add(new Velocity(0 , 2));
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
        return "Level 1: Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Level1Background(this.blocks().remove(0));
    }

    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(BLOCK_LOC, BLOCK_WIDTH, BLOCK_HEIGHT));
        block.setColor(new Color(150, 0, 0));
        List<Block> list = new LinkedList<>();
        list.add(block);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }


}
