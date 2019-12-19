package net.alexander.backdata.network;

import net.alexander.backdata.BackData;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkManager implements Service {

    private LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    private Map<String, Client> clients;

    private Server server;

    private String ip;
    private int port = -1;

    public NetworkManager() {
        clients = new HashMap<>();
    }

    protected void registerClient(Client client) {
        this.clients.put(client.getChannel().id().asLongText(), client);
    }

    public boolean isRegistered(String channelId) {
        return this.clients.get(channelId) != null;
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

        try {
            server = new Server(this.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregisterClient(UUID uniqueId) {
        loggerManager.log("Client[IP: " + clients.get(uniqueId).getSocket().getInetAddress().getHostAddress() + " / UUID: " + uniqueId.toString() + "] disconnected");
        this.clients.remove(uniqueId);
    }

    public Client getClient(String channelId) {
        return this.clients.get(channelId);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
