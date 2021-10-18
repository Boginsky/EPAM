package by.boginsky.audiostore.util.validator.impl;

import by.boginsky.audiostore.util.validator.InputDataValidator;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

public final class InputDataValidatorImpl implements InputDataValidator {

    private static InputDataValidator instance;
    private static final AtomicBoolean isInputDataValidator = new AtomicBoolean(false);
    private static final String CHECK_EMAIL = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String CHECK_PASSWORD = "[0-9a-zA-Z]{1,20}";
    private static final int MAX_LENGTH_USER_NAME = 255;
    private static final String REPLACE_SCRIPT = "</?script>";

    private InputDataValidatorImpl() {
    }

    public static InputDataValidator getInstance() {
        while (instance == null) {
            if (isInputDataValidator.compareAndSet(false, true)) {
                instance = new InputDataValidatorImpl();
            }
        }
        return instance;
    }

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
    public boolean isCorrectPrice(BigDecimal price) {
        return price.doubleValue() > 0;
    }

    public boolean isCorrectUserName(String userName){ return userName.length() <= MAX_LENGTH_USER_NAME;}

    public String replaceScript(String value){
        return value.replaceAll(REPLACE_SCRIPT, "");
    }

}
