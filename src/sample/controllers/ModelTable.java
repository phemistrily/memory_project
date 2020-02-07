package sample.controllers;

import javafx.beans.property.SimpleStringProperty;

public class ModelTable {
    SimpleStringProperty TablePlayerName,TablePoints,TableTime;

    public ModelTable(String tablePlayerName, String tablePoints, String tableTime) {
        TablePlayerName = new SimpleStringProperty(tablePlayerName);
        TablePoints = new SimpleStringProperty(tablePoints);
        TableTime = new SimpleStringProperty(tableTime);
    }

    public String getTablePlayerName() {
        return TablePlayerName.get();
    }

    public SimpleStringProperty TablePlayerNameProperty() {
        return TablePlayerName;
    }

    public void setTablePlayerName(String TablePlayerName) {
        this.TablePlayerName.set(TablePlayerName);
    }

    public String getTablePoints() {
        return TablePoints.get();
    }

    public SimpleStringProperty TablePointsProperty() {
        return TablePoints;
    }

    public void setTablePoints(String TablePoints) {
        this.TablePoints.set(TablePoints);
    }

    public String getTableTime() {
        return TableTime.get();
    }

    public SimpleStringProperty TableTimeProperty() {
        return TableTime;
    }

    public void setTableTime(String TableTime) {
        this.TableTime.set(TableTime);
    }
}
