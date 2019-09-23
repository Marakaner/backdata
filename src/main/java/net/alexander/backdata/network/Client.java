package net.alexander.backdata.network;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Client extends Thread {

    private UUID uniqueId;
    private Socket socket;

    public Client(UUID uniqueId, Socket socket) {
        this.uniqueId = uniqueId;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while ((message = bufferedReader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String message) {
    }

    public Socket getSocket() {
        return socket;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
