package net.alexander.backdata.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.alexander.backdata.BackData;
import net.alexander.backdata.event.Event;
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

    private UUID uniqueId;
    private Socket socket;
    private User user;

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
                JsonParser jsonParser = new JsonParser();
                Object object = jsonParser.parse(message);
                if(object instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) object;

                    if(this.user != null) {
                        MessageReceivedEvent event = new MessageReceivedEvent(this, jsonObject);
                        BackData.getInstance().getEventManager().fireEvent(event);

                        if(event.isCancelled()) return;

                        BackData.getInstance().getDatabaseManager().handleRequest(this, jsonObject);

                    } else {
                        verify(jsonObject);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(JsonObject jsonObject) {

        MessageSendEvent event = new MessageSendEvent(this, jsonObject);
        BackData.getInstance().getEventManager().fireEvent(event);
        if(event.isCancelled()) return;

        try {
            new PrintStream(socket.getOutputStream()).println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verify(JsonObject jsonObject) {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        User user = BackData.getInstance().getUserManager().login(username, password);
        if(user != null) {
            this.user = user;
            write(new Document().addBoolean("response", true).addString("value", "You successfully logged in").create());
        } else {
            write(new Document().addBoolean("response", false).addString("value", "Wrong username or password").create());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
