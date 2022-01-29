package ir.ac.kntu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application {
    private Pane root = new Pane();
    private double time = 0;
    Text text = new Text();
    private Hero player = new Hero(300, 700, 40, 40, 100);
    private Scene scene = new Scene(contentAdder());
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Parent contentAdder(){
        root.setPrefSize(600, 800);
        root.getChildren().add(player);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();

            }
        };
        timer.start();
        return root;
    }
    private void update(){
        root.getChildren().remove(text);
        text.setText("Your Hero's HP: "+ player.getHp());
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setX(10);
        text.setY(30);
        root.getChildren().add(text);
        time += 0.016;
            for(Bullet b:bullets){
                switch (b.sender) {
                    case "Hero":
                        b.moveUp();
                        for (Enemy s : enemies) {
                                if (b.getBoundsInParent().intersects(s.getBoundsInParent())) {
                                    root.getChildren().remove(s);
                                    enemies.remove(s);
                                    root.getChildren().remove(b);
                                    bullets.remove(b);
                                }
                        }
                        break;
                    case "Enemy":
                        b.moveDown();
                        if (b.getBoundsInParent().intersects(player.getBoundsInParent())) {
                                root.getChildren().remove(b);
                                bullets.remove(b);
                                player.setHp(player.getHp() - 10);
                        }
                        break;

                }
            }
            if(player.getHp()<0){
                System.out.println("Game over");
                root.getChildren().remove(player);
            }
            for(Enemy e:enemies){
                if(time>2){
                    if(Math.random()<0.3){
                        e.shoot();
                    }
                }
            }

        if (time>2){
            time=0;
        }

    }
    public static void main(String[] args) {
        launch(args);
        System.out.println("hi");
    }

    @Override
    public void start(Stage stage) {
        getPlayer().app=this;
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case S:
                    player.moveDown();
                    break;
                case W:
                    player.moveUp();
                    break;
                case SPACE:
                    player.shoot();
                    break;
            }
                });
        enemyBuilder();
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
    public void enemyBuilder(){
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90+i*100,150,30,30, Color.RED);
            e.app=this;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 200, 30, 30, Color.RED);
            e.app = this;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 250, 30, 30, Color.RED);
            e.app = this;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 300, 30, 30, Color.RED);
            e.app = this;
            root.getChildren().add(e);
            enemies.add(e);
        }
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy(90 + i * 100, 350, 30, 30, Color.RED);
            e.app = this;
            root.getChildren().add(e);
            enemies.add(e);
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Hero getPlayer() {
        return player;
    }

    public void setPlayer(Hero player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
