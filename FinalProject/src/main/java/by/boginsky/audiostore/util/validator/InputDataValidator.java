package by.boginsky.audiostore.util.validator;

import java.math.BigDecimal;

public interface InputDataValidator {

    boolean isCorrectEmail(String email);

    boolean isCorrectPassword(String password);

    boolean arePasswordsEqual(String firstPassword, String secondPassword);

    boolean isCorrectPrice(BigDecimal price);

}
