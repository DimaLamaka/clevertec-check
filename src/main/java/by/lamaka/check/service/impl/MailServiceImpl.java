package by.lamaka.check.service.impl;

import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.MailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailServiceImpl implements MailService {
    JavaMailSender mailSender;
    String mailTo;
    String mailFrom;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, @Value("${mail.to}") String mailTo, @Value("${mail.user}") String mailFrom) {
        this.mailSender = mailSender;
        this.mailTo = mailTo;
        this.mailFrom = mailFrom;
    }

    @Override
    public void sendEmail(String subject, String message) throws MailAuthenticationException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            throw new MailAuthenticationException("Authentication failed");
        }
    }

    @Override
    public void update(String eventType, String object) throws MailAuthenticationException {
        sendEmail(eventType, object);
    }
}
