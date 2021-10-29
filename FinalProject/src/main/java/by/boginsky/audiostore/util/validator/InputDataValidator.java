package by.boginsky.audiostore.util.validator;

import java.math.BigDecimal;

/**
 * The interface Input data validator.
 */
public interface InputDataValidator {

    /**
     * Is correct email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isCorrectEmail(String email);

    /**
     * Is correct password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isCorrectPassword(String password);

    /**
     * Are passwords equal boolean.
     *
     * @param firstPassword  the first password
     * @param secondPassword the second password
     * @return the boolean
     */
    boolean arePasswordsEqual(String firstPassword, String secondPassword);

    /**
     * Is correct price boolean.
     *
     * @param price the price
     * @return the boolean
     */
    boolean isCorrectPrice(BigDecimal price);

    /**
     * Is correct user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    boolean isCorrectUserName(String userName);

}
