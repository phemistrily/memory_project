package sample.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableController implements Initializable {

        @FXML
        private TableView<ModelTable> table;
        @FXML
        private AnchorPane primaryStage;

        @FXML
        private Button buttonExitGame;
        @FXML
        private Button buttonIndexPage;

        @FXML
        private TableColumn<ModelTable, String> TablePlayerName;

        @FXML
        private TableColumn<ModelTable, String> TablePoints;

        @FXML
        private TableColumn<ModelTable, String> TableTime;
        ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //zainicjowac polaczenie
        SqlConnector sqlConnector = new SqlConnector();
        ResultSet rs = sqlConnector.getData("SELECT playerName,score,time from player_results ");

//        while (true){
//            try {
//                if (!rs.next()) break;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                oblist.add(new ModelTable(rs.getString("playerName"),
//                        rs.getString("score"),
//                        rs.getString("time")));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
     TablePlayerName.setCellValueFactory(new PropertyValueFactory<>("TablePlayerName"));
     TablePoints.setCellValueFactory(new PropertyValueFactory<>("TablePoints"));
     TableTime.setCellValueFactory(new PropertyValueFactory<>("TableTime"));

     table.setItems(oblist);
    }

    @FXML
    void IndexView(ActionEvent event) {
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/index.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //IndexController indexController = loader.getController();
            /**
             * Set scene and pass data through the scenes
             */
            primaryStage.getChildren().setAll(pane);
        }
    }
    @FXML
    void ExitAction(ActionEvent event) {
        Stage stage = (Stage) buttonExitGame.getScene().getWindow();
        stage.close();
    }
}
