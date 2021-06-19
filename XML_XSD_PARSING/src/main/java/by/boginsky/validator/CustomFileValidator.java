package by.boginsky.validator;

import java.io.File;

public class CustomFileValidator {

    public static boolean isFileValid(String path) {
        if (path == null) {
            return false;
        }
        File file = new File(path);
        if (file.exists() && file.canRead() && file.isFile() && file.length() > 0) {
            return true;
        }
        return false;
    }
}
