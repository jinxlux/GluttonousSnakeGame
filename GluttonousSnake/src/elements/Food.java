package elements;

import java.awt.Image;
import java.util.Random;

/**
 * food for snake
 */
public class Food extends GameObject{

    /**
     * initialize food for snake
     * @param image food picture
     */
    public Food(Image image) {
        super(image);
        this.setPositionRandom();
    }

    /**
     * random set food's position
     */
    public void setPositionRandom() {
        Random rand = new Random();
        super.setX(rand.nextInt(649)+28);
        super.setY(rand.nextInt(628)+40);
    }

}
