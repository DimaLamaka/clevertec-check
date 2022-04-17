package by.lamaka.check.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBuilder {
    int id;
    String title;
    double price;

    public ProductBuilder id(int id){
        this.id = id;
        return this;
    }
    public ProductBuilder title(String title){
        this.title = title;
        return this;
    }
    public ProductBuilder price(double price){
        this.price = price;
        return this;
    }
    public Product build(){
        return new Product(id,title,price);
    }
}
