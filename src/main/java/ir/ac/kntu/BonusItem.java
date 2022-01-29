package ir.ac.kntu;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class BonusItem extends Rectangle{

    public BonusItem(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    public static void appear(Pane pane){
        Random r = new Random();
        int low = 10;
        int high = 590;
        int x = r.nextInt(high-low) + low;
        int y = r.nextInt(790-510)+510;
        System.out.println(x);
        System.out.println(y);
        BonusItem heart = new BonusItem(x,y,30,30);
        heart.setFill(new ImagePattern(new Image("assets/heart.png")));
        pane.getChildren().add(heart);
        Main.bonusItems.add(heart);

    }
}
