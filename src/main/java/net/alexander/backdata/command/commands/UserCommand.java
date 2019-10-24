package net.alexander.backdata.command.commands;

import net.alexander.backdata.BackData;
import net.alexander.backdata.command.Command;

public class UserCommand extends Command {

    public UserCommand(String name) {
        super(name);
    }

    /**
     * Command overview
     * <p>
     * user - Help page
     * user list - List all user
     * user create [name] [password]
     * user delete [name]
     * user
     */

    @Override
    public void execute(String[] args) {

        if (args.length == 0) {
            log("Exit Help Page");
            log("user help - The help page");
            log("user create [name] [password]");
            log("user delete [name]");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                log("Exit Help Page");
                log("user help - The help page");
                log("user create [name]");
                log("user delete [name]");
            } else {
                return;
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete")) {
                String name = args[1];
                if (BackData.getInstance().getUserManager().isUserExisting(name)) {
                    if (BackData.getInstance().getUserManager().getUserMap().size() > 1) {
                        try {
                            BackData.getInstance().getUserManager().deleteUser(name);
                            log("The user '" + name + "' was successfully deleted.");
                        } catch (Exception e) {
                            log("A error occurred while trying to perform this action");
                        }
                    } else {
                        log("You cannot delete the last existing user");
                    }
                } else {
                    log("The user '" + name + "' do not exist.");
                }
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("create")) {
                String name = args[1];
                String password = args[2];

                if (!BackData.getInstance().getUserManager().isUserExisting(name)) {
                    BackData.getInstance().getUserManager().createUser(name, password);
                    log("The user '" + name + "' was successfully created.");
                } else {
                    log("The user '" + name + "' already exist.");
                }
            }
        } else {
            log("Wrong syntax! Try 'user help' for more information's about this command.");
        }

    }

    @Override
    public void log(String message) {
        super.log(message);
    }
}
