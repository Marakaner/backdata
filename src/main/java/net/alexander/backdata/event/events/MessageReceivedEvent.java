package net.alexander.backdata.event.events;

import com.google.gson.JsonObject;
import net.alexander.backdata.event.Cancellable;
import net.alexander.backdata.event.Event;
import net.alexander.backdata.network.Client;

public class MessageReceivedEvent implements Event, Cancellable {

    private Client sender;
    private JsonObject message;

    private boolean cancelled;

    public MessageReceivedEvent(Client sender, JsonObject message) {
        this.sender = sender;
        this.message = message;
    }

    public JsonObject getMessage() {
        return message;
    }

    public Client getSender() {
        return sender;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled() {
        cancelled = true;
    }
}
