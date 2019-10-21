package net.alexander.backdata.command.commands;

import net.alexander.backdata.BackData;
import net.alexander.backdata.command.Command;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

public class ExitCommand extends Command {

    public ExitCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String[] args) {
        log("The Database is now shutting down...");
        BackData.getInstance().shutdown();
    }

    @Override
    public void log(String message) {
        super.log(message);
    }
}
