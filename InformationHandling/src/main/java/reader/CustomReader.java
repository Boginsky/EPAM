package reader;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomReader {

    private static Logger logger = LogManager.getLogger();

    public String readText(String filePath) throws CustomException {
        if (filePath == null) {
            throw new CustomException("Invalid file to read");
        }
        try {
            String text = Files.readString(Paths.get(filePath));
            logger.info("Text was read");
            return text;
        } catch (IOException e) {
            logger.error("Invalid file to read");
            throw new CustomException("Invalid file to read", e);
        }
    }
}
