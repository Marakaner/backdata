package net.alexander.backdata.command;

import lombok.Getter;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

/**
 * The common used class to identify a Command and
 *  to implement the given parameter to use it
 */
public abstract class Command {

    private LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    @Getter
    private String name;

    public Command(String name) {
        this.name = name;
    }

    /**
     * The super method which every Command object has to extend
     * @param args The args which typed in relative to the command
     */
    public abstract void execute(String[] args);

    public void log(String message) {
        loggerManager.log(message);
    }

    public void log(Priority priority, String message) {
        loggerManager.log(priority, message);
    }

}
