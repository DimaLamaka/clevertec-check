package by.lamaka.check.service.util;

import by.lamaka.check.entity.DiscountCard;
import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;

import java.util.Map;

public interface CheckMapper {
    Check getCheck(Map<Product, Integer> productList, DiscountCard card);
}
