package validator;

public class CustomValidator {
    private static final String PATTERN = "(\\b\\d*\\b)*";

    public static boolean validatorOfString(String randomString){
        return randomString.matches(PATTERN);
    }
}
