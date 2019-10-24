package net.alexander.backdata.event;

import net.alexander.backdata.service.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements Service {

    private List<Class> events;
    private Map<Class, List<Method>> listener;

    public EventManager() {
        this.events = new ArrayList<>();
        this.listener = new HashMap<>();
    }

    public void registerListener(Listener listener) {
        Class clazz = listener.getClass();

        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                for (Parameter parameter : method.getParameters()) {
                    for (Class event : events) {
                        if (parameter.getType().equals(event)) {
                            this.listener.get(event).add(method);
                        }
                    }
                }
            }
        }
    }

    public void registerEvent(Class event) {
        if ((!this.events.contains(event)) || (!this.listener.containsKey(event))) {
            this.events.add(event);
            this.listener.put(event, new ArrayList<>());
        }
    }

    public void fireEvent(Event event) {
        for (Method method : listener.get(event.getClass())) {
            try {
                method.invoke(method.getDeclaringClass().newInstance(), event);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
