package sample.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    GameController param;

    public MyTimerTask(GameController param) {
        this.param = param;
    }

    @Override
    public void run() {
        Platform.runLater(new UpdateYourMove(this.param, "Twój ruch"));
//        this.param.stepLock = this.param.client.stepLock;
        this.param.waitingForPlayer.setVisible(this.param.client.gameNotStart);
        this.param.tilesMap = this.param.client.tilesMap.toArray(new String[0]);
        this.param.countRemoveButton = this.param.client.countRemoveButton;
        this.param.removeButtonList = this.param.client.removeButtonList;
        Platform.runLater(new UpdateTiles(this.param, this.param.removeButtonList));
        Platform.runLater(new UpdatePoints(this.param, this.param.client.myPoints, this.param.client.enemyPoints));
        if(this.param.client.stepLock == true)
        {
            Platform.runLater(new UpdateYourMove(this.param, "Twój ruch"));
            //this.param.movePlayerLabel.setText("Twój ruch");
        }
        else
        {
            Platform.runLater(new UpdateYourMove(this.param, "Ruch przeciwnika"));
        }
//        for (int i = 0; i < this.param.tilesMap.length;i++)
//        {
//            System.out.println(this.param.tilesMap[i]);
//        }
        if(this.param.client.endGame)
        {
            Platform.runLater(new EndGame(this.param, this.param.client.myPoints, this.param.client.enemyPoints));

        }
    }
}