package net.alexander.backdata.command;

import lombok.Getter;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

public abstract class Command {

    private LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    @Getter
    private String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute(String[] args);

    public void log(String message) {
        loggerManager.log(message);
    }

    public void log(Priority priority, String message) {
        loggerManager.log(priority, message);
    }

}
