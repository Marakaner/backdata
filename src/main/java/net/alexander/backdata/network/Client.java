package net.alexander.backdata.network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.channel.Channel;
import lombok.Getter;
import net.alexander.backdata.BackData;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.user.User;
import net.alexander.backdata.util.Document;

import java.net.Socket;
import java.util.UUID;

public class Client extends Thread {

    private boolean alive;

    @Getter private Channel channel;
    @Getter private User user;
    @Getter private ClientType clientType;

    public Client(Channel channel) {
        this.channel = channel;
        alive = true;
        setDaemon(true);
        start();
    }

    public void handleMessage(String message) {

        JsonParser jsonParser = new JsonParser();
        JsonObject element = (JsonObject) jsonParser.parse(message);

        BackData.getInstance().getDatabaseManager().handleRequest(this, element);

    }

    public void login(String message) {

        JsonParser jsonParser = new JsonParser();
        JsonObject element = (JsonObject) jsonParser.parse(message);

        User user = BackData.getInstance().getUserManager().login(element.get("username").getAsString(), element.get("password").getAsString());
        if(user != null) {
            this.user = user;

            BackData.getInstance().getNetworkManager().registerClient(this);

            this.channel.write(new Document().addString("id", this.channel.id().asLongText()).addBoolean("response", true).addString("value", "You successfully logged in").create());


        } else {
            this.channel.write(new Document().addString("type", EntryType.ERROR.getName()).addString("value", "Could not identify you."));
        }
    }

    public void write(String message) {
        this.channel.write(message);
    }

    public void write(JsonObject jsonObject) {
        write(jsonObject.getAsString());
    }
}
