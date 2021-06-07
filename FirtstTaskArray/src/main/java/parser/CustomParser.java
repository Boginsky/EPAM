package parser;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.CustomValidator;

import java.util.ArrayList;
import java.util.List;

public class CustomParser {

    static Logger logger = LogManager.getLogger();
    private static final String SEPARATOR = "\\s";

    public int[] parserOfRowArray(List<String> rowList) throws CustomException {

        if (rowList == null) {
            logger.error("Argument is null");
            throw new CustomException("Argument is null");
        }

        List<Integer> intermediateListOfInteger = new ArrayList<>();
        for (int i = 0; i < rowList.size(); i++) {
            if (CustomValidator.validationOfString(rowList.get(i))) {
                String[] arrayOfString = rowList.get(i).split(SEPARATOR);
                for (int j = 0; j < arrayOfString.length; j++) {
                    String arrayOfStringElement = arrayOfString[j].trim();
                    intermediateListOfInteger.add(Integer.parseInt(arrayOfStringElement));
                }
            }
        }
        int[] cleanArray = intermediateListOfInteger.stream().mapToInt(i -> i).toArray();
        return cleanArray;
    }
}
