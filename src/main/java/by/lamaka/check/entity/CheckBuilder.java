package by.lamaka.check.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckBuilder {
    Long id;
    Map<Product, Integer> products;
    DiscountCard card;
    LocalDateTime dateTime;

    public CheckBuilder id(Long id){
        this.id = id;
        return this;
    }
    public CheckBuilder products(Map<Product, Integer> products){
        this.products = products;
        return this;
    }
    public CheckBuilder card(DiscountCard card){
        this.card = card;
        return this;
    }
    public CheckBuilder dateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
        return this;
    }
    public Check build(){
        return new Check(id,products,card,dateTime);
    }
}
