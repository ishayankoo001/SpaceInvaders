package ir.ac.kntu;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Enemy extends Rectangle {
    Main app;
    static boolean shouldMoveRight;

    Enemy(int x, int y, int w, int h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
        Image img = new Image("assets/alien_one_white.png");
        this.setFill(new ImagePattern(img));
    }

    void shoot() {
        Bullet b = new Bullet(this.getTranslateX() + 15, this.getTranslateY() + 20, 5, 20, Color.GREEN);
        b.sender = "Enemy";
        app.getRoot().getChildren().add(b);
        app.getBullets().add(b);

    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 20);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 20);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }
    public static void moveAllToLeft(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveLeft();
        }
    }
    public static void moveAllToRight(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveRight();
        }
    }
    public static void moveAllDown(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveDown();
        }
    }

    public static void herdMove(Main main, ArrayList<Enemy> enemies, double time) {
        if(time%6==0) {
            for (Enemy s : enemies) {
                if (s.getTranslateX() > 580) {
                    shouldMoveRight = false;
                    moveAllDown(enemies);
                }
                if (s.getTranslateX() < 0) {
                    shouldMoveRight = true;
                    moveAllDown(enemies);
                }
            }
            if (shouldMoveRight) {
                moveAllToRight(enemies);
            } else {
                moveAllToLeft(enemies);
            }
        }

    }

    public static void enemyBuilder(Pane root, Main main, ArrayList<Enemy> enemies) {
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 150, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 200, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 250, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 300, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 350, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
    }
}
