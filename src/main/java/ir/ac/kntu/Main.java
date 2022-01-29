package ir.ac.kntu;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application {
    private Pane root = new Pane();
    private Hero player = new Hero(300, 700, 40, 40);
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
        stage.setScene(new Scene(contentAdder()));
        stage.show();
    }
}
