package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import elements.Order;
import elements.Order.Ord;

/**
 * listen to player's orders from his/her keyboard
 */
public class Listener implements KeyListener {
    List<Order> orderList;

    /**
     * initialize Listener
     */
    public Listener() {
        super();
        this.orderList = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==38){
            Order order = new Order(Ord.Up);
            if(this.orderList.isEmpty() || order.getOrd() != this.orderList.get(this.orderList.size()-1).getOrd())
                orderList.add(order);
        }
        if (e.getKeyCode()==40){
            Order order = new Order(Ord.Down);
            if(this.orderList.isEmpty() || order.getOrd() != this.orderList.get(this.orderList.size()-1).getOrd())
                orderList.add(order);
        }
        if (e.getKeyCode()==37){
            Order order = new Order(Ord.Left);
            if(this.orderList.isEmpty() || order.getOrd() != this.orderList.get(this.orderList.size()-1).getOrd())
                orderList.add(order);
        }
        if (e.getKeyCode()==39){
            Order order = new Order(Ord.Right);
            if(this.orderList.isEmpty() || order.getOrd() != this.orderList.get(this.orderList.size()-1).getOrd())
                orderList.add(order);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }




}
