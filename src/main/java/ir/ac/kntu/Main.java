package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main extends Application {
    private Pane menuRoot = new Pane();
    private Pane root = new Pane();
    private Bonus bonus = new Bonus();
    private boolean isLost = false;
    private double time = 0;
    private double time2 = 0;
    Text text = new Text();
    Text score = new Text();
    private Hero player = new Hero(300, 700, 40, 40, 3);
    private Scene scene = new Scene(contentAdder());
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    private ArrayList<Bullet> bullets = new ArrayList<>();
    static ArrayList<BonusItem> bonusItems = new ArrayList<>();

    /**
     * Handles the frames and calls the update method
     * @return
     */
    private Parent contentAdder() {
        root.setPrefSize(600, 800);
        root.getChildren().add(player);
        AnimationTimer timer = new AnimationTimer() {
            int frameCount =0;

            @Override
            public void handle(long l) {
                if(!isLost) {
                    if(frameCount%2==0) {
                        update();
                    }
                    frameCount++;
                }
            }
        };
        timer.start();
        return root;
    }

    /**
     * The main method that refreshes the objects accordingly as time passes, and also
     * handles the collisions between objects.
     */
    private void update() {
        root.getChildren().remove(text);
        root.getChildren().remove(score);
        text.setText("Your Hero's HP: " + player.getHp());
        score.setText("Your Score is: "+ player.getScore());
        Image img = new Image("assets/heart.png");
        Rectangle health = new Rectangle(210, 8, 30, 30);
        health.setFill(new ImagePattern(img));
        root.getChildren().add(health);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setFill(Color.ANTIQUEWHITE);
        text.setX(10);
        text.setY(30);
        root.getChildren().add(text);
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score.setFill(Color.ANTIQUEWHITE);
        score.setX(10);
        score.setY(60);
        root.getChildren().add(score);
        time += 0.016;
        time2 += 0.25;
        Enemy.herdMove(this, enemies, time2);
        for (Bullet b : bullets) {
            switch (b.sender) {
                case "Hero":
                    b.moveUp();
                    for (Enemy s : enemies) {
                        if (b.getBoundsInParent().intersects(s.getBoundsInParent())) {
                            root.getChildren().remove(s);
                            enemies.remove(s);
                            root.getChildren().remove(b);
                            bullets.remove(b);
                            player.setScore(player.getScore()+10);
                        }
                    }
                    break;
                case "Enemy":
                    b.moveDown();
                    if (b.getBoundsInParent().intersects(player.getBoundsInParent())) {
                        root.getChildren().remove(b);
                        bullets.remove(b);
                        player.setHp(player.getHp() - 1);
                    }
                    break;

            }
        }
        for(BonusItem b: bonusItems){
            if(b.getBoundsInParent().intersects(player.getBoundsInParent())){
                bonusItems.remove(b);
                root.getChildren().remove(b);
                player.setHp(player.getHp()+1);
            }
        }
        if (player.getHp() <= 0) {
            System.out.println("Game over");
            root.getChildren().remove(player);
            isLost =true;

        }
        for (Enemy e : enemies) {
            if (time > 2) {
                if (Math.random() < 0.3) {
                    e.shoot();
                }
            }
        }

        if (time > 2) {
            time = 0;
        }

    }

    /**
     * main method of Main class, does nothing important :D
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method is overrode as the Main class inherits Application class,
     * this method controls the movement of the player by getting keyboard inputs.
     * It also first sets the scene to the menu and then builds the game according to the difficulty lever
     * user chooses
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Image menuImg = new Image("assets/menu.jpg");
        BackgroundImage menuBImg = new BackgroundImage(menuImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background menuBGround = new Background(menuBImg);
        menuRoot.setBackground(menuBGround);
        Scene menuScene = new Scene(menuRoot);
        Image img = new Image("assets/background.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);
        getPlayer().app = this;
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
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
        Rectangle easy = new Rectangle(98, 443, 404, 74);
        easy.setFill(Color.TRANSPARENT);
        Rectangle medium = new Rectangle(98, 572, 404, 74);
        medium.setFill(Color.TRANSPARENT);
        Rectangle hard = new Rectangle(98,698,404,74);
        menuRoot.getChildren().add(easy);
        menuRoot.getChildren().add(medium);
        menuRoot.getChildren().add(hard);
        easy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Enemy.enemyBuilder(root, Main.this, enemies,4);
                stage.setScene(scene);
                bonus.app=Main.this;
                bonus.start();

            }
        });
        medium.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Enemy.enemyBuilder(root, Main.this, enemies,5);
                stage.setScene(scene);
                bonus.app=Main.this;
                bonus.start();

            }
        });
        hard.setFill(Color.TRANSPARENT);
        hard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Enemy.enemyBuilder(root, Main.this, enemies,6);
                stage.setScene(scene);

            }
        });

        stage.setScene(menuScene);
        stage.setHeight(800);
        stage.setWidth(600);
        stage.show();
    }


    public Pane getRoot() {
        return root;
    }

    public Hero getPlayer() {
        return player;
    }



}
