import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Adani
 * ID 208642884
 *
 * represent a block in the field.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param r the rectangle
     */
    public Block(Rectangle r) {
        this.rectangle = r;
        this.color = Color.BLUE;
        hitListeners = new LinkedList();
    }

    /**
     * Set a new color.
     *
     * @param c the new color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Notify all listeners that a hit accrued
        this.notifyHit(hitter);
        // If the collision is on top or bottom then -dy
        if (rectangle.getTopSide().isOnLine(collisionPoint)
                || rectangle.getBottomSide().isOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }

        // If the collision is on right or left then -dx
        if (rectangle.getRightSide().isOnLine(collisionPoint)
                || rectangle.getLeftSide().isOnLine(collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }

        // If the collision point is not on the rectangle then keep going (default)
        return currentVelocity;
    }

    /**
     * Notify all listeners that a hit accrued.
     *
     * @param hitter the ball that did the hit
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point startP = this.rectangle.getUpperLeft();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        surface.fillRectangle((int) startP.getX(), (int) startP.getY(), width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) startP.getX(), (int) startP.getY(), width, height);

    }

    @Override
    public void timePassed() {
    }

    /**
     * Add this block to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove this block from the game.
     *
     * @param gameLevel the game to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * returns the center of the block.
     *
     * @return center of the block
     */
    public Point getCenter() {
        return new Point(rectangle.getBottomSide().middle().getX(),
                rectangle.getRightSide().middle().getY());
    }
}
