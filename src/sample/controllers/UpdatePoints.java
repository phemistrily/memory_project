package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UpdatePoints implements Runnable {
    private final String myPoints;
    private final String enemyPoints;
    private final GameController param;


    public UpdatePoints(GameController param, String myPoints, String enemyPoints) {
        this.param = param;
        this.myPoints = myPoints;
        this.enemyPoints = enemyPoints;
    }

    @Override
    public void run() {
        param.playerFirstPoints.setText(myPoints);
        param.playerSecondPoints.setText(enemyPoints);
    }
}
