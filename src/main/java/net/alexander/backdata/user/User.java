package net.alexander.backdata.user;

import java.util.List;

public class User {

    private String username;
    private String password;
    private List<String> permission;

    public List<String> getPermission() {
        return permission;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
