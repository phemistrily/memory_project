package sample.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientController {
    String hostname = "localhost";
    Integer port = 7172;
    Integer playerPoints1;
    Integer playerPoints2;
    Boolean stepLock;
    String[] tilesMap;
    ArrayList<String> removeButtonList;

        public ClientController(Integer playerPoints, Boolean stepLock, String[] tilesMap, ArrayList<String> removeButtonList) {
            this.playerPoints1 = playerPoints;
            this.stepLock = stepLock;
            this.tilesMap = tilesMap;
            this.removeButtonList = removeButtonList;

            try (Socket socket = new Socket(hostname, port)) {

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);


            } catch (UnknownHostException ex) {

                System.out.println("Server not found: " + ex.getMessage());

            } catch (IOException ex) {

                System.out.println("I/O error: " + ex.getMessage());
            }
        }

        public void makeMove(Integer playerPoints, Boolean stepLock, String[] tilesMap, ArrayList<String> removeButtonList)
        {
            this.playerPoints1 = playerPoints;
            this.stepLock = stepLock;
            this.tilesMap = tilesMap;
            this.removeButtonList = removeButtonList;
            System.out.println(this.playerPoints1);
            System.out.println(this.playerPoints2);
            System.out.println(this.stepLock);
            System.out.println(this.tilesMap);
            System.out.println(this.removeButtonList);
        }
}