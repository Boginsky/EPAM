package by.boginsky.validator.impl;

import by.boginsky.validator.InputDataValidator;

import java.math.BigDecimal;

public final class InputDataValidatorImpl implements InputDataValidator {

    private static final String CHECK_EMAIL = "[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@" +
            "([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*([a-z]{2,4})";
    private static final String CHECK_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    private static final String REPLACE_SCRIPT = "</?script>";
    private static final int MAX_LOGIN_LENGTH = 20;
    private static final int MAX_LENGTH_USER_NAME = 255;

    @Override
    public boolean isCorrectEmail(String email) {
        return email.matches(CHECK_EMAIL);
    }

    @Override
    public boolean isCorrectPassword(String password) {
        return password.matches(CHECK_PASSWORD);
    }

    @Override
    public boolean arePasswordsEqual(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }

    @Override
    public boolean isCorrectLogin(String login) {
        return login.length() <= MAX_LOGIN_LENGTH;
    }

    @Override
    public boolean isCorrectPrice(BigDecimal price) {
        return price.doubleValue() > 0;
    }

    @Override
    public String replaceScript(String value) {
        return value.replaceAll(REPLACE_SCRIPT,"");
    }

    @Override
    public boolean isCorrectUserName(String userName) {
        return userName.length() <= MAX_LENGTH_USER_NAME;
    }
}
