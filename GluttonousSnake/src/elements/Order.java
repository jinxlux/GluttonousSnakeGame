package elements;

/**
 * order from keyboard
 */
public class Order {
    public enum Ord{
        Up,Down,Left,Right
    }
    private Ord ord;

    /**
     * initialize order from keyboard
     * @param ord
     */
    public Order(Ord ord) {
        super();
        this.ord = ord;
    }
    public Ord getOrd() {
        return ord;
    }
    public void setOrd(Ord ord) {
        this.ord = ord;
    }



}
