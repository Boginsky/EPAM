package by.boginsky.validator;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface InputDataValidator {

    boolean isCorrectEmail(String email);

    boolean isCorrectPassword(String password);

    boolean arePasswordsEqual(String firstPassword, String secondPassword);

    boolean isCorrectPrice(BigDecimal price);

    String replaceScript(String value);

    boolean isCorrectUserName(String userName);

}
