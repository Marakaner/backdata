package net.alexander.backdata.network;

import net.alexander.backdata.BackData;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkManager extends Thread implements Service {

    private LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    private ServerSocket server;

    private Map<UUID, Client> clients;

    private String ip;
    private int port = -1;

    public NetworkManager() {
        clients = new HashMap<>();
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        try {
            init();

            Socket socket;
            while ((socket = server.accept()) != null) {
                Client client = new Client(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        FileLoader fileLoader = new FileLoader(new File("BackData/config.bd"));
        fileLoader.load();
        this.ip = (String) fileLoader.get("IP");
        this.port = Integer.parseInt((String) fileLoader.get("Port"));

        if (this.ip == null || this.port == -1) {
            loggerManager.log(Priority.ERROR, "IP or Port is empty in config.bd");
            BackData.getInstance().shutdown();
        }

        this.server = new ServerSocket(port);
    }

    public void unregisterClient(UUID uniqueId) {
        loggerManager.log("Client[IP: " + clients.get(uniqueId).getSocket().getInetAddress().getHostAddress() + " / UUID: " + uniqueId.toString() + "] disconnected");
        this.clients.remove(uniqueId);
    }

    public Map<UUID, Client> getClients() {
        return clients;
    }
}
