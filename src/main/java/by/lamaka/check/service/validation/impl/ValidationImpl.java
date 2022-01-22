package by.lamaka.check.service.validation.impl;

import by.lamaka.check.exceptions.ValidateException;
import by.lamaka.check.service.util.ProductParser;
import by.lamaka.check.service.validation.Validation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ValidationImpl implements Validation {
    static String REG = "[0-9]+";

    @Override
    public void validationFields(String[] strings) throws ValidateException {
        boolean isCard = false;
        for (String str : strings) {
            ProductParser parser = s -> s.split("\\s*-\\s*");
            String[] fields = parser.parser(str);

            if (fields.length != 2) {
                throw new ValidateException(str + " incorrect field");
            }
            if (fields[0].matches("card") && fields[1].matches(REG)) {
                if (isCard) {
                    throw new ValidateException("Сheck cannot contain more than 1 card");
                }
                isCard = true;
            } else if (!fields[0].matches(REG) || !fields[1].matches(REG)) {
                throw new ValidateException(str + " incorrect field");
            }
        }
    }

}
