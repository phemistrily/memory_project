package sample.controllers;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateTiles implements Runnable {
    private final ArrayList<String> removeButtonList;
    private final GameController param;
    Map<String,Button> map = new HashMap<String,Button>();

    public UpdateTiles(GameController param, ArrayList<String> removeButtonList) {
        this.param = param;
        this.removeButtonList = removeButtonList;
        map.put("tile0", this.param.tile0);
        map.put("tile1", this.param.tile1);
        map.put("tile2", this.param.tile2);
        map.put("tile3", this.param.tile3);
        map.put("tile4", this.param.tile4);
        map.put("tile5", this.param.tile5);
        map.put("tile6", this.param.tile6);
        map.put("tile7", this.param.tile7);
        map.put("tile8", this.param.tile8);
        map.put("tile9", this.param.tile9);
        map.put("tile10", this.param.tile10);
        map.put("tile11", this.param.tile11);
        map.put("tile12", this.param.tile12);
        map.put("tile13", this.param.tile13);
        map.put("tile14", this.param.tile14);
        map.put("tile15", this.param.tile15);
        map.put("tile16", this.param.tile16);
        map.put("tile17", this.param.tile17);
        map.put("tile18", this.param.tile18);
        map.put("tile19", this.param.tile19);
    }

    @Override
    public void run() {
        System.out.println("ZACZYNAMY");
        Iterator i = removeButtonList.iterator();
        while (i.hasNext()) {
            String ins = String.valueOf(i.next());
            if(!map.get(ins).getStyleClass().contains("tileRemove"))
            {
                map.get(ins).getStyleClass().add("tileRemove");
                map.get(ins).setDisable(true);
            }
        }
    }
}
