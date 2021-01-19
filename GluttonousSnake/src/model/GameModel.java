package model;

import java.awt.Image;

import elements.BodyObject;
import elements.Snake;
import elements.Snake.Direction;
import elements.BodyObject.State;
import elements.Food;

/**
 * model of the game state
 */
public class GameModel {


    Image snakeBodyIm;
    Snake snake;
    Food food;

    /**
     * set the whole game state
     * @param snakeBodyIm image of the snake body
     */
    public GameModel(Image snakeBodyIm) {
        initizlize(snakeBodyIm);
    }

    /**
     * initizlize the whole game state
     * @param snakeBodyIm image of the snake body
     */
    public void initizlize(Image snakeBodyIm) {
        this.snakeBodyIm = snakeBodyIm;
        this.snake = new Snake(Direction.Right);
        snake.addBody(new BodyObject(200,200,this.snakeBodyIm,State.Right));
        for(int i = 1;i <= 2;i++) {
            BodyObject body2 = new BodyObject(snake.getHead().getX() - i * 35,200,snakeBodyIm,State.Right);
            snake.addBody(body2);
        }
        food = new Food(snakeBodyIm);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Image getSnakeBodyIm() {
        return snakeBodyIm;
    }

    public void setSnakeBodyIm(Image snakeBodyIm) {
        this.snakeBodyIm = snakeBodyIm;
    }


    public Snake getSnake() {
        return snake;
    }



    /**
     * set snake's body balls directions and positions according to previous balls
     */
    private void turnBody() {
        for(int i = 1; i < snake.getLength();i++) {
            BodyObject bd = snake.getBody().get(i);
            BodyObject prebd = snake.getBody().get(i-1);
            if(bd.getX()!=prebd.getX() && bd.getY()==prebd.getY() ) {
                if(bd.getX()<prebd.getX()) {
                    bd.setState(State.Right);
                    bd.setX(bd.getX()+1);
                }
                if(bd.getX()>prebd.getX()) {
                    bd.setState(State.Left);
                    bd.setX(bd.getX()-1);
                }
            }
            if(bd.getY()!=prebd.getY() && bd.getX()==prebd.getX() ) {
                if(bd.getY()<prebd.getY()) {
                    bd.setState(State.Down);
                    bd.setY(bd.getY()+1);
                }
                if(bd.getY()>prebd.getY()) {
                    bd.setState(State.Up);
                    bd.setY(bd.getY()-1);
                }
            }
            if(bd.getY()!=prebd.getY() && bd.getX()!=prebd.getX()){
                if(prebd.getState()==State.Right || prebd.getState()==State.Left) {
                    if(bd.getY()<prebd.getY())
                        bd.setY(bd.getY()+1);
                    if(bd.getY()>prebd.getY())
                        bd.setY(bd.getY()-1);
                }
                if(prebd.getState()==State.Up || prebd.getState()==State.Down) {
                    if(bd.getX()<prebd.getX())
                        bd.setX(bd.getX()+1);
                    if(bd.getX()>prebd.getX())
                        bd.setX(bd.getX()-1);
                }
            }
        }
    }

    /**
     * snake starts to go down
     */
    public void turnDown() {
        if(snake.getDirection() == Direction.Up)
            turnUp();
        else {
            turnBody();
            this.snake.setHead(snake.getHead().getX(),snake.getHead().getY()+1);
            this.snake.getHead().setState(State.Down);
            this.snake.setDirection(Direction.Down);
        }

    }

    /**
     * snake starts to go up
     */
    public void turnUp() {
        if(snake.getDirection() == Direction.Down)
            turnDown();
        else {
            turnBody();
            this.snake.setHead(snake.getHead().getX(),snake.getHead().getY()-1);
            this.snake.getHead().setState(State.Up);
            this.snake.setDirection(Direction.Up);
        }

    }

    /**
     * snake starts to go right
     */
    public void turnRight() {
        if(snake.getDirection() == Direction.Left)
            turnLeft();
        else {
            turnBody();
            this.snake.setHead(snake.getHead().getX()+1,snake.getHead().getY());
            this.snake.getHead().setState(State.Right);
            this.snake.setDirection(Direction.Right);
        }
    }

    /**
     * snake starts to go left
     */
    public void turnLeft() {
        if(snake.getDirection() == Direction.Right)
            turnRight();
        else {
            turnBody();
            this.snake.setHead(snake.getHead().getX()-1,snake.getHead().getY());
            this.snake.getHead().setState(State.Left);
            this.snake.setDirection(Direction.Left);
        }
    }

    /**
     * add a new ball to the whole body
     */
    public void addTail() {
        if(snake.getLength() > 45) {
            return;
        }
        BodyObject lastob = this.getSnake().getTail();
        if(lastob.getState() == State.Right) {
            BodyObject body = new BodyObject(snake.getTail().getX() - 35,snake.getTail().getY(),snakeBodyIm,State.Right);
            this.getSnake().addBody(body);
        }
        if(lastob.getState() == State.Left) {
            BodyObject body = new BodyObject(snake.getTail().getX() + 35,snake.getTail().getY(),snakeBodyIm,State.Left);
            this.getSnake().addBody(body);
        }
        if(lastob.getState() == State.Up) {
            BodyObject body = new BodyObject(snake.getTail().getX(),snake.getTail().getY() + 35,snakeBodyIm,State.Left);
            this.getSnake().addBody(body);
        }
        if(lastob.getState() == State.Down) {
            BodyObject body = new BodyObject(snake.getTail().getX(),snake.getTail().getY() - 35,snakeBodyIm,State.Left);
            this.getSnake().addBody(body);
        }
        this.snake.addLength();
    }

    /**
     * player get 1 more point
     */
    public void addPoint() {
        this.getSnake().addPoint();
    }

    public long getSnakePoint() {
        return this.getSnake().getPoint();
    }
}
