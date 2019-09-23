package net.alexander.backdata.event;

public interface Cancellable {

    boolean isCancelled();
    void setCancelled();

}
