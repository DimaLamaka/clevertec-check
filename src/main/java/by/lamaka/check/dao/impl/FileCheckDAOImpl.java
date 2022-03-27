package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.view.ViewCheck;
import by.lamaka.check.view.impl.ViewCheckImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FileCheckDAOImpl implements CheckDAO {
    static String PATH = "src/main/resources/output.txt";
    ViewCheck viewCheck;

    public FileCheckDAOImpl() {
        viewCheck = new ViewCheckImpl();
    }

    @Override
    public void saveCheck(Check check) throws IOException {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(PATH))) {
            reader.write(viewCheck.getView(check));
            reader.flush();
        }
    }

}
