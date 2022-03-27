package by.lamaka.check.service.validation.impl;

import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.validation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationImplTest {
    private Validation validation;

    @BeforeEach
    void setUp() {
        validation = new ValidationImpl();
    }

    @Test
    void validationFieldsShouldSuccess() throws ValidateException {
        String[] str = {"1-2", "3-4", "5-6"};

        assertDoesNotThrow(() -> validation.validationFields(str));
    }
    @Test
    void validationFieldsShouldThrowValidateExceptionWhenIncorrectField() throws ValidateException {
        String[] str = {"2", "3-4", "5-6"};

        assertThrows(ValidateException.class,()->validation.validationFields(str));
    }
    @Test
    void validationFieldsShouldThrowValidateExceptionWhenTwoCards() throws ValidateException {
        String[] str = {"card-2422", "card-4124", "5-6"};

        assertThrows(ValidateException.class,()->validation.validationFields(str));
    }
    @Test
    void validationFieldsShouldThrowValidateExceptionWhenFieldsContainsNotNumbers() throws ValidateException {
        String[] str = {"c-2", "a-4", "5-6"};

        assertThrows(ValidateException.class,()->validation.validationFields(str));
    }
}