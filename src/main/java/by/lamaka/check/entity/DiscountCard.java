package by.lamaka.check.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum DiscountCard {
    BRONZECARD(Arrays.asList("1245", "1386", "1953", "1852"), 5),
    SILVERCARD(Arrays.asList("2081", "2581", "2739", "2901"), 7),
    GOLDCARD(Arrays.asList("3156", "3691", "3100", "3710"), 10);

    List<String> cardNumbers;
    int discount;//percents

    public static DiscountCard getCardByNumber(String number) {
        for (DiscountCard card : DiscountCard.values()) {
            if (card.getCardNumbers().contains(number)) {
                return card;
            }
        }
        return null;
    }
}
