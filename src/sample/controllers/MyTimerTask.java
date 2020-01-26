package sample.controllers;

import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    GameController param;

    public MyTimerTask(GameController param) {
        this.param = param;
    }

    @Override
    public void run() {
        this.param.waitingForPlayer.setVisible(false);
    }
}