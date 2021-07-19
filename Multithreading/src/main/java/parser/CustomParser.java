package parser;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String SPLIT_REGEX = "\\s+";


    public List<Integer> parseListOfCharacteristicsOfMcDonalds(List<String> array) throws CustomException {
        List<Integer> result = new ArrayList<>();
        if (array != null && !array.isEmpty()) {
            int i = -1;
            while (++i < array.size()) {
                result = Stream.
                        of(array.get(i).split(SPLIT_REGEX))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            }
        } else {
            logger.error("Invalid list of string for parsing");
            throw new CustomException("Invalid list of strings for parsing");
        }
        logger.info("Info from file was successfully parsed");
        return result;
    }
}
