package net.alexander.backdata.console;

import net.alexander.backdata.BackData;
import net.alexander.backdata.command.CommandManager;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

import java.util.Scanner;

public class ConsoleManager {

    LoggerManager logger = ServiceManager.getService(LoggerManager.class);

    private CommandManager commandManager = ServiceManager.getService(CommandManager.class);

    public ConsoleManager() {

        Scanner scanner = new Scanner(System.in);
        String line;

        while(BackData.getInstance().isRunning() && ((line = scanner.nextLine()) != null &! line.equals(""))) {
            commandManager.checkCommand(line);
        }
    }
}
