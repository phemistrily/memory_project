package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.entities.PlayerScoresEntity;

import java.io.IOException;

public class SummaryController {
    @FXML
    AnchorPane primaryStage;
    @FXML
    private Label WinnerPointsButton;
    @FXML
    private Label WinnerPlayerName;
    @FXML
    private Label WinnerTimeButton;

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

    public void initData(String myPoints, String enemyPoints, String playerId, String time) {
        PlayerScoresEntity psc = new PlayerScoresEntity();
        psc.pushData(myPoints,playerId,time);
        this.WinnerTimeButton.setText(String.valueOf(Integer.valueOf(time)/1000));
        if(Integer.valueOf(myPoints) > Integer.valueOf(enemyPoints))
        {
            this.WinnerPointsButton.setText(myPoints);
            this.WinnerPlayerName.setText("Ty wygrałeś");
        }
        else
        {
            this.WinnerPointsButton.setText(enemyPoints);
            this.WinnerPlayerName.setText("Wygrał przeciwnik");
        }
    }
}
