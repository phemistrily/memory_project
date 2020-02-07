package sample.controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class IndexController {

    @FXML
    public AnchorPane primaryStage;

    @FXML
    private Button buttonPlaySolo;

    @FXML
    private Button buttonPlayOnline;

    @FXML
    private Button buttonExitGame;

    @FXML
    void ExitAction(ActionEvent event) {
        Stage stage = (Stage) buttonExitGame.getScene().getWindow();
        stage.close();
    }

    @FXML
    void PlayOnlineAction(ActionEvent event) {

    }

    @FXML

        public void PlaySoloAction(ActionEvent actionEvent) throws IOException
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/game.fxml"));
            AnchorPane pane = loader.load();
            GameController gameController = loader.getController();
            /**
             * Set scene and pass data through the scenes
             */
            primaryStage.getChildren().setAll(pane);

        }


    public void SummaryView(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/summary.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableController tableController = loader.getController();
        /**
         * Set scene and pass data through the scenes
         */
        primaryStage.getChildren().setAll(pane);
    }
}



