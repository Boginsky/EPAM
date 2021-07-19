package parser;

import entity.CubeFields;
import exception.CubeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.CustomStringValidator;

import java.util.EnumMap;
import java.util.Map;

public class CubeParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SEPARATOR = "\s";
    private static final int REQUIRED_ARRAY_LENGTH = 4;

    public static Map<CubeFields, Double> parseFromString(String dataForParsing) throws CubeException {
        if (dataForParsing == null) {
            throw new CubeException("Data for parsing is null");
        }
        String[] stringArray = dataForParsing.split(SEPARATOR);

        Map<CubeFields, Double> cubeData = new EnumMap<CubeFields, Double>(CubeFields.class);
        if (stringArray.length != REQUIRED_ARRAY_LENGTH
                || !CustomStringValidator.isCoordinate(stringArray[0])
                || !CustomStringValidator.isCoordinate(stringArray[1])
                || !CustomStringValidator.isCoordinate(stringArray[2])
                || !CustomStringValidator.isSideLength(stringArray[3])
        ) {
            throw new CubeException("Incorrect dataForParsing");
        }

        cubeData.put(CubeFields.POINT_X, Double.parseDouble(stringArray[0]));
        cubeData.put(CubeFields.POINT_Y, Double.parseDouble(stringArray[1]));
        cubeData.put(CubeFields.POINT_Z, Double.parseDouble(stringArray[2]));
        cubeData.put(CubeFields.SIDE_LENGTH, Double.parseDouble(stringArray[3]));


        logger.log(Level.INFO,"Parsing finished");
        return cubeData;
    }
}
