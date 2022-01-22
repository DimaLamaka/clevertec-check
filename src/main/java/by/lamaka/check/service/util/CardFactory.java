package by.lamaka.check.service.util;

import by.lamaka.check.entity.DiscountCard;


public interface CardFactory {
    DiscountCard getCard(String[] card);
}
