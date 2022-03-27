package by.lamaka.check.service.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.dao.impl.ConsoleCheckDAOImpl;
import by.lamaka.check.dao.impl.FileCheckDAOImpl;
import by.lamaka.check.entity.DiscountCard;
import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;
import by.lamaka.check.entity.ProductList;
import by.lamaka.check.exceptions.EmptyProductList;
import by.lamaka.check.exceptions.IdNotFoundException;
import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.CheckService;
import by.lamaka.check.service.util.*;
import by.lamaka.check.service.validation.Validation;
import by.lamaka.check.service.validation.impl.ValidationImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CheckServiceImpl implements CheckService {
    Validation validator;
    CheckDAO checkDAO;

    public CheckServiceImpl() {
        validator = new ValidationImpl();
        checkDAO = new ConsoleCheckDAOImpl(new FileCheckDAOImpl());
    }

    public void saveCheck(String[] productList) throws ValidateException, IdNotFoundException, IOException, EmptyProductList {
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
        CheckMapper checkMapper = (listP, myCard) -> new Check(listP, myCard, LocalDateTime.now());
        Check check = checkMapper.getCheck(productsWithCount, card);
        checkDAO.saveCheck(check);
    }
}
