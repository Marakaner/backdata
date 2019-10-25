package net.alexander.backdata.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import net.alexander.backdata.BackData;
import net.alexander.backdata.event.events.MessageReceivedEvent;
import net.alexander.backdata.event.events.MessageSendEvent;
import net.alexander.backdata.user.User;
import net.alexander.backdata.util.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class Client extends Thread {

    private boolean alive;

    @Getter private UUID uniqueId;
    @Getter private Socket socket;
    @Getter private User user;
    @Getter private ClientType clientType;

    public Client(UUID uniqueId, Socket socket) {
        this.uniqueId = uniqueId;
        this.socket = socket;
        alive = true;
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            try {
                while ((message = bufferedReader.readLine()) != null) {
                    JsonParser jsonParser = new JsonParser();
                    JsonElement element = jsonParser.parse(message);
                    if (element instanceof JsonObject) {
                        JsonObject jsonObject = (JsonObject) element;

                        if (this.user != null) {
                            MessageReceivedEvent event = new MessageReceivedEvent(this, jsonObject);
                            BackData.getInstance().getEventManager().fireEvent(event);

                            if (event.isCancelled()) return;

                            if(jsonObject.get("id").getAsString().equalsIgnoreCase("pipeline")) {

                            } else {
                                BackData.getInstance().getDatabaseManager().handleRequest(this, jsonObject);
                            }
                        } else {
                            verify(jsonObject);
                        }
                    }
                }
            } catch (Exception ignored) {
            } finally {
                BackData.getInstance().getNetworkManager().unregisterClient(uniqueId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsonObject
     */
    public void write(JsonObject jsonObject) {

        MessageSendEvent event = new MessageSendEvent(this, jsonObject);
        BackData.getInstance().getEventManager().fireEvent(event);
        if (event.isCancelled()) return;

        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println(jsonObject.toString());
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verify(JsonObject jsonObject) {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        User user = BackData.getInstance().getUserManager().login(username, password);
        if (user != null) {
            this.user = user;
            this.clientType = ClientType.getByName(jsonObject.get("type").getAsString());

            write(new Document().addBoolean("response", true).addString("value", "You successfully logged in").create());
        } else {
            write(new Document().addBoolean("response", false).addString("value", "Wrong username or password").create());
            alive = false;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
