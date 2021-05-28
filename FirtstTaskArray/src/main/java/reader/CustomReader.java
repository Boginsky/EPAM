package reader;


import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CustomReader {

    static Logger logger = LogManager.getLogger();

    public ArrayList<String> dataFromFile(String path) throws CustomException {
        ArrayList<String> rowList = new ArrayList<>();
        if (!Files.exists(Path.of(path))) {
            throw new CustomException("File doesn't exist");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rowList.add(line);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        logger.debug("Finish of reading file");
        return rowList;
    }
}
