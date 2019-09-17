package net.alexander.backdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.alexander.backdata.command.CommandManager;
import net.alexander.backdata.console.ConsoleManager;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.login.LoginManager;
import net.alexander.backdata.service.ServiceManager;
import net.alexander.backdata.test.Test;

import java.io.File;
import java.util.Scanner;

public class BackData {

    private boolean running;

    private static BackData instance;
    private Gson publicGson;

    /**
     * All Manager
     */
    private LoginManager loginManager;
    private CommandManager commandManager;
    private LoggerManager loggerManager;
    private ConsoleManager consoleManager;

    public BackData() {
        instance = this;
        running = true;

        File folder = new File("BackData");

        if(folder.exists()) {
            this.loggerManager = new LoggerManager();
            ServiceManager.registerService(LoggerManager.class, loggerManager);

            this.loggerManager.log("Initializing 'backdata' Database Software");
            init();
        } else {
            folder.mkdir();
            this.loggerManager = new LoggerManager();
            ServiceManager.registerService(LoggerManager.class, loggerManager);

            installing();
        }
    }

    private void init() {
        publicGson = new GsonBuilder().setPrettyPrinting().create();

        initCommands();
        initManager();
    }

    private void installing() {
        String ip;
        int port;
        Scanner scanner = new Scanner(System.in);
        FileLoader fileLoader = new FileLoader(new File("BackData/config.bd"));


        this.loggerManager.log("Installing Database Software. This may take a minute...");
        new File("BackData").mkdir();

        this.loggerManager.log("Please type the IP4 Address of the current Server");
        this.loggerManager.log("You can change the following Values everytime in the config.bd");
        ip = scanner.nextLine();
        this.loggerManager.log("The IP4 Address of the server has set to " + ip);

        this.loggerManager.log("Please type the Port on which the netty server should be started");
        port = Integer.parseInt(scanner.nextLine());
        this.loggerManager.log("The port was set to " + port);
        this.loggerManager.log("The remaining things will now be installed...");

        fileLoader.set("IP", ip);
        fileLoader.set("Port", port);
        this.loggerManager.log("The login:");
        this.loggerManager.log("Username: root");
        this.loggerManager.log("Password: Backyard");
        this.loggerManager.log("Please change the password directly after first login");

        System.exit(0);

    }

    /**
     * Initializing all Manager
     */
    private void initManager() {
        this.loginManager = new LoginManager();
        ServiceManager.registerService(LoginManager.class, loginManager);

        this.commandManager = new CommandManager();
        ServiceManager.registerService(CommandManager.class, commandManager);

        new Test().start();

        this.consoleManager = new ConsoleManager();
    }

    /**
     * Initializing all Commands
     */
    private void initCommands() {

    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public LoggerManager getLoggerManager() {
        return loggerManager;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public static BackData getInstance() {
        return instance;
    }

    public Gson getPublicGson() {
        return publicGson;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) {
        new BackData();
    }

}
