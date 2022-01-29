package ir.ac.kntu;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application {
    private Pane root = new Pane();
    private Hero player = new Hero(300, 700, 40, 40);
    private Scene scene = new Scene(contentAdder());
    private Parent contentAdder(){
        root.setPrefSize(600, 800);
        root.getChildren().add(player);
        return root;
    }
    public static void main(String[] args) {
        launch(args);
        System.out.println("hi");
    }

    @Override
    public void start(Stage stage) {
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
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
