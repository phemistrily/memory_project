package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Button tile0, tile1, tile2, tile3, tile4;
    @FXML
    private Button tile5, tile6, tile7, tile8, tile9;
    @FXML
    private Button tile10, tile11, tile12, tile13, tile14;
    @FXML
    private Button tile15, tile16, tile17, tile18, tile19;

    /**
     * "Mapa" kafelków - tablica przechowująca układ zdjęć
     * Tablica ma być losowan na serwerze i przesyłana do klientów.
     */
    private String[] tilesMap = {
            "img1", "img2", "img3", "img4", "img5",
            "img7", "img10", "img6", "img2", "img8",
            "img3", "img4", "img9", "img7", "img10",
            "img9", "img5", "img1", "img8", "img6",
    };

    /**
     * Tablica przechowuje nazwy klas klikniętych zdjęć
     */
    private String[] showImgArr = new String[2];

    /**
     * Tablica przechowuje kliknięte buttony
     */
    private Button[] showButtonArr = new Button[2];

    /**
     * Zmienna zliczająca kliknięcia kafelków - liczy do 2
     */
    private Integer countClickedTiles = 0;

    /**
     * Tablica przechowuje id usuniętych kaflków-buttonów tzn. już poprawnie odkryte czyli dopasowane ze sobą
     */
    private String[] removeButtonArr = new String[20];
    private ArrayList<String> removeButtonList = new ArrayList<String>(20);

    /**
     * Zmienna zliczjąca usunięte kafelki
     */
    private Integer countRemoveButton = 0;

    /**
     * Zmienna bool ustawiająca blokadę
     */
    private Boolean stepLock = false;


    public Controller() {
        System.out.println("Controler działa");
    }


    @FXML
    void initialize() {
        //img1 = "-fx-background-image: url('https://ocdn.eu/pulscms-transforms/1/c2iktkpTURBXy8wNWIxNDFiZmE2ZGNkYmExOGNkMWNjNmMxYzQ5ZTNhMS5qcGeRkwIAzQHk')";
        //img2 = "-fx-background-image: url('https://i.pinimg.com/originals/81/ef/53/81ef53720cd4342e057b99a012fd9a1c.jpg')";
        //img2 = "-fx-background-image: url('sample.img/nosacz.jpg')"; // lokalny plik nie dziala
        //tile1.getStyleClass().add("tileBlank");
        //tile5.setStyle(img1);

    }


    @FXML
    public void tileClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if(!stepLock){
            if(removeButtonList.contains(clickedButton.getId())){
                System.out.println("kafelek został już dopasowany - nie możemy odsłonić");
            } else {
                showTiles(clickedButton);
            }
        }
    }

    /**
     * Pokazanie kafelka
     * @param clickedButton
     */
    public void showTiles(Button clickedButton) {

        if(countClickedTiles < 2){

            showButtonArr[countClickedTiles] = clickedButton;
            showImg(clickedButton, countClickedTiles);
            countClickedTiles++;
            if(countClickedTiles == 2){
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
        if(showImgArr[0] == showImgArr[1]){
            removeTiles();
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
            hideTiles();    //ukrywam kafelki
            countClickedTiles = 0;  //ustawiam countClickedTiles na 0 aby móc dalej odkrywać kafelki
            /**
             * stepLock = true;     np blokada w tym programie i to co poniżej
             * tu trzeba też wywołać jakąś metodę blokującą tego klienta
             * i wysyłającą informację do serwera aby odblokował drugiego gracza
             */
        }
    }


    public void hideTiles(){
        for(Integer i = 0; i<2; i++){
            showButtonArr[i].getStyleClass().remove(showImgArr[i]);
        }
    }

    public void removeTiles(){
        for(Integer i = 0; i<2; i++){
            removeButtonList.add(showButtonArr[i].getId());
            //removeButtonArr[countRemoveButton] = showButtonArr[i].getId();
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

        switch (clickedButtonId){
            case "tile0":
                clickedButton.getStyleClass().add(tilesMap[0]);
                showImgArr[i] = tilesMap[0];
                break;
            case "tile1":
                clickedButton.getStyleClass().add(tilesMap[1]);
                showImgArr[i] = tilesMap[1];
                break;
            case "tile2":
                clickedButton.getStyleClass().add(tilesMap[2]);
                showImgArr[i] = tilesMap[2];
                break;
            case "tile3":
                clickedButton.getStyleClass().add(tilesMap[3]);
                showImgArr[i] = tilesMap[3];
                break;
            case "tile4":
                clickedButton.getStyleClass().add(tilesMap[4]);
                showImgArr[i] = tilesMap[4];
                break;
            case "tile5":
                clickedButton.getStyleClass().add(tilesMap[5]);
                showImgArr[i] = tilesMap[5];
                break;
            case "tile6":
                clickedButton.getStyleClass().add(tilesMap[6]);
                showImgArr[i] = tilesMap[6];
                break;
            case "tile7":
                clickedButton.getStyleClass().add(tilesMap[7]);
                showImgArr[i] = tilesMap[7];
                break;
            case "tile8":
                clickedButton.getStyleClass().add(tilesMap[8]);
                showImgArr[i] = tilesMap[8];
                break;
            case "tile9":
                clickedButton.getStyleClass().add(tilesMap[9]);
                showImgArr[i] = tilesMap[9];
                break;
            case "tile10":
                clickedButton.getStyleClass().add(tilesMap[10]);
                showImgArr[i] = tilesMap[10];
                break;
            case "tile11":
                clickedButton.getStyleClass().add(tilesMap[11]);
                showImgArr[i] = tilesMap[11];
                break;
            case "tile12":
                clickedButton.getStyleClass().add(tilesMap[12]);
                showImgArr[i] = tilesMap[12];
                break;
            case "tile13":
                clickedButton.getStyleClass().add(tilesMap[13]);
                showImgArr[i] = tilesMap[13];
                break;
            case "tile14":
                clickedButton.getStyleClass().add(tilesMap[14]);
                showImgArr[i] = tilesMap[14];
                break;
            case "tile15":
                clickedButton.getStyleClass().add(tilesMap[15]);
                showImgArr[i] = tilesMap[15];
                break;
            case "tile16":
                clickedButton.getStyleClass().add(tilesMap[16]);
                showImgArr[i] = tilesMap[16];
                break;
            case "tile17":
                clickedButton.getStyleClass().add(tilesMap[17]);
                showImgArr[i] = tilesMap[17];
                break;
            case "tile18":
                clickedButton.getStyleClass().add(tilesMap[18]);
                showImgArr[i] = tilesMap[18];
                break;
            case "tile19":
                clickedButton.getStyleClass().add(tilesMap[19]);
                showImgArr[i] = tilesMap[19];
                break;
            default:
                break;
        }
    }
}
