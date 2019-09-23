package net.alexander.backdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.alexander.backdata.command.CommandManager;
import net.alexander.backdata.console.ConsoleManager;
import net.alexander.backdata.event.Event;
import net.alexander.backdata.event.EventManager;
import net.alexander.backdata.event.events.MessageReceivedEvent;
import net.alexander.backdata.event.events.MessageSendEvent;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.login.LoginManager;
import net.alexander.backdata.service.ServiceManager;
import net.alexander.backdata.user.User;
import net.alexander.backdata.user.UserManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private UserManager userManager;
    private ConsoleManager consoleManager;
    private EventManager eventManager;

    public BackData() {

        instance = this;

        File folder = new File("BackData");

        publicGson = new GsonBuilder().setPrettyPrinting().create();

        if(folder.exists()) {
            running = true;
            init();
        } else {
            folder.mkdir();

            this.loggerManager = new LoggerManager();
            ServiceManager.registerService(LoggerManager.class, loggerManager);

            this.userManager = new UserManager();
            ServiceManager.registerService(UserManager.class, userManager);

            User root = userManager.createUser("root", "Backyard");
            root.addPermission("admin");

            installing();
        }
    }

    private void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            shutdown();
        }));

        initCommands();
        initManager();
        initEvents();
    }

    public void shutdown() {
        running = false;

        try {
            FileWriter fileWriter = new FileWriter("BackData/user/user.json");
            BackData.getInstance().getPublicGson().toJson(userManager.getUserMap(), fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.loggerManager = new LoggerManager();
        ServiceManager.registerService(LoggerManager.class, loggerManager);

        this.loginManager = new LoginManager();
        ServiceManager.registerService(LoginManager.class, loginManager);

        this.commandManager = new CommandManager();
        ServiceManager.registerService(CommandManager.class, commandManager);

        this.userManager = new UserManager();
        ServiceManager.registerService(UserManager.class, userManager);

        this.eventManager = new EventManager();
        ServiceManager.registerService(EventManager.class, eventManager);

        this.consoleManager = new ConsoleManager();
    }

    /**
     * Initializing all Commands
     */
    private void initCommands() {

    }

    private void initEvents() {
        this.eventManager.registerEvent(MessageSendEvent.class);
        this.eventManager.registerEvent(MessageReceivedEvent.class);
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

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public EventManager getEventManager() {
        return eventManager;
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
