package sample.controllers;

import javafx.application.Platform;

import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    GameController param;

    public MyTimerTask(GameController param) {
        this.param = param;
    }

    @Override
    public void run() {
//        this.param.stepLock = this.param.client.stepLock;
        this.param.waitingForPlayer.setVisible(this.param.client.gameNotStart);
        this.param.tilesMap = this.param.client.tilesMap.toArray(new String[0]);
        if(this.param.client.stepLock == true)
        {
            Platform.runLater(new UpdateYourMove("Twój ruch"));
            //this.param.movePlayerLabel.setText("Twój ruch");
        }
        else
        {
            this.param.movePlayerLabel.setText("Przeciwnik");
        }
//        for (int i = 0; i < this.param.tilesMap.length;i++)
//        {
//            System.out.println(this.param.tilesMap[i]);
//        }
    }
}