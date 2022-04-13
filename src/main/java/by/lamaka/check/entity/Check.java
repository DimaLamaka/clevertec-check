package by.lamaka.check.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Check {
    Long id;
    Map<Product, Integer> productList;
    DiscountCard card;
    LocalDateTime localDateTime;

    public Check(Map<Product, Integer> productList, DiscountCard card, LocalDateTime localDateTime) {
        this.productList = productList;
        this.card = card;
        this.localDateTime = localDateTime;
    }
}
