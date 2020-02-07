package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SummaryController {
    @FXML
    AnchorPane primaryStage;
    public void ExitAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/index.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IndexController indexController = loader.getController();
        /**
         * Set scene and pass data through the scenes
         */
        primaryStage.getChildren().setAll(pane);
    }

    public void summaryView(ActionEvent actionEvent) {
    }
}
