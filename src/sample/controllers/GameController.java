package sample.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class GameController {
    @FXML
    public Label waitingForPlayer;
    @FXML
    public Label playerFirstPoints;
    @FXML
    public Label playerSecondPoints;
    @FXML
    public AnchorPane primary;
    @FXML
    public Button tile0, tile1, tile2, tile3, tile4;
    @FXML
    public Button tile5, tile6, tile7, tile8, tile9;
    @FXML
    public Button tile10, tile11, tile12, tile13, tile14;
    @FXML
    public Button tile15, tile16, tile17, tile18, tile19;
    @FXML
    public Label movePlayerLabel;

    protected String[] tilesMap = {
            "img1", "img2", "img3", "img4", "img5",
            "img7", "img10", "img6", "img2", "img8",
            "img3", "img4", "img9", "img7", "img10",
            "img9", "img5", "img1", "img8", "img6",
    };

    private String[] showImgArr = new String[4];
    public Button[] showButtonArr = new Button[2];
    private Integer countClickedTiles = 0;
    private String[] removeButtonArr = new String[200];
    public ArrayList<String> removeButtonList = new ArrayList<String>(200);//usuniete
    public Integer countRemoveButton = 0; // koniec gry jak 20
    public Boolean stepLock = true;
    private String playerPoints = "odświeżam";
    protected ClientController client;

    public GameController() {
        System.out.println("Controler działa");
    }

    private String[] shuffleArray(String[] tilesMap) {
        Random rnd = ThreadLocalRandom.current();
        Integer legnthArray = tilesMap.length - 1;
        for (int i = legnthArray; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = tilesMap[index];
            tilesMap[index] = tilesMap[i];
            tilesMap[i] = a;
        }
        return tilesMap;
    }

    @FXML
    void initialize() {
        this.client = new ClientController();
        
        this.client.start();
//        Timer timer = new java.util.Timer();
//
//        timer.schedule(new TimerTask() {
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        movePlayerLabel.setText("dupa");
//                    }
//                });
//            }
//        }, 2000, 2000);
        Timer timer = new java.util.Timer();
        timer.schedule(new MyTimerTask(this), 2000, 2000);
    }

    @FXML
    public void tileClick(ActionEvent event) {
//        for (int i = 0; i < this.tilesMap.length;i++)
//        {
//            System.out.println(this.tilesMap[i]);
//        }
        Button clickedButton = (Button) event.getSource();
        if(client.stepLock){
            if(removeButtonList.contains(clickedButton.getId())){
                System.out.println(clickedButton.getId());
                System.out.println("kafelek został już dopasowany - nie możemy odsłonić");
            } else {
                showTiles(clickedButton);
                System.out.println(clickedButton.getId());
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Zaczekaj!");
            alert.setHeaderText("Popatrz co zrobiłeś!?");
            alert.setContentText("Zaczekaj cierpliwie na swój ruch");

            alert.showAndWait();
        }
    }

    /**
     * Pokazanie kafelka
     * @param clickedButton
     */
    public void showTiles(Button clickedButton) {

        if(countClickedTiles < 2){

            showButtonArr[countClickedTiles] = clickedButton;
            showImg(clickedButton, countClickedTiles); //TODO
            countClickedTiles++;
            if(countClickedTiles == 2){
                client.sendMessage(showImgArr);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        checkShowTiles();
                    }
                });
                thread.start();
            }
        }
    }

    /**
     * Sprawdza czy odkryte kafelki są takie same
     */
    public void checkShowTiles(){
        System.out.println("CHECKSHOWTILES");
        for(int i = 0; i < 4;i++)
        {
            System.out.println(showImgArr[i]);
        }
        if((this.showImgArr[0].equals(this.showImgArr[1])) && (!this.showImgArr[2].equals(this.showImgArr[3])))
        {
            System.out.println("wchodzi tutaj");
            this.removeTiles();
            countClickedTiles = 0;
            if(countRemoveButton == 20){
                System.out.println("koniec gry");
                /**
                 * stepLock = false;    możliwa dalsza gra - defakto gdzieś ze serwera trzeba
                 * tą informację pobierać i ustawiać ale nie tu bo tu to i tak chyba nie ma sensu bo skoro
                 * odkrywa poprawnie to ta blokada raczej się nie zmieni
                 * //tu trzeba zrobić informację do serwera czy coś
                 */
            }
        } else {
            System.out.println("nie udało się");
            hideTiles();    //ukrywam kafelki\
            countClickedTiles = 0;  //ustawiam countClickedTiles na 0 aby móc dalej odkrywać kafelki
            /**
             * stepLock = true;     np blokada w tym programie i to co poniżej
             * tu trzeba też wywołać jakąś metodę blokującą tego klienta
             * i wysyłającą informację do serwera aby odblokował drugiego gracza
             */
        }
        this.client.makeMove(showImgArr);
    }

    public void hideTiles(){
        for(Integer i = 0; i<2; i++){
            showButtonArr[i].getStyleClass().remove(showImgArr[i]);
        }
    }

    public void removeTiles(){
        System.out.println("kasowanie kafelkow");
        for(Integer i = 0; i<2; i++){
            removeButtonList.add(showButtonArr[i].getId());
            removeButtonArr[countRemoveButton] = showButtonArr[i].getId();
            countRemoveButton++;
            showButtonArr[i].getStyleClass().remove(showImgArr[i]);
            showButtonArr[i].getStyleClass().add("tileRemove");
        }
    }

    /**
     * Wyświetlenie zdjęcja na klikniętym kafelku
     * @param clickedButton
     * @param i
     */
    public void showImg(Button clickedButton, Integer i){
        String clickedButtonId = clickedButton.getId();
        String tileMapData = tilesMap[Integer.parseInt(clickedButtonId.substring(4))];
        clickedButton.getStyleClass().add(tileMapData);
        showImgArr[i] = tileMapData;
        showImgArr[i+2] = clickedButtonId;
    }
}