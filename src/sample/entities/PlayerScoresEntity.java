package sample.entities;

import sample.controllers.SqlConnector;
import sample.controllers.SqlInterface;

import java.sql.ResultSet;

public class PlayerScoresEntity implements SqlInterface {
    SqlConnector sqlConnector;

    public PlayerScoresEntity() {this.sqlConnector = getLocalhostConnection();}
    @Override
    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector;
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }

    public ResultSet getScores() {
        String query = "SELECT uuid as TablePlayerName,score as TablePoints,time as TableTime from player_results";
        ResultSet scores = sqlConnector.getData(query);
        return scores;
    }

    public void pushData(String points, String playerId, String time) {
        String createQuery = "INSERT INTO player_results (uuid, score, time) VALUES ('" + playerId + "','" + points + "','" + time + "')";
        sqlConnector.insertData(createQuery);
    }
}
