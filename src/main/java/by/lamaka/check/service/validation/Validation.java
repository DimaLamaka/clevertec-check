package by.lamaka.check.service.validation;

import by.lamaka.check.exceptions.ValidateException;

public interface Validation {
    void validationFields(String[] strings) throws ValidateException;
}
