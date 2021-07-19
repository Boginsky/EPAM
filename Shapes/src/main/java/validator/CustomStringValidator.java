package validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomStringValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String REG_EXP_WRONG_STRING = "[^\\d\\s\\.]+";
    private static final String COORDINATE_REGEXP = "^(-?\\d+(.\\d+)?)$";
    private static final String SIDE_LENGTH_REGEXP = "^(\\d+(.\\d+)?)$";

    public static boolean isStringValid(String inputString){
        if(inputString == null || inputString.length() == 0){
            logger.log(Level.DEBUG, "Input string is null or empty");
            return false;
        }
        Pattern pattern = Pattern.compile(REG_EXP_WRONG_STRING);
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()){
            logger.log(Level.INFO,"Invalid string");
            return false;
        }
        logger.log(Level.INFO,"Valid string");
        return true;
    }
    public static boolean isCoordinate(String stringForCheck){
        return stringForCheck.matches(COORDINATE_REGEXP);
    }
    public static boolean isSideLength(String stringForCheck) {
        return stringForCheck.matches(SIDE_LENGTH_REGEXP);
    }
}
