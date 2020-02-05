package sample.controllers;

import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    GameController param;

    public MyTimerTask(GameController param) {
        this.param = param;
    }

    @Override
    public void run() {
        System.out.println("stepLocks");
        System.out.println(this.param.client);
        this.param.stepLock = this.param.client.stepLock;
        this.param.waitingForPlayer.setVisible(false);
        this.param.tilesMap = this.param.client.tilesMap.toArray(new String[0]);
        for (int i = 0; i < this.param.tilesMap.length;i++)
        {
            System.out.println(this.param.tilesMap[i]);
        }
    }
}