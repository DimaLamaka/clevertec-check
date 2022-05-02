package by.lamaka.check.service;

import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.exceptions.ValidateException;

import java.io.IOException;

public interface CheckService {
    void saveCheck(String[] productList) throws ValidateException, IdNotFoundException, IOException, EmptyProductList, MailAuthenticationException;
}
