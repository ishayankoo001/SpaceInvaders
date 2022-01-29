package ir.ac.kntu;

import javafx.scene.shape.Rectangle;

public class Hero extends Rectangle {
    private boolean isDead = false;
    Hero(int x, int y, int w, int h){
        super(w, h);
        setTranslateX(x);
        setTranslateY(y);
    }
    void moveLeft(){
        setTranslateX(getTranslateX()-20);
    }
    void moveRight(){
        setTranslateX(getTranslateX()+20);
    }
    void moveUp(){
        setTranslateY(getTranslateY()-20);
    }
    void moveDown(){
        setTranslateY(getTranslateY()+20);
    }
    void shoot(){

    }
}
