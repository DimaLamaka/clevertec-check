package by.lamaka.check.runner;


import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.CheckService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class CheckRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.lamaka.check");
        CheckService bean = context.getBean(CheckService.class);

        try {
            /*bean.saveCheck(args);*/
            bean.saveCheck(new String[]{"card-3156","7-6","1-3","2-5"});
        } catch (IdNotFoundException | ValidateException | IOException | EmptyProductList e) {
            System.err.println(e);
        }
    }
}
