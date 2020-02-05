package sample.controllers;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ClientController extends Thread {

    protected ArrayList<String> tilesMap = new ArrayList<String>();
    private final static Gson GSON = new Gson();
    public Boolean stepLock = false;
    PrintWriter writer;
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
                if (line.equals("[broadcast]:Game pushed")) {
                    while((line = reader.readLine()) != "endTilesMap") {
                        tilesMap.add(line);
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