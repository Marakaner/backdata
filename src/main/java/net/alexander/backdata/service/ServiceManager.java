package net.alexander.backdata.service;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private static Map<Class, Service> services = new HashMap<>();

    public static <T> T getService(Class clazz) {
        if(!services.containsKey(clazz)) return null;
        return (T) services.get(clazz);
    }

    /**
     * Register a internal Service.
     *
     * @param clazz has to implement the Service class
     */
    public static void registerService(Class<Service> clazz) {
        try {
            registerService(clazz, clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerService(Class<?> clazz, Service service) {
        if (!services.containsKey(clazz)) services.put(clazz, service);
    }
}
