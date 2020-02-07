package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EndGame implements Runnable {

    private final GameController param;
    private final String myPoints;
    private final String enemyPoints;
    private final String playerId;

    public EndGame(GameController param, String myPoints, String enemyPoints, String playerId) {
        this.param = param;
        this.myPoints = myPoints;
        this.enemyPoints = enemyPoints;
        this.playerId = playerId;
    }

    @Override
    public void run() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/winner.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SummaryController summaryController = loader.getController();
        summaryController.initData(this.myPoints, this.enemyPoints, this.playerId);
        /**
         * Set scene and pass data through the scenes
         */
        this.param.primaryStage.getChildren().setAll(pane);
    }
}
