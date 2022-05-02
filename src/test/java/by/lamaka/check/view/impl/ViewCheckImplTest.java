package by.lamaka.check.view.impl;

import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.DiscountCard;
import by.lamaka.check.entity.Product;
import by.lamaka.check.view.ViewCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ViewCheckImplTest {
    private Check check;
    private ViewCheck viewCheck;

    @BeforeEach
    void setUp() {
        check = new Check();
        viewCheck = new ViewCheckImpl();
        check.setCard(DiscountCard.BRONZECARD);
        check.setDateTime(LocalDateTime.of(2021,3,20,20,14,33));
        Map<Product,Integer> productList = new HashMap<>();
        productList.put(new Product(9, "Cookie", 1.70),10);
        productList.put(new Product(8, "Tea", 3.20),2);
        check.setProducts(productList);
    }

    @Test
    void getViewShouldSuccess() throws IOException {
        String excepted = "         CASH RECEIPT\n" +
                "Cashier: #0139\n" +
                "Date: 2021-03-20\n" +
                "Time: 08:14:33\n" +
                "-------------------------------\n" +
                "QTY DESCRIPTION    PRICE  TOTAL\n" +
                "10  Cookie         $1,70  $17,00\n" +
                "         discount QTY>5: -$1,70\n" +
                "2   Tea            $3,20  $6,40\n" +
                "-------------------------------\n" +
                "Total sum:               $23,40\n" +
                "Discount sum qty>5:      -$1,70\n" +
                "Discount by card 5%:     -$1,17\n" +
                "TOTAL:                   $20,53";
        String actual = viewCheck.getView(check);

        assertEquals(excepted,actual);
    }
}