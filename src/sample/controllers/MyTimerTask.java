package sample.controllers;

import java.util.TimerTask;

class MyTimerTask extends TimerTask {
    ClientController param;

    public MyTimerTask(ClientController param) {
        this.param = param;
    }

    @Override
    public void run() {
        System.out.println(this.param.startGame);
    }
}