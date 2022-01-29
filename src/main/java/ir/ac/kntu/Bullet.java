package ir.ac.kntu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    String sender;
    Bullet(double x, double y, int w, int h, Color c){
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }
    void moveUp(){
        setTranslateY(getTranslateY()-30);
    }
    void moveDown(){
        setTranslateY(getTranslateY()+5);
    }
}
