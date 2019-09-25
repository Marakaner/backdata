package net.alexander.backdata.user;

import com.google.gson.reflect.TypeToken;
import net.alexander.backdata.BackData;
import net.alexander.backdata.service.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Service {

    private Map<String,User> userMap;

    public UserManager() {
        userMap = new HashMap<>();
        init();
    }

    public User createUser(String name, String password) {
        User user = new User(this.userMap.size() + 1, name, password);
        this.userMap.put(name.toLowerCase(), user);
        saveFile();
        return user;
    }

    public void deleteUser(String name) {
        if(isUserExisting(name)) {
            this.userMap.remove(name.toLowerCase());
            saveFile();
        }
    }

    public User getUser(String name) {
        return this.userMap.getOrDefault(name.toLowerCase(), null);
    }

    public boolean isUserExisting(String name) {
        return this.userMap.containsKey(name.toLowerCase());
    }

    public User login(String username, String password) {
        if(isUserExisting(username)) {
            User user = getUser(username);
            if(user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void init() {
        if(!new File("BackData/user/").exists()) {
            new File("BackData/user/").mkdir();
            File userFile = new File("BackData/user/user.json");
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File userFile = new File("BackData/user/user.json");
            if(!userFile.exists()) {
                try {
                    userFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    userMap = BackData.getInstance().getPublicGson().fromJson(new FileReader(userFile), new TypeToken<HashMap<String, User>>(){}.getType());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void saveFile() {
        new Thread(() -> {
            try {
                FileWriter fileWriter = new FileWriter("BackData/user/user.json");
                BackData.getInstance().getPublicGson().toJson(userMap, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
