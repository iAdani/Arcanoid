import biuoop.GUI;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Moving from one level to another.
 */
public class GameFlow {
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height

    private Counter score;
    private GUI gui;
    private Boolean win;
    private KeyPressStoppableAnimation stoppedAnimation;
    private KeyPressStoppableAnimation pause;

    /**
     * Constructor.
     */
    public GameFlow() {
        this.score = new Counter(0);
        win = true;
        gui = new GUI("Arkanoid", WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Running the required levels.
     *
     * @param levels the levels to play
     */
    public void runLevels(List<LevelInformation> levels) {
        int counter = 1;
        int numOfLev = levels.size();
        for (LevelInformation levelInfo : levels) {
            Boolean isLastLevel = (counter == numOfLev);
            GameLevel level = new GameLevel(levelInfo, score, isLastLevel, gui);

            level.initialize();

            while (!level.getBalls().isEmpty() && level.getBlocksCounter().getValue() > 0) {
                level.run();
            }

            if (level.getBalls().isEmpty()) {
                break;
            }
            this.score = new Counter(level.getScore().getValue() + 100);
            counter++;
        }
    }
}
