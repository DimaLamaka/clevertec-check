package by.lamaka.check.entity;

import by.lamaka.check.exceptions.IdNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductList {
    static List<Product> products;

    private ProductList() {
    }

    static {
        products = new ArrayList<>();
        products.add(new ProductBuilder().id(1).title("Sugar").price(1.60).build());
        products.add(new ProductBuilder().id(2).title("Eggs").price(2.10).build());
        products.add(new ProductBuilder().id(3).title("Bread").price(1.05).build());
        products.add(new ProductBuilder().id(4).title("Milk").price(1.25).build());
        products.add(new ProductBuilder().id(5).title("Apple").price(2.05).build());
        products.add(new ProductBuilder().id(6).title("Water").price(1).build());
        products.add(new ProductBuilder().id(7).title("Coffee").price(5.80).build());
        products.add(new ProductBuilder().id(8).title("Tea").price(3.20).build());
        products.add(new ProductBuilder().id(9).title("Cookie").price(1.70).build());
        products.add(new ProductBuilder().id(10).title("Beer").price(2.30).build());
    }

    public static Product getProductById(int id) throws IdNotFoundException {
        if (id > products.size()) {
            throw new IdNotFoundException("Id " + id + " not found");
        }
        return products.get(id - 1);
    }
}
