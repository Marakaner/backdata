package net.alexander.backdata.command.commands;

import net.alexander.backdata.command.Command;

public class TableCommand extends Command {

    public TableCommand(String name) {
        super(name);
    }

    /**
     * table create name
     * table delete name
     */

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            log("");
        } else if (args.length == 1) {

        }
    }

    @Override
    public void log(String message) {
        super.log(message);
    }
}
