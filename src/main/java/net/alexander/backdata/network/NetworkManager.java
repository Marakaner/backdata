package net.alexander.backdata.network;

import net.alexander.backdata.BackData;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkManager extends Thread {

    private LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    private ServerSocket server;

    private Map<UUID, Client> clients;

    private String ip;
    private int port = -1;

    public NetworkManager() {
        clients = new HashMap<>();
    }

    @Override
    public void run() {
        setDaemon(true);
        try {
            init();

            Socket client;
            while ((client = server.accept()) != null) {
                UUID uniqueId = UUID.randomUUID();
                this.clients.put(uniqueId, new Client(uniqueId, client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        FileLoader fileLoader = new FileLoader(new File("BackData/config.bd"));
        fileLoader.load();
        this.ip = (String) fileLoader.get("ip");
        this.port = (int) fileLoader.get("port");

        if(this.ip == null || this.port == -1) {
            loggerManager.log(Priority.ERROR, "IP or Port is empty in config.bd");
            BackData.getInstance().shutdown();
        }

        this.server = new ServerSocket(port);
    }

}
