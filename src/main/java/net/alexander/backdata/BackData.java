package net.alexander.backdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.command.CommandManager;
import net.alexander.backdata.command.commands.ExitCommand;
import net.alexander.backdata.command.commands.HelpCommand;
import net.alexander.backdata.console.ConsoleManager;
import net.alexander.backdata.database.DatabaseManager;
import net.alexander.backdata.event.EventManager;
import net.alexander.backdata.event.events.MessageReceivedEvent;
import net.alexander.backdata.event.events.MessageSendEvent;
import net.alexander.backdata.file.FileLoader;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.login.LoginManager;
import net.alexander.backdata.network.NetworkManager;
import net.alexander.backdata.service.ServiceManager;
import net.alexander.backdata.user.User;
import net.alexander.backdata.user.UserManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BackData {

    @Getter @Setter private boolean running;

    @Getter private static BackData instance;
    @Getter private Gson publicGson;

    /**
     * All Manager
     */
    @Getter private LoginManager loginManager;
    @Getter private CommandManager commandManager;
    @Getter private LoggerManager loggerManager;
    @Getter private UserManager userManager;
    @Getter private ConsoleManager consoleManager;
    @Getter private EventManager eventManager;
    @Getter private DatabaseManager databaseManager;
    @Getter private NetworkManager networkManager;

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

        initManager();
        initCommands();
        initEvents();
        /**
         * Not loaded in 'initManager' because it would block the main thread
         */
        this.consoleManager = new ConsoleManager();
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
        fileLoader.save();
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

        this.databaseManager = new DatabaseManager();
        ServiceManager.registerService(DatabaseManager.class, databaseManager);

        this.networkManager = new NetworkManager();
        ServiceManager.registerService(NetworkManager.class, networkManager);
    }

    /**
     * Initializing all Commands
     */
    private void initCommands() {
        commandManager.registerCommand(new HelpCommand("help"));
        commandManager.registerCommand(new ExitCommand("exit"));
    }

    private void initEvents() {
        this.eventManager.registerEvent(MessageSendEvent.class);
        this.eventManager.registerEvent(MessageReceivedEvent.class);
    }

    public static void main(String[] args) {
        new BackData();
    }

}
