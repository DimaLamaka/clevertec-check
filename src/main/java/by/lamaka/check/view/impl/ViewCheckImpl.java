package by.lamaka.check.view.impl;

import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;
import by.lamaka.check.service.util.CheckUtil;
import by.lamaka.check.view.ViewCheck;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ViewCheckImpl implements ViewCheck {
    @Override
    public String getView(Check check) {
        StringBuilder view = new StringBuilder();
        Map<String, Double> sumsByCheck = CheckUtil.getSumsByCheck(check);
        double totalProdSum = sumsByCheck.get("totalProdSum");
        double totalDiscSum = sumsByCheck.get("totalDiscSum");
        double totalDiscSumByCard = sumsByCheck.get("totalDiscSumByCard");
        double totalSum = sumsByCheck.get("totalSum");

        view.append(String.format("%21s\n", "CASH RECEIPT"))
                .append(String.format("%s\n", "Cashier: #0139"))
                .append(String.format("Date: %s\n", check.getDateTime().toLocalDate().toString()))
                .append(String.format("Time: %s\n", check.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"))))
                .append("-------------------------------\n")
                .append(String.format("%-2s %-14s %-6s %s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL"));

        for (Map.Entry<Product, Integer> entry : check.getProducts().entrySet()) {
            double prodSum = entry.getKey().getPrice() * entry.getValue();
            view.append(String.format("%-3s %-14s $%-5.2f $%.2f\n", entry.getValue(), entry.getKey().getTitle(), entry.getKey().getPrice(), prodSum));
            if (entry.getValue() > 5) {
                view.append(String.format("%24s -$%.2f\n", "discount QTY>5:", prodSum / 10));
            }
        }

        view.append("-------------------------------\n")
                .append(String.format("%-24s $%.2f\n", "Total sum:", totalProdSum))
                .append(String.format("%-24s -$%.2f\n", "Discount sum qty>5:", totalDiscSum));

        if (check.getCard() != null) {
            view.append(String.format("%-24s -$%.2f\n", "Discount by card " + check.getCard().getDiscount() + "%:", totalDiscSumByCard));
        }

        view.append(String.format("%-24s $%.2f", "TOTAL:", totalSum));
        return view.toString();
    }
}
