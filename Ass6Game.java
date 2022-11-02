import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * Let's play!
 */
public class Ass6Game {
    /**
     * Play!
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Setting the list of levels
        List<LevelInformation> levels = new LinkedList<>();

        for (String s : args) {
            if (s.equals("1")) {
                levels.add(new Level1());
            } else if (s.equals("2")) {
                levels.add(new Level2());
            } else if (s.equals("3")) {
                levels.add(new Level3());
            } else if (s.equals("4")) {
                levels.add(new Level4());
            }
        }

        if (levels.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        // Starting the game!!
        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }
}
