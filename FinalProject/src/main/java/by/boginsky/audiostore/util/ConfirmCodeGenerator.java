package by.boginsky.audiostore.util;

public class ConfirmCodeGenerator {
    public static String generateConfirmCode() {
        return java.util.UUID.randomUUID().toString();
    }
}
