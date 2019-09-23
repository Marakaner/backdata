package net.alexander.backdata.service;

import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.user.UserManager;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private static Map<Class, Service> services = new HashMap<>();

    public static  <T> T getService(Class clazz) {
        return (T) services.get(clazz);
    }

    public static void registerService(Class<Service> clazz) {
        try {
            registerService(clazz, clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerService(Class clazz, Service service) {
        if(!services.containsKey(clazz)) services.put(clazz, service);
    }
}
