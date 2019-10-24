package net.alexander.backdata.user;

import net.alexander.backdata.BackData;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private List<String> permission;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = new ArrayList<>();
    }

    public List<String> getPermission() {
        return permission;
    }

    public boolean hasPermission(String permission) {
        return this.permission.contains(permission.toLowerCase());
    }

    public void addPermission(String permission) {
        if (!hasPermission(permission)) {
            this.permission.add(permission);
            BackData.getInstance().getUserManager().saveFile();
        }
    }

    public void removePermission(String permission) {
        if (hasPermission(permission)) {
            this.permission.remove(permission.toLowerCase());
            BackData.getInstance().getUserManager().saveFile();
        }
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
