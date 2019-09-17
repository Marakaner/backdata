package net.alexander.backdata.console;

import net.alexander.backdata.command.CommandManager;
import net.alexander.backdata.service.ServiceManager;

import java.util.Scanner;

public class ConsoleManager {

    private CommandManager commandManager = ServiceManager.getService(CommandManager.class);
    private boolean running;

    public ConsoleManager() {
        running = true;
        Scanner scanner = new Scanner(System.in);
        String line;

        while(running && ((line = scanner.nextLine()) != null &! line.equals(""))) {
            commandManager.checkCommand(line);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
