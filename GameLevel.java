import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 * <p>
 * Running the levels.
 */
public class GameLevel implements Animation {

    private static final int SCORE_SIZE = 20; // Window width
    private static final int WINDOW_WIDTH = 800; // Window width
    private static final int WINDOW_HEIGHT = 600; // Window height
    private static final int BOR_SIZE = 5; // Border width
    private static final int PADDLE_HEIGHT = 4; // Paddle height
    private static final int FPS = 60; // Default frames per second
    private static final int BALL_SPACE = 60; // The space between the balls when creating them


    private SpriteCollection sprites; // Sprites collection
    private GameEnvironment environment; // Collidable collection
    private GUI gui;
    private List<Ball> balls;
    private Counter remainingBalls;
    private Counter remainingBlocks;
    private Counter score;
    private AnimationRunner runner;
    private Boolean running;
    private Boolean isLastLevel;
    private Paddle paddle;
    private LevelInformation levelInfo;

    /**
     * Constructor.
     *
     * @param level the level info
     * @param currentScore current score
     * @param isLast is it the last level?
     * @param g the gui
     */
    public GameLevel(LevelInformation level, Counter currentScore, Boolean isLast, GUI g) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.balls = new ArrayList<>();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
        this.levelInfo = level;
        this.score = currentScore;
        this.isLastLevel = isLast;
        this.gui = g;
        this.runner = new AnimationRunner(this.gui, FPS);


    }

    /**
     * Add a new Collidable to the collection.
     *
     * @param c new Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a new Sprite to the collection.
     *
     * @param s new Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        //Make listeners
        BlockRemover blr = new BlockRemover(this, remainingBlocks);
        BallRemover bar = new BallRemover(this, remainingBalls);
        ScoreTrackingListener stl = new ScoreTrackingListener(score);

        // Add everything to the game
        addBackground();
        addScore();
        addBorders(bar);
        addBlocks(blr, stl);
        addPaddle();
        addBalls();
        addLevelName();
        this.runner.run(new CountdownAnimation(sprites));
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * adding the level name to the game.
     */
    private void addLevelName() {
        LevelName ln = new LevelName(levelInfo.levelName());
        addSprite(ln);
    }

    /**
     * Adding the blocks to the game.
     *
     * @param blr the ball remover listener
     * @param stl the score listener
     */
    public void addBlocks(BlockRemover blr, ScoreTrackingListener stl) {

        List<Block> blocks = this.levelInfo.blocks();
        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());

        for (Block b : blocks) {
            b.addHitListener(blr);
            b.addHitListener(stl);
            b.addToGame(this);
        }
    }

    /**
     * Adding the window borders to the game.
     *
     * @param bar the ball remover listener
     */
    public void addBorders(BallRemover bar) {

        List<Block> borders = new ArrayList<Block>();
        borders.add(new Block(new Rectangle(new Point(0, SCORE_SIZE), WINDOW_WIDTH, BOR_SIZE)));
        borders.add(new Block(new Rectangle(new Point(0, 0), BOR_SIZE, WINDOW_HEIGHT)));
        borders.add(new Block(new Rectangle(new Point(WINDOW_WIDTH - BOR_SIZE, 0), BOR_SIZE, WINDOW_HEIGHT)));
        Block bottom = new Block(new Rectangle(new Point(0, WINDOW_HEIGHT - BOR_SIZE), WINDOW_WIDTH, BOR_SIZE));
        //Add listener to the bottom border
        bottom.addHitListener(bar);
        borders.add(bottom);

        for (Block c : borders) {
            c.setColor(new Color(30, 30, 30));
            c.addToGame(this);
        }

    }

    /**
     * Adding the background of the game.
     */
    public void addBackground() {
        addSprite(levelInfo.getBackground());
    }

    /**
     * Adding the paddle to the game.
     */
    public void addPaddle() {
        double x = (WINDOW_WIDTH - levelInfo.paddleWidth()) / 2;
        double y = WINDOW_HEIGHT - BOR_SIZE - PADDLE_HEIGHT;
        Paddle p = new Paddle(new Rectangle(new Point(x, y), levelInfo.paddleWidth(), PADDLE_HEIGHT));
        p.setSpeed(levelInfo.paddleSpeed());
        p.setGui(this.gui);
        p.addToGame(this);
        this.paddle = p;
    }

    /**
     * Adding the balls to the game.
     */
    public void addBalls() {
        int counter = 0;
        Point center = paddle.getCenter();

        for (Velocity vel : levelInfo.initialBallVelocities()) {

            // Make the balls next to each other.
            double side = Math.pow(-1, counter + 1) * BALL_SPACE * ((counter + 1) / 2);
            Ball b = new Ball(new Point(center.getX() + side, center.getY() - 3), 3, Color.WHITE);
            counter++;

            b.setVelocity(vel);
            b.addToGame(this);
            b.setGame(this);

            balls.add(b);
        }

        remainingBalls.increase(levelInfo.numberOfBalls());
    }

    /**
     * Add score to the game.
     */
    public void addScore() {
        ScoreIndicator si = new ScoreIndicator(score);
        addSprite(si);
    }

    /**
     * Returns this game balls.
     *
     * @return this game balls
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Returns this counter of blocks.
     *
     * @return this counter of blocks
     */
    public Counter getBlocksCounter() {
        return remainingBlocks;
    }

    /**
     * Returns this game's score.
     *
     * @return score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Returns this paddle.
     *
     * @return paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Return this game's gui.
     *
     * @return this gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Returns this game environment.
     *
     * @return this game environment
     */
    public GameEnvironment getGameEnvi() {
        return this.environment;
    }

    /**
     * remove the collidable from the environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove the sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * remove the ball from the game.
     *
     * @param b the ball to remove
     */
    public void removeBall(Ball b) {
        balls.remove(b);
        sprites.removeSprite(b);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // if there are no blocks or balls left - end the game
        if (remainingBlocks.getValue() <= 0 || remainingBalls.getValue() <= 0) {
            if (remainingBlocks.getValue() <= 0) {
                if (isLastLevel) {
                    score.increase(100);
                    this.runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                            new WinScreen(score)));
                    gui.close();
                }
            } else {
                this.runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                        new EndScreen(score)));
                gui.close();
            }
            this.running = false;
        }

        if (gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
    }
}