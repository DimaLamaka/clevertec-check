package by.lamaka.check.service;

import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.listeners.EventListener;

public interface MailService extends EventListener {
    void sendEmail(String subject,String message) throws MailAuthenticationException;
}
