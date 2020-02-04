package sample.controllers;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ClientController extends Thread {

    protected ArrayList<String> tilesMap = new ArrayList<String>();
    private final static Gson GSON = new Gson();
    public Boolean stepLock = false;

    public ClientController() {
    }

    @Override
    public void run() {
        try {
            final int serverPort = 7171;
            Socket socket = new Socket("localhost", serverPort);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello to the server, from " + socket.getLocalPort());
            writer.println();
            writer.flush();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("get_move")) {
                    this.stepLock = true;
                    writer.println(GSON.toJson(
                            new GameMoveDto(new GameMoveDto.TileDto(1, "img"), new GameMoveDto.TileDto(2, "img"))));
                    writer.flush();
                }
                System.out.println(line);
            }
        }
        catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }
    public

    static class GameMoveDto {
        TileDto tile1;
        TileDto tile2;

        public GameMoveDto(TileDto tile1, TileDto tile2) {
            this.tile1 = tile1;
            this.tile2 = tile2;
        }

        static class TileDto {
            int idx;
            String image;

            public TileDto(int idx, String image) {
                this.idx = idx;
                this.image = image;
            }
        }
    }
}