package by.lamaka.check.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Check {
    Long id;
    Map<Product, Integer> products;
    DiscountCard card;
    LocalDateTime dateTime;
}
