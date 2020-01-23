package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.ConfigClass;

public class Main extends Application {

    public ConfigClass configClass;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String connectionName = "localhost";
        configClass = new ConfigClass(connectionName);
        Parent root = FXMLLoader.load(getClass().getResource("fxmlData/index.fxml"));
        primaryStage.setTitle("Gra memory");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
