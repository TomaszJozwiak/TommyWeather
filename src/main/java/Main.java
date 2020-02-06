import controller.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MainController controller = new MainController();

        Parent root = FXMLLoader.load(Main.class.getResource("TommyWeather.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setTitle("TommyWeather");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
