import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainGUI extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("connect4.fxml"));
        stage.setTitle("Connect4");
        stage.setScene(new Scene(root, 928, 526));
        stage.setResizable(false);
        stage.show();

    }
}