package sample.controllers;

import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    private final ClientController client;
    GameController param;

    public MyTimerTask(GameController param, ClientController client) {
        this.param = param;
        this.client = client;
    }

    @Override
    public void run() {
        this.param.waitingForPlayer.setVisible(false);
        this.param.tilesMap = this.client.tilesMap.toArray(new String[0]);
        for (int i = 0; i < this.param.tilesMap.length;i++)
        {
            System.out.println(this.param.tilesMap[i]);
        }
    }
}