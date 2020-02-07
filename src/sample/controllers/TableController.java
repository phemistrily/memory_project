package sample.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.entities.PlayerScoresEntity;

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
        private TableColumn<ModelTable, String> TablePlayerName;

        @FXML
        private TableColumn<ModelTable, String> TablePoints;

        @FXML
        private TableColumn<ModelTable, String> TableTime;
        ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeFactory();
        this.getScores();


    }

    private void getScores() {
        PlayerScoresEntity psc = new PlayerScoresEntity();
        ResultSet scores = psc.getScores();
        try {
            this.putScoresToTableModel(scores);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(oblist);
    }

    private void putScoresToTableModel(ResultSet scores) throws SQLException {
        while(scores.next()) {
            oblist.add(new ModelTable(scores.getString("TablePlayerName"),scores.getString("TablePoints"),scores.getString("TableTime")));
        }
    }

    private void initializeFactory() {
        TablePlayerName.setCellValueFactory(new PropertyValueFactory<>("TablePlayerName"));
        TablePoints.setCellValueFactory(new PropertyValueFactory<>("TablePoints"));
        TableTime.setCellValueFactory(new PropertyValueFactory<>("TableTime"));
    }

    @FXML
    void ExitAction(ActionEvent event) {
        Stage stage = (Stage) buttonExitGame.getScene().getWindow();
        stage.close();
    }
}
