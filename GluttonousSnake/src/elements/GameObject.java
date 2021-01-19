package elements;

import java.awt.Image;

/**
 * main objects in game (food, snake body)
 */
public class GameObject {
    private int x;
    private int y;
    private Image image;

    /**
     * initialize main objects in game (food, snake body) by image
     * @param image ball image
     */
    public GameObject(Image image) {
        this.image = image;
    }

    /**
     * initialize main objects in game (food, snake body) by image and position
     * @param x object's x position in view
     * @param y  object's y position in view
     * @param image  object's image
     */
    public GameObject(int x, int y,Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }



}
