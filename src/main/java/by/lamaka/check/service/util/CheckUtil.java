package by.lamaka.check.service.util;

import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;


import java.util.HashMap;
import java.util.Map;


public class CheckUtil {
    private CheckUtil() {
    }

    public static Map<String, Double> getSumsByCheck(Check check) {
        Map<String, Double> checkSums = new HashMap<>();
        double totalProdSum = 0;
        double totalDiscSum = 0;
        double totalDiscSumByCard = 0;
        double totalSum = 0;

        for (Map.Entry<Product, Integer> entry : check.getProducts().entrySet()) {
            double prodSum = entry.getKey().getPrice() * entry.getValue();
            totalProdSum += entry.getKey().getPrice() * entry.getValue();
            if (entry.getValue() > 5) {
                totalDiscSum += prodSum / 10;
            }
        }
        if (check.getCard() != null) {
            totalDiscSumByCard = totalProdSum / 100 * check.getCard().getDiscount();
        }
        totalSum = totalProdSum - totalDiscSum - totalDiscSumByCard;

        checkSums.put("totalProdSum", totalProdSum);
        checkSums.put("totalDiscSum", totalDiscSum);
        checkSums.put("totalDiscSumByCard", totalDiscSumByCard);
        checkSums.put("totalSum", totalSum);
        return checkSums;
    }

}
