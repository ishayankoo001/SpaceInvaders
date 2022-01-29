package ir.ac.kntu;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Hero extends Rectangle {
    Main app;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    private int hp;
    private int score = 0;

    /**
     * Constructor of the Hero class
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param hp
     */
    Hero(int x, int y, int w, int h, int hp) {
        super(w, h);
        setTranslateX(x);
        setTranslateY(y);
        this.hp = hp;
        Image img = new Image("assets/spaceship.png");
        this.setFill(new ImagePattern(img));
    }

    /**
     * Moves the hero 20 pixels to left
     */
    void moveLeft() {
        setTranslateX(getTranslateX() - 20);
    }

    /**
     * Moves the hero 20 pixels to right
     */

    void moveRight() {
        setTranslateX(getTranslateX() + 20);
    }

    /**
     * Moves the hero 20 pixels up
     */

    void moveUp() {
        setTranslateY(getTranslateY() - 20);
    }

    /**
     * Moves the hero 20 pixels down
     */
    void moveDown() {
        setTranslateY(getTranslateY() + 20);
    }

    /**
     * Creates a new bullet and puts it in the main scene
     */
    void shoot() {
        Bullet b = new Bullet(this.getTranslateX() + 20, this.getTranslateY(), 5, 20, Color.GOLD);
        b.sender = "Hero";
        app.getRoot().getChildren().add(b);
        app.getBullets().add(b);


    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
