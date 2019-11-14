package net.alexander.backdata.command.commands;

import net.alexander.backdata.BackData;
import net.alexander.backdata.command.Command;
import net.alexander.backdata.database.DataSet;
import net.alexander.backdata.database.DatabaseManager;
import net.alexander.backdata.database.Table;
import net.alexander.backdata.database.entries.IntegerEntry;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

public class TestCommand extends Command {

    private final LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);

    public TestCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String[] args) {
        DatabaseManager databaseManager = BackData.getInstance().getDatabaseManager();

        databaseManager.createTable("test");
        Table table = databaseManager.getTable("test");

        DataSet dataSet = new DataSet();

        dataSet.setEntry("test", new IntegerEntry(300));

        table.getDataSets().add(dataSet);

        System.out.println(table.getName());

        for(DataSet all : table.getDataSets()) {
            for(String entry : all.getEntries().keySet()) {
                System.out.println(all.getEntry(entry).getGlobalValue());
            }
        }

        loggerManager.log("The Test database was successfully created.");
    }
}
