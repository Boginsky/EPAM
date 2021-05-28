package validator;

import java.util.regex.Pattern;

public class CustomValidator {
    private static final String PATTERN_IS_NUMBER = "[\b\\d+[\\s,-]+]*";

    public static boolean validatorOfString(String randomString) {
        var pattern = Pattern.compile(PATTERN_IS_NUMBER);
        var matcher = pattern.matcher(randomString);
        boolean a = matcher.matches();
        return matcher.matches();
    }
}
