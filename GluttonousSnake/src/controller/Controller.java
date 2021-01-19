package controller;

import java.awt.Toolkit;

import elements.GameObject;
import elements.Order;
import elements.Snake.Direction;
import model.GameModel;
import view.View;

/**
 * Controller is for receiving user's instructions
 *,changing model, and informing views to repaint according to the new model state
 * Main is in Controller
 */
public class Controller{

    private GameModel model;
    private View view;
    boolean breakS;
    Listener listener;
    int time;
    int lengthSave;
    int clock;

    /**
     * initial controller
     * @param model game model
     * @param view game view
     */
    public Controller(GameModel model, View view) {
        this.model = model;
        this.view = view;
        this.listener = new Listener();
        this.view.addKeyListener(listener);
        this.breakS = false;
        time = 3;
        lengthSave = model.getSnake().getLength();
    }

    /**
     * get next order from player's keyboard
     * @return Order order from player
     */
    private Order getNextOrder(){
        Order ord = listener.orderList.get(0);
        listener.orderList.remove(0);
        return ord;
    }

    /**
     * return next order if it is ready to be implemented
     * @param oldX old x position
     * @param oldY old y position
     * @return next order if it is ready; null otherwise
     */
    public Order getOrder(int oldX, int oldY) {
        if(this.ifTurn(oldX,oldY) && !listener.orderList.isEmpty()) {
            return this.getNextOrder();
        }
        return null;
    }

    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }

    /**
     * decide whether is ready to implement turn order
     */
    public boolean ifTurn(int oldX, int oldY) {
        if(Math.abs(model.getSnake().getHead().getX()-oldX) > 45 || Math.abs(model.getSnake().getHead().getY()-oldY) > 45)
            return true;
        return false;
    }

    /**
     * inform the view to repaint
     */
    public void updateView() {
        view.setSnake(model.getSnake());
        view.repaint();
    }

    /**
     * inform model to change inner states according to player's order
     */
    public void executeOrder(Order order) {
        if(order.getOrd() == Order.Ord.Up) {
            model.turnUp();
        }
        else if(order.getOrd() == Order.Ord.Down) {
            model.turnDown();
        }
        else if(order.getOrd() == Order.Ord.Right) {
            model.turnRight();
        }
        else {
            model.turnLeft();
        }
    }

    /**
     * Test if the snake collides with any game objects (food,body; Check elements directory)
     * @param obj an GameObject
     * @return true if collide happens;false otherwise
     */
    public boolean isCollide(GameObject obj) {
        if(model.getSnake().getDirection() == Direction.Left) {
            return Math.abs(model.getSnake().getHead().getX() - obj.getX()-obj.getImage().getWidth(null)) == 0 &&
                    Math.abs(model.getSnake().getHead().getY() - obj.getY()) <= obj.getImage().getHeight(null);
        }
        if(model.getSnake().getDirection() == Direction.Right) {
            return Math.abs(model.getSnake().getHead().getX() + model.getSnake().getHead().getImage().getWidth(null) - obj.getX()) == 0 &&
                    Math.abs(model.getSnake().getHead().getY() - obj.getY()) <= obj.getImage().getHeight(null);
        }
        if(model.getSnake().getDirection() == Direction.Up || model.getSnake().getDirection() == Direction.Down) {
            return Math.abs(obj.getY() - model.getSnake().getHead().getY()) <= obj.getImage().getHeight(null) &&
                    Math.abs(model.getSnake().getHead().getX() - obj.getX()) <= model.getSnake().getHead().getImage().getWidth(null);
        }

        return false;
    }

    /**
     * Check whether the snake hit itself
     * @return true if collide happens;false otherwise
     */
    public boolean isHitSelf() {
        for(int i = 3; i < model.getSnake().getLength(); i++) {
            if(this.isCollide(model.getSnake().getBody().get(i)))
                return true;
        }
        return false;
    }

    /**
     * Test whether the game should end (Time up or Collide something)
     * @return true if the game should end; false otherwise
     */
    public boolean testEnd() {
        return model.getSnake().getHead().getX() >= 765 || model.getSnake().getHead().getX() <= -2
                || model.getSnake().getHead().getY() >= 685 || model.getSnake().getHead().getY() <= 15 || this.isHitSelf() || this.getView().getClock() <= 0;
    }

    /**
     * slow the refreshing speed of the view, so the snake will move slower until time >= 10
     */
    public void addTimeByLength() {
        if(this.time >= 10) {
            this.time = 10;
        }
        else {
            if(this.model.getSnake().getLength() - this.lengthSave == 4) {
                System.out.println(this.time);
                this.time ++;
                this.lengthSave = this.model.getSnake().getLength();
            }
        }
    }

    /**
     * restart the game when player choose to play again
     */
    public void restart() {
        GameModel model = new GameModel(Toolkit.getDefaultToolkit().getImage("images/ball.jpeg"));
        View view = new View(model.getSnake(),model.getFood());
        Listener test = new Listener();
        view.addKeyListener(test);
        Controller controller = new Controller(model,view);

        controller.startGame();

    }

    /**
     * Game starts here!!
     */
    public void startGame() {
        view.launch();
        int oldX = model.getSnake().getHead().getX();
        int oldY = model.getSnake().getHead().getY();
        Order currentOrder = new Order(Order.Ord.Right);
        while(true) {
            // decide whether snake is knock the wall
            if(testEnd()) {
                view.showDialog(this.model.getSnakePoint());
                restart();
                break;
            }
            Order order = this.getOrder(oldX, oldY);
            if(order == null) {
                order = currentOrder;
            }
            else {
                currentOrder = order;
                oldX = model.getSnake().getHead().getX();
                oldY = model.getSnake().getHead().getY();
            }
            executeOrder(order);
            if(isCollide(model.getFood())) {
                model.getFood().setPositionRandom();
                model.addTail();
                model.addPoint();
            }
            updateView();
            addTimeByLength();
            try {
                Thread.sleep(this.time);
            }catch(Exception e) {
                e.getMessage();
            }
        }
    }

    public static void main(String args[]) {
        GameModel model = new GameModel(Toolkit.getDefaultToolkit().getImage("images/ball.jpeg"));
        View view = new View(model.getSnake(),model.getFood());

        Controller controller = new Controller(model,view);

        //test

        controller.startGame();

    }




}
