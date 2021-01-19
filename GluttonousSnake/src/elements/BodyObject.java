package elements;

import java.awt.Image;

/**
 * snake body ball
 */
public class BodyObject extends GameObject{

    public enum State{
        Right,Left,Up,Down
    }
    private State state;

    /**
     * Ball of a snake whole body
     * @param x ball's x position in view
     * @param y  ball's y position in view
     * @param image  ball image
     * @param state ball moves direction
     */
    public BodyObject(int x, int y, Image image, State state) {
        super(x,y,image);
        this.state = state;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }



}
