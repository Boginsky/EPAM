package validator;

import java.io.File;

public class CustomFileValidator {

    private static final String ROW_REGEXP = "^(-?\\d+(\\.\\d+)?)\\s(-?\\d+(\\.\\d+)?)\\s(-?\\d+(\\.\\d+)?)\\s(\\d+(\\.\\d+)?)$";

    public static boolean isFileValid(String fileAddress) {
        if (fileAddress == null) {
            return false;
        }
        File file = new File(fileAddress);
        if (file.exists() && file.canRead() && file.isFile() && file.length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean rowValidator(String row) {
        return row.matches(ROW_REGEXP);
    }
}
