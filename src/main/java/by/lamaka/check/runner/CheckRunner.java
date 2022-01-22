package by.lamaka.check.runner;


import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.CheckService;
import by.lamaka.check.service.impl.CheckServiceImpl;

import java.io.IOException;


public class CheckRunner {
    public static void main(String[] args) {
        CheckService checkService = new CheckServiceImpl();
        try {
            checkService.saveCheck(args);
        } catch (IdNotFoundException | ValidateException | IOException | EmptyProductList e) {
            System.err.println(e);
        }
    }
}
