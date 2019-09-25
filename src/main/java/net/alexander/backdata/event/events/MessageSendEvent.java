package net.alexander.backdata.event.events;

import com.google.gson.JsonObject;
import net.alexander.backdata.event.Cancellable;
import net.alexander.backdata.event.Event;
import net.alexander.backdata.network.Client;

public class MessageSendEvent implements Event, Cancellable {

    private Client receiver;
    private JsonObject message;

    private boolean cancelled;

    public MessageSendEvent(Client receiver, JsonObject message) {
        this.receiver = receiver;
        this.message = message;
    }

    public JsonObject getMessage() {
        return message;
    }

    public Client getReceiver() {
        return receiver;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled() {

    }

}
