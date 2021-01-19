package elements;

import java.util.ArrayList;
import java.util.List;

/**
 * record of the snake's whole body (balls, length, move direction)
 */
public class Snake {
    private List<BodyObject> body;
    private int length;
    public enum Direction{
        Right,Left,Up,Down
    }

    /**
     * set the initial snake state (move direction and length)
     * @param direction up,down,right,left
     */
    public Snake(Direction direction) {
        body =  new ArrayList<>();
        length = 3;
        this.direction = direction;
        point = 0;
    }

    private Direction direction;
    private long point;

    public long getPoint() {
        return point;
    }
    public void setPoint(long point) {
        this.point = point;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<BodyObject> getBody() {
        return body;
    }

    public void setBody(List<BodyObject> body) {
        this.body = body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addBody(BodyObject bodyOb) {
        this.body.add(bodyOb);
    }

    public BodyObject getHead() {
        return this.body.get(0);
    }

    public BodyObject getTail() {
        return this.body.get(this.getLength()-1);
    }

    /**
     * set the head position of the snake
     * @param x x position
     * @param y y position
     */
    public void setHead(int x, int y) {
        this.getHead().setX(x);
        this.getHead().setY(y);
    }

    /**
     * add snake length by 1 after eating food
     */
    public void addLength() {
        this.length += 1;
    }

    /**
     * add points for counting final mark
     */
    public void addPoint() {
        this.setPoint(this.getPoint()+1);
    }

    /**
     * clear body list
     */
    public void cleanBody() {
        this.getBody().clear();
    }



}
