package by.lamaka.check.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardTest {

    @Test
    void getCardByNumberShouldSuccess() {
        DiscountCard expected = DiscountCard.GOLDCARD;
        DiscountCard actual = DiscountCard.getCardByNumber("3156");

        assertEquals(expected,actual);
    }
    @Test
    void getCardByNumberShouldReturnNull() {
        DiscountCard actual = DiscountCard.getCardByNumber("53353");
        assertNull(actual);
    }

    @Test
    void getDiscountByCard(){
        DiscountCard card = DiscountCard.GOLDCARD;
        int expected = 10;
        int actual = card.getDiscount();

        assertEquals(expected,actual);
    }
}