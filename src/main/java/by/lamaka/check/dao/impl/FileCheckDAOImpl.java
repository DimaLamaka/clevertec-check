package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.listeners.EventManager;
import by.lamaka.check.view.ViewCheck;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class FileCheckDAOImpl implements CheckDAO {
    @Value("${output.path}")
    String path;
    ViewCheck viewCheck;
    EventManager manager;

    @Override
    public void saveCheck(Check check) throws IOException, MailAuthenticationException {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(path))) {
            String checkView = viewCheck.getView(check);
            reader.write(checkView);
            reader.flush();
            manager.addOperation("save in file");
            manager.notify("save in file", checkView);
        }
    }
}
