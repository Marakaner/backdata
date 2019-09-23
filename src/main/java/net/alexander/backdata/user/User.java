package net.alexander.backdata.user;

import net.alexander.backdata.BackData;
import net.alexander.backdata.service.ServiceManager;

import java.util.ArrayList;
import java.util.List;

public class User {

    public int id;
    public String username;
    public String password;
    public List<String> permission;

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
        if(!hasPermission(permission)) {
            this.permission.add(permission);
            BackData.getInstance().getUserManager().saveFile();
        }
    }

    public void removePermission(String permission) {
        if(hasPermission(permission)) {
            this.permission.remove(permission.toLowerCase());
            BackData.getInstance().getUserManager().saveFile();
        }
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
