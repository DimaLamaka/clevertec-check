package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.DiscountCard;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.listeners.EventManager;
import by.lamaka.check.view.ViewCheck;
import by.lamaka.check.view.impl.ViewCheckImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileCheckDAOImplTest {
    private CheckDAO checkDAO;
    private ViewCheck viewCheck;
    private Check check;
    private EventManager eventManager;

    @BeforeEach
    public void setUp() {
        viewCheck = mock(ViewCheckImpl.class);
        eventManager = mock(EventManager.class);
        checkDAO = new FileCheckDAOImpl(viewCheck,eventManager);
        check = new Check();
        check.setCard(DiscountCard.BRONZECARD);
        check.setDateTime(LocalDateTime.now());
    }

    @Test
    public void saveCheckShouldSuccess() throws IOException, MailAuthenticationException {
        String excepted = check.toString();
        when(viewCheck.getView(check)).thenReturn(excepted);

        checkDAO.saveCheck(check);
        String actual = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/output.txt"))) {
            while (bufferedReader.ready()) {
                actual += bufferedReader.readLine();
            }
        }

        assertEquals(actual, excepted);

    }
}