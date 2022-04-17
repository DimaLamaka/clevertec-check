package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.listeners.EventManager;
import by.lamaka.check.view.ViewCheck;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Component
public class ConsoleCheckDAOImpl implements CheckDAO {
    CheckDAO checkDAO;
    ViewCheck viewCheck;
    EventManager manager;

    @Autowired
    public ConsoleCheckDAOImpl(@Qualifier("databaseDAOImpl") CheckDAO checkDAO, ViewCheck viewCheck, EventManager manager) {
        this.checkDAO = checkDAO;
        this.viewCheck = viewCheck;
        this.manager = manager;
    }

    @Override
    public void saveCheck(Check check) throws IOException, MailAuthenticationException {
        if (checkDAO != null) {
            checkDAO.saveCheck(check);
        }
        String checkView = viewCheck.getView(check);
        System.out.println(checkView);
        manager.addOperation("print");
        manager.notify("print",checkView);
    }
}
