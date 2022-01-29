package ir.ac.kntu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {
    Main app;
    Enemy(int x, int y, int w, int h, Color c){
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }
    void shoot(){
        Bullet b = new Bullet(this.getTranslateX()+20, this.getTranslateY(), 5, 20, Color.GREEN);
        b.sender="Enemy";
        app.getRoot().getChildren().add(b);
        app.getBullets().add(b);

    }
}
