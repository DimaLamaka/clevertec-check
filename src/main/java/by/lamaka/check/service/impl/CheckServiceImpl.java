package by.lamaka.check.service.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.*;
import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.CheckService;
import by.lamaka.check.service.MailService;
import by.lamaka.check.service.listeners.EventManager;
import by.lamaka.check.service.util.*;
import by.lamaka.check.service.validation.Validation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class CheckServiceImpl implements CheckService {
    Validation validator;
    CheckDAO checkDAO;
    MailService mailService;
    EventManager manager;

    public CheckServiceImpl(Validation validator,@Qualifier("consoleCheckDAOImpl") CheckDAO checkDAO,
                            MailService mailService, EventManager manager) {
        this.validator = validator;
        this.checkDAO = checkDAO;
        this.mailService = mailService;
        this.manager = manager;
    }

    public void saveCheck(String[] productList) throws ValidateException, IdNotFoundException, IOException, EmptyProductList, MailAuthenticationException {
        validator.validationFields(productList);
        Map<Product, Integer> productsWithCount = new HashMap<>();
        DiscountCard card = null;
        Product product = null;
        int count = 0;

        for (String prod : productList) {
            ProductParser parser = s -> s.split("\\s*-\\s*");
            String[] params = parser.parser(prod);

            if (params[0].toLowerCase().matches("card")) {
                CardFactory cardFactory = s -> DiscountCard.getCardByNumber(s[1]);
                card = cardFactory.getCard(params);
            } else {
                ProductFactory productFactory = s -> ProductList.getProductById(Integer.parseInt(s[0]));
                product = productFactory.getProduct(params);
                count = Integer.parseInt(params[1]);

                if (productsWithCount.containsKey(product)) {
                    productsWithCount.put(product, count + productsWithCount.get(product));
                } else {
                    productsWithCount.put(product, count);
                }
            }
        }
        if (productsWithCount.isEmpty()) {
            throw new EmptyProductList("Product list is empty");
        }
        CheckMapper checkMapper = (listP, myCard) ->
                new CheckBuilder()
                .products(listP)
                .card(myCard)
                .dateTime(LocalDateTime.now())
                .build();
        Check check = checkMapper.getCheck(productsWithCount, card);

        manager.subscribe("print",mailService);
        checkDAO.saveCheck(check);
    }
}
