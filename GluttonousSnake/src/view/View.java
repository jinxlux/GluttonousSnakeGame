package view;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import elements.BodyObject;
import elements.Food;
import elements.Snake;

/**
 * Game view
 */
public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private Snake snake;
    private Food food;
    private int clock;

    /**
     * initialize game view
     * @param snake snake in the game
     * @param food food in the game
     */
    public View(Snake snake, Food food){
        this.snake = snake;
        this.food = food;

        //count down
        this.clock = 181;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public Snake getSnake() {
        return snake;
    }



    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * paint the view
     * @param g Graphics for painting
     */
    public void paint(Graphics g) {
        g.drawImage(Toolkit.getDefaultToolkit().getImage("images/back.jpeg"),0,20,null);
        g.drawImage(food.getImage(), food.getX(), food.getY(), null);
        g.setColor(Color.WHITE);
        g.drawString(" "+this.snake.getLength(), 320, 50);
        g.drawString(" "+this.clock, 125, 50);
        for(BodyObject bd : snake.getBody()) {
            g.drawImage(bd.getImage(), bd.getX(), bd.getY(), null);
        }
    }

    /**
     * set frame for lunch
     */
    public void launchFrame(){
        setSize(800,720);
        setLocation(50,50);
        this.setBackground(Color.WHITE);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    /**
     * set option dialog ; launch frame
     */
    public void launch() {
        Object[] options ={ "YES", "NO" };
        int m = JOptionPane.showOptionDialog(null, "Snake\nControl the snake to eat small balls to increase its length. The more the snake eats, the slower the action\n" +
                "When the snake eats to a certain extent, it will stop growing, but still calculate the score\nStart？", "Instruction",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(m > 0) {
            System.exit(0);
        }
        ClockThread clock = new ClockThread();
        new Thread(clock).start();
        launchFrame();
    }

    /**
     * show option dialog after the end of game
     * @param point how many points player have
     */
    public void showDialog(long point) {
        Object[] options ={ "Play Again", "Quit" };
        int m = JOptionPane.showOptionDialog(null, "Game End\nFinal Points："+point, "End Game",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(m > 0) {
            System.exit(0);
        }

    }

    /**
     * for counting down
     */
    class ClockThread implements Runnable{

        @Override
        public void run() {
            while(true) {
                if(clock<0)
                    break;
                clock--;
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
