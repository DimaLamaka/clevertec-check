package by.lamaka.check.service.listeners;

import by.lamaka.check.exceptions.MailAuthenticationException;

public interface EventListener {
    void update(String eventType, String object) throws MailAuthenticationException;
}
