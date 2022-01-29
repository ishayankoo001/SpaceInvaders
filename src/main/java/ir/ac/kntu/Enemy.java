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

    /**
     * Constructor of the enemy class
     * @param x
     * @param y
     * @param w
     * @param h
     * @param c
     */
    Enemy(int x, int y, int w, int h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
        Image img = new Image("assets/alien_one_white.png");
        this.setFill(new ImagePattern(img));
    }

    /**
     * Shoots a bullet downwards
     */
    void shoot() {
        Bullet b = new Bullet(this.getTranslateX() + 15, this.getTranslateY() + 20, 5, 20, Color.GREEN);
        b.sender = "Enemy";
        app.getRoot().getChildren().add(b);
        app.getBullets().add(b);

    }

    /**
     * Moves the enemy to left
     */
    void moveLeft() {
        setTranslateX(getTranslateX() - 20);
    }

    /**
     * Moves the enemy to right
     */
    void moveRight() {
        setTranslateX(getTranslateX() + 20);
    }

    /**
     * Moves the enemy down, but not 20 pixels as that would have been too much.
     */
    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }

    /**
     * Gets an array of enemies as input and moves all of them to left
     * @param enemies
     */
    public static void moveAllToLeft(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveLeft();
        }
    }

    /**
     * Gets an array of enemies and moves all of them to right
     * @param enemies
     */
    public static void moveAllToRight(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveRight();
        }
    }

    /**
     * Gets an array of enemies and moves all of them down
     * @param enemies
     */
    public static void moveAllDown(ArrayList<Enemy> enemies){
        for(Enemy s:enemies){
            s.moveDown();
        }
    }

    /**
     * This method is used to handle the herd movement of the enemies, it pushes them right untill they hit the
     * screen limits, then it pushes them lefts and each time they hit the screen limit, it pushes them down a little.
     * @param main
     * @param enemies
     * @param time
     */
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

    /**
     * This method is used to create enemies based on the difficulty level users choose
     * @param root
     * @param main
     * @param enemies
     * @param level
     */
    public static void enemyBuilder(Pane root, Main main, ArrayList<Enemy> enemies, int level) {
        for (int i = 0; i < level; i++) {
            Enemy e = new Enemy(60 + i * 100, 150, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < level; i++) {
            Enemy e = new Enemy(60 + i * 100, 200, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < level; i++) {
            Enemy e = new Enemy(60 + i * 100, 250, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < level; i++) {
            Enemy e = new Enemy(60 + i * 100, 300, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < level; i++) {
            Enemy e = new Enemy(60 + i * 100, 350, 30, 30, Color.RED);
            e.app = main;
            root.getChildren().add(e);
            enemies.add(e);
        }
    }
}
