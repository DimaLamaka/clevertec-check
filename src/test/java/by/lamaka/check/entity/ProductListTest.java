package by.lamaka.check.entity;

import by.lamaka.check.exceptions.IdNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {

    @Test
    void getProductByIdShouldSuccess() throws IdNotFoundException {
        Product expected1 = new Product(1, "Sugar", 1.60);
        Product expected2 = new Product(10, "Beer", 2.30);

        assertEquals(expected1, ProductList.getProductById(1));
        assertEquals(expected2, ProductList.getProductById(10));
    }

    @Test
    void getProductByIdShouldThrowIdNotFoundException() throws IdNotFoundException {
        assertThrows(IdNotFoundException.class, () -> ProductList.getProductById(20));
    }
}