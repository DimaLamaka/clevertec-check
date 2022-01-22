package by.lamaka.check.service.util;

import by.lamaka.check.entity.Product;
import by.lamaka.check.exceptions.IdNotFoundException;


public interface ProductFactory {
    Product getProduct(String[] params) throws IdNotFoundException;
}
