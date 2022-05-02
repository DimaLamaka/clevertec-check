package by.lamaka.check.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailConfig {
    Environment environment;
    static String MAIL_USER = "mail.user";
    static String MAIL_PASSWORD = "mail.password";
    static String MAIL_SMTP_HOST = "mail.smtp.host";
    static String MAIL_SMTP_PORT = "mail.smtp.port";
    static String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    static String MAIL_SMTP_AUTH = "mail.smtp.auth";
    static String MAIL_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    static String MAIL_DEBUG = "mail.debug";
    static String MAIL_SMTP_SSL_PROTOCOLS = "mail.smtp.ssl.protocols";
    static String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";

    @Autowired
    public MailConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setUsername(environment.getProperty(MAIL_USER));
        sender.setPassword(environment.getProperty(MAIL_PASSWORD));
        sender.setHost(environment.getProperty(MAIL_SMTP_HOST));
        sender.setPort(environment.getProperty(MAIL_SMTP_PORT, Integer.class, 587));

        Properties javaMailProperties = sender.getJavaMailProperties();
        javaMailProperties.put(MAIL_TRANSPORT_PROTOCOL, environment.getProperty(MAIL_TRANSPORT_PROTOCOL));
        javaMailProperties.put(MAIL_SMTP_AUTH, environment.getProperty(MAIL_SMTP_AUTH));
        javaMailProperties.put(MAIL_STARTTLS_ENABLE, environment.getProperty(MAIL_STARTTLS_ENABLE));
        javaMailProperties.put(MAIL_DEBUG, environment.getProperty(MAIL_DEBUG));
        javaMailProperties.put(MAIL_SMTP_SSL_PROTOCOLS, environment.getProperty(MAIL_SMTP_SSL_PROTOCOLS));
        javaMailProperties.put(MAIL_SMTP_SSL_TRUST, environment.getProperty(MAIL_SMTP_SSL_TRUST));
        return sender;
    }
}
