package sample.controllers;

public class UpdateYourMove implements Runnable {
    private final String ruch;

    public UpdateYourMove(String ruch) {
        this.ruch = ruch;
    }

    public void run() {
        movePlayerLabel.setText(ruch);
    }
}
