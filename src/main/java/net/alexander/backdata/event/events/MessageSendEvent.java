package net.alexander.backdata.event.events;

import net.alexander.backdata.event.Cancellable;
import net.alexander.backdata.event.Event;
import net.alexander.backdata.network.Client;

public class MessageSendEvent implements Event, Cancellable {

    private Client receiver;
    private String message;

    private boolean cancelled;

    public MessageSendEvent(Client receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    public String getMessage() {
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
