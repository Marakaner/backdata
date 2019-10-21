package net.alexander.backdata.command.commands;

import net.alexander.backdata.command.Command;

public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    public void execute(String[] args) {
        if(args.length == 0) {
            log("List of all existing Commands:");
            log("user - Command to manage the user");
            log("permission - Command to manage the permission");
            log("table - Command to manage the tables");
            log("exit - Command to stop the BackData Database");
        } else {
            log("Wrong syntax!");
        }
    }

    @Override
    public void log(String message) {
        super.log(message);
    }
}
