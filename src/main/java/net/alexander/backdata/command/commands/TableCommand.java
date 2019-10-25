package net.alexander.backdata.command.commands;

import net.alexander.backdata.BackData;
import net.alexander.backdata.command.Command;
import net.alexander.backdata.database.DatabaseManager;

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

        DatabaseManager databaseManager = BackData.getInstance().getDatabaseManager();

        if (args.length == 0) {
            log("Table Help Page");
            log("table create [tablename]");
            log("table delete [tablename]");
        } else if (args.length == 2) {
            if(args[0].equalsIgnoreCase("create")) {
                if(databaseManager.isTableExist(args[1])) {

                } else {
                    log("The table '" + args[1] + "' already exist.");
                }
            } else if(args[0].equalsIgnoreCase("delete")) {

            } else {
                log("Wrong syntax.");
            }
        } else {
            log("Wrong syntax");
        }
    }

    @Override
    public void log(String message) {
        super.log(message);
    }
}
