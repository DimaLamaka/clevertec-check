package by.lamaka.check.entity;

import by.lamaka.check.exceptions.IdNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductList {
    static List<Product> productList;

    static {
        productList = new ArrayList<>();
        productList.add(new Product(1, "Sugar", 1.60));
        productList.add(new Product(2, "Eggs", 2.10));
        productList.add(new Product(3, "Bread", 1.05));
        productList.add(new Product(4, "Milk", 1.25));
        productList.add(new Product(5, "Apple", 2.05));
        productList.add(new Product(6, "Water", 1));
        productList.add(new Product(7, "Coffee", 5.80));
        productList.add(new Product(8, "Tea", 3.20));
        productList.add(new Product(9, "Cookie", 1.70));
        productList.add(new Product(10, "Beer", 2.30));
    }

    public static Product getProductById(int id) throws IdNotFoundException {
        if (id > productList.size()) {
            throw new IdNotFoundException("Id " + id + " not found");
        }
        return productList.get(id - 1);
    }
}
