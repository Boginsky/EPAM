package by.boginsky.audiostore.util;

/**
 * The type Confirm code generator.
 */
public class ConfirmCodeGenerator {

    /**
     * Generate confirm code string.
     *
     * @return the string
     */
    public static String generateConfirmCode() {
        return java.util.UUID.randomUUID().toString();
    }
}
