package sample.controllers;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientController extends Thread {

    public ClientController() {
    }

    @Override
    public void run() {
        try {
            final int serverPort = 7171;
            Socket socket = new Socket("localhost", serverPort);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello to the server, from " + socket.getLocalPort());
            writer.println();
            writer.flush();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }
}