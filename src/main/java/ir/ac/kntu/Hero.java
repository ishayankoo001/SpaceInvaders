package ir.ac.kntu;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Hero extends Rectangle {
    Main app;
    private boolean isDead = false;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    private int hp;

    Hero(int x, int y, int w, int h, int hp) {
        super(w, h);
        setTranslateX(x);
        setTranslateY(y);
        this.hp = hp;
        Image img = new Image("assets/spaceship.png");
        this.setFill(new ImagePattern(img));
    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 20);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 20);
    }

    void moveUp() {
        setTranslateY(getTranslateY() - 20);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 20);
    }

    void shoot() {
        Bullet b = new Bullet(this.getTranslateX() + 20, this.getTranslateY(), 5, 20, Color.GOLD);
        b.sender = "Hero";
        app.getRoot().getChildren().add(b);
        app.getBullets().add(b);


    }
}
