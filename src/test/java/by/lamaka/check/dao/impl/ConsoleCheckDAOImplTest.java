package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.DiscountCard;
import by.lamaka.check.view.ViewCheck;
import by.lamaka.check.view.impl.ViewCheckImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsoleCheckDAOImplTest {
    private CheckDAO checkDAO;
    private ViewCheck viewCheck;
    private Check check;
    private ConsoleCheckDAOImpl consoleCheckDAO;

    @BeforeEach
    public void setUp() {
        checkDAO = mock(FileCheckDAOImpl.class);
        viewCheck = mock(ViewCheckImpl.class);
        consoleCheckDAO = new ConsoleCheckDAOImpl(checkDAO, viewCheck);
        check = new Check();
        check.setCard(DiscountCard.BRONZECARD);
        check.setLocalDateTime(LocalDateTime.now());
    }
    @Test
    public void shouldCreateConsoleCheckDAOImplWithoutParams(){
        CheckDAO checkDAO = new ConsoleCheckDAOImpl();
        assertNotNull(checkDAO);
    }

    @Test
    public void saveCheckShouldSaveCheckInFile() throws IOException {
        consoleCheckDAO.saveCheck(check);
        verify(checkDAO, times(1)).saveCheck(check);
    }

    @Test
    public void saveCheckShouldGetViewCheck() throws IOException {
        String excepted = check.toString();
        when(viewCheck.getView(check)).thenReturn(excepted);
        consoleCheckDAO.saveCheck(check);

        String actual = viewCheck.getView(check);
        assertEquals(excepted, actual);
        verify(viewCheck, times(2)).getView(check);
    }

}