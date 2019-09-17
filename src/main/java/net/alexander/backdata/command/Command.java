package net.alexander.backdata.command;

import net.alexander.backdata.service.Service;
import net.alexander.backdata.user.User;

import java.util.logging.LogManager;

public abstract class Command {

    private String name;
    private String permission;

    public Command(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public Command(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public abstract void execute(String[] args);

}
