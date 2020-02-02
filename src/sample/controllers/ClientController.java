package sample.controllers;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientController extends Thread {

    protected String playerId;
    protected Boolean stepLock = false;
    protected ArrayList<String> tilesMap = new ArrayList<String>();
    PrintWriter writer;
    public ClientController() {
    }

    @Override
    public void run() {
        try {
            final int serverPort = 7171;
            Socket socket = new Socket("localhost", serverPort);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            writer.println("Hello to the server, from " + socket.getLocalPort());
            writer.println();
            writer.flush();

            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                switch(line) {
                    case "PlayerId":
                        line = reader.readLine();
                        if(this.playerId == null) {
                            this.playerId = line;
                        }
                        System.out.println(this.playerId);
                        break;
                    case "gameStart":
                        this.stepLock = Boolean.valueOf(reader.readLine());
                        System.out.println(this.stepLock);
                        while((line = reader.readLine()) != "endTilesMap")
                        {
                            tilesMap.add(line);
                        }
                        System.out.println("tilesMap");
                        System.out.println(this.tilesMap);
                        break;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }

    public void makeWrongMove(String[] showImgArr) {
        writer.println("wrongMove");
        writer.println(showImgArr[0]);
        writer.println(showImgArr[1]);
        writer.println(showImgArr[2]);
        writer.println(showImgArr[3]);
    }
}