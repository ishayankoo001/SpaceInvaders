package ir.ac.kntu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    String sender;

    /**
     * Constructor of the bullet class
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param c
     */
    Bullet(double x, double y, int w, int h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }

    /**
     * Moves the bullet up, with more speed as it is sent by the player
     */
    void moveUp() {
        setTranslateY(getTranslateY() - 30);
    }

    /**
     * Moves the bullet down, with less speed as it would have been too hard to play if the speed was 30
     */
    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }
}
