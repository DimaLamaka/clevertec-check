package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.view.ViewCheck;
import by.lamaka.check.view.impl.ViewCheckImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ConsoleCheckDAOImpl implements CheckDAO {
    CheckDAO checkDAO;
    ViewCheck viewCheck;

    public ConsoleCheckDAOImpl() {
        viewCheck = new ViewCheckImpl();
    }

    public ConsoleCheckDAOImpl(CheckDAO checkDAO) {
        this.checkDAO = checkDAO;
        viewCheck = new ViewCheckImpl();
    }

    @Override
    public void saveCheck(Check check) throws IOException {
        if (checkDAO != null) {
            checkDAO.saveCheck(check);
        }
        System.out.println(viewCheck.getView(check));
    }
}
