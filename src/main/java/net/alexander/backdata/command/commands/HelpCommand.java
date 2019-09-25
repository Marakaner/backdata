package net.alexander.backdata.command.commands;

import net.alexander.backdata.command.Command;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

public class HelpCommand extends Command {

    private LoggerManager logger = ServiceManager.getService(LoggerManager.class);

    public HelpCommand(String name) {
        super(name);
    }

    public void execute(String[] args) {
        logger.log("Help Page");
    }
}
