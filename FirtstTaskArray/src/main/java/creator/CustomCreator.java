package creator;

import entity.SelfMadeArray;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomCreator {

    static Logger logger = LogManager.getLogger();

    public SelfMadeArray creatorOfArray(int[] cleanArray) throws CustomException {
        if (cleanArray == null) {
            logger.error("Argument is null");
            throw new CustomException("Argument is null");
        }
        SelfMadeArray selfMadeArray = new SelfMadeArray(cleanArray);
        return selfMadeArray;
    }
}
