package net.alexander.backdata.command;

import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.service.ServiceManager;
import org.apache.log4j.Priority;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements Service {

    private LoggerManager logger = ServiceManager.getService(LoggerManager.class);

    private Map<String, Command> commands;

    public CommandManager() {
        commands = new HashMap<>();
    }

    public void registerCommand(Command command) {
        if(!this.commands.containsKey(command.getName().toLowerCase())) {
            try {
                this.commands.put(command.getName().toLowerCase(), command);
            } catch (Exception ex) {
                logger.log(Priority.ERROR, "Could not find command");
            }
        }
    }

    public void checkCommand(String input) {
        try {
            String name;
            String[] args;

            input = input.trim();
            args = input.split(" ");
            name = args[0];

            if (this.commands.containsKey(name.toLowerCase())) {
                input = input.replace(name, "").trim();
                args = input.split(" ");
                this.commands.get(name).execute(args);
            } else {
                logger.log(Priority.ERROR, "The command '" + name + "' could not be found.");
            }
        } catch (Exception ex) {
            logger.log(Priority.ERROR, "Could not find command");
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
