package sample.controllers;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;
import javafx.scene.control.Button;

public class ClientController extends Thread {

    public boolean gameNotStart = true;
    protected ArrayList<String> tilesMap = new ArrayList<String>();
    private final static Gson GSON = new Gson();
    public Boolean stepLock = false;
    PrintWriter writer;
    String myPoints = "1000";
    String enemyPoints = "1000";
    public ClientController() {
    }

    @Override
    public void run() {
        try {
            final int serverPort = 7171;
            Socket socket = new Socket("localhost", serverPort);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            writer = new PrintWriter(output, true);
            writer.println("Hello to the server, from " + socket.getLocalPort());
            writer.println();
            writer.flush();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("get_move")) {
                    this.stepLock = true;
                    System.out.println("Twoj ruch");
                }
                if (line.equals("successMove") || line.equals("failMove")) {
                    this.myPoints = reader.readLine();
                    this.enemyPoints = reader.readLine();
                }
                if (line.equals("[broadcast]:Game pushed")) {
                    this.gameNotStart = false;
                    while((line = reader.readLine()) != "endTilesMap") {
                        if(line.contains("img"))
                        {
                            tilesMap.add(line);
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                System.out.println(line);
            }
        }
        catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }
    public void makeMove(String[] showImgArr) {
        this.stepLock = false;
        writer.println(GSON.toJson(
                new GameMoveDto(new GameMoveDto.TileDto(showImgArr[0], showImgArr[2]), new GameMoveDto.TileDto(showImgArr[1], showImgArr[3]))));
        writer.flush();
    }

    public void sendMessage(String[] imgArr) {
        GameMoveDto dto = new GameMoveDto(new GameMoveDto.TileDto(imgArr[0], imgArr[2]), new GameMoveDto.TileDto(imgArr[1], imgArr[3]));
        writer.println(GSON.toJson(dto));
    }

    public static class GameMoveDto {
        TileDto tile1;
        TileDto tile2;

        public GameMoveDto(TileDto tile1, TileDto tile2) {
            this.tile1 = tile1;
            this.tile2 = tile2;
        }

        static class TileDto {
            String idx;
            String image;

            public TileDto(String idx, String image) {
                this.idx = idx;
                this.image = image;
            }
        }
    }
}
