package by.lamaka.check.service.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.dao.impl.ConsoleCheckDAOImpl;
import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.CheckService;
import by.lamaka.check.service.validation.Validation;
import by.lamaka.check.service.validation.impl.ValidationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckServiceImplTest {
    private CheckDAO checkDAO;
    private Validation validation;
    private CheckService checkService;

    @BeforeEach
    void setUp() {
        checkDAO = mock(ConsoleCheckDAOImpl.class);
        validation = mock(ValidationImpl.class);
        checkService = new CheckServiceImpl(validation, checkDAO);
    }

    @Test
    void saveCheckShouldSuccess() throws ValidateException, IOException, EmptyProductList, IdNotFoundException {
        String[] productList = {"1-2","3-7","5-6"};
        doNothing().when(validation).validationFields(productList);
        doNothing().when(checkDAO).saveCheck(any());

        checkService.saveCheck(productList);

        verify(validation, times(1)).validationFields(productList);
        verify(checkDAO, times(1)).saveCheck(any());
    }
    @Test
    void saveCheckWithCardShouldSuccess() throws ValidateException, IOException, EmptyProductList, IdNotFoundException {
        String[] productList = {"1-2","card-3156","5-6"};
        doNothing().when(validation).validationFields(productList);
        doNothing().when(checkDAO).saveCheck(any());

        checkService.saveCheck(productList);

        verify(validation, times(1)).validationFields(productList);
        verify(checkDAO, times(1)).saveCheck(any());
    }
    @Test
    void saveCheckWithEmptyParamsShouldThrowEmptyProductList() throws ValidateException, IOException, EmptyProductList, IdNotFoundException {
        String[] productList = {};
        doNothing().when(validation).validationFields(productList);

        assertThrows(EmptyProductList.class,()->checkService.saveCheck(productList));
        verify(validation, times(1)).validationFields(productList);

    }

    @Test
    void saveCheckWithEqualsProductsShouldSuccess() throws ValidateException, IOException, EmptyProductList, IdNotFoundException {
        String[] productList = {"1-2","1-5","5-6"};
        doNothing().when(validation).validationFields(productList);
        doNothing().when(checkDAO).saveCheck(any());

        checkService.saveCheck(productList);

        verify(validation, times(1)).validationFields(productList);
        verify(checkDAO, times(1)).saveCheck(any());
    }
}