/**
 * @author Guy Adani
 * ID 208642884
 *
 * Just a simple counter.
 */
public class Counter {

    private int counter;

    /**
     * Constructor.
     *
     * @param number current count to set
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Default Constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return current count
     */
    public int getValue() {
        return counter;
    }
}
