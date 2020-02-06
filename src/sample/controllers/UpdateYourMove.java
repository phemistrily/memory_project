package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UpdateYourMove implements Runnable {
    private final String ruch;
    private final GameController param;

    public UpdateYourMove(GameController param, String ruch) {
        this.param = param;
        this.ruch = ruch;
    }

    @Override
    public void run() {
        param.movePlayerLabel.setText(ruch);
    }
}
