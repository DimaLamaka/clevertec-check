package by.lamaka.check.exceptions.view.impl;

import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;
import by.lamaka.check.exceptions.view.ViewCheck;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ViewCheckImpl implements ViewCheck {
    @Override
    public String getView(Check check) {
        StringBuilder view = new StringBuilder();
        String str = "";
        double totalProdSum = 0;
        double totalDiscSum = 0;
        double totalDiscSumByCard = 0;
        double totalSum = 0;
        view.append(String.format("%21s\n", "CASH RECEIPT"))
                .append(String.format("%s\n", "Cashier: #0139"))
                .append(String.format("Date: %s\n", check.getLocalDateTime().toLocalDate().toString()))
                .append(String.format("Time: %s\n", check.getLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"))))
                .append("-------------------------------\n")
                .append(String.format("%-2s %-14s %-6s %s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL"));
        for (Map.Entry<Product, Integer> entry : check.getProductList().entrySet()) {
            double prodSum = entry.getKey().getPrice() * entry.getValue();
            totalProdSum += prodSum;
            str = String.format("%-3s %-14s $%-5.2f $%.2f\n", entry.getValue(), entry.getKey().getTitle(), entry.getKey().getPrice(), prodSum);
            view.append(str);
            if (entry.getValue() > 5) {
                view.append(String.format("%24s -$%.2f\n", "discount QTY>5:", prodSum / 10));
                totalDiscSum += prodSum / 10;
            }
        }
        view.append("-------------------------------\n")
                .append(String.format("%-24s $%.2f\n", "Total sum:", totalProdSum))
                .append(String.format("%-24s -$%.2f\n", "Discount sum qty>5:", totalDiscSum));
        if (check.getCard() != null) {
            totalDiscSumByCard = totalProdSum / 100 * check.getCard().getDiscount();
            view.append(String.format("%-24s -$%.2f\n", "Discount by card " + check.getCard().getDiscount() + "%:", totalDiscSumByCard));
        }
        totalSum = totalProdSum - totalDiscSum - totalDiscSumByCard;
        view.append(String.format("%-24s $%.2f", "TOTAL:", totalSum));
        return view.toString();
    }
}
