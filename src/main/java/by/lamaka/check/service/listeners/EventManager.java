package by.lamaka.check.service.listeners;

import by.lamaka.check.exceptions.MailAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventManager {
    private static final Map<String, List<EventListener>> listeners = new HashMap<>();

    public void addOperation(String... operations) {
        for (String operation : operations) {
            listeners.computeIfAbsent(operation, s-> new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        if (Objects.isNull(users)) {
            listeners.put(eventType, new ArrayList<>());
            users = listeners.get(eventType);
        }
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String object) throws MailAuthenticationException {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, object);
        }
    }
}
