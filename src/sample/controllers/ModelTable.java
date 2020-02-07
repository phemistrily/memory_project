package sample.controllers;

public class ModelTable {
    String TablePlayerName,TablePoints,TableTime;

    public ModelTable(String tablePlayerName, String tablePoints, String tableTime) {
        TablePlayerName = tablePlayerName;
        TablePoints = tablePoints;
        TableTime = tableTime;
    }

    public String getTablePlayerName() {
        return TablePlayerName;
    }

    public void setTablePlayerName(String tablePlayerName) {
        TablePlayerName = tablePlayerName;
    }

    public String getTablePoints() {
        return TablePoints;
    }

    public void setTablePoints(String tablePoints) {
        TablePoints = tablePoints;
    }

    public String getTableTime() {
        return TableTime;
    }

    public void setTableTime(String tableTime) {
        TableTime = tableTime;
    }
}
