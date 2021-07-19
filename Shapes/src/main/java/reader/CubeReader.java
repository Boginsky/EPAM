package reader;

import exception.CubeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.CustomFileValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CubeReader {

    private static final Logger logger = LogManager.getLogger();

    public List<String> readDataFromFile(String filename) throws CubeException {
        logger.info("Reading a data file " + filename);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);

        if (inputStream == null) {
            throw new CubeException("Can not find file " + filename);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> correctStrings = reader.lines()
                .filter(CustomFileValidator::rowValidator)
                .collect(Collectors.toList());

        logger.info("Reading a data file " + filename + " finished. String list: " + correctStrings);
        return correctStrings;
    }
}