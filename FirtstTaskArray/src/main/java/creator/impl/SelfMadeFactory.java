package creator.impl;

import creator.ISelfMadeFactory;
import entity.SelfMadeArray;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelfMadeFactory implements ISelfMadeFactory {

    static Logger logger = LogManager.getLogger();
    private static ISelfMadeFactory factory;

    private SelfMadeFactory() {
    }

    public static ISelfMadeFactory getInstance() {
        if (factory == null) {
            factory = new SelfMadeFactory();
        }
        return factory;
    }

    @Override
    public SelfMadeArray getSelfMadeArray(int[] cleanArray) throws CustomException {
        SelfMadeArray selfMadeArray;
        if (cleanArray == null) {
            logger.error("Argument is null");
            throw new CustomException ("Argument is null");
        } else {
            selfMadeArray = new SelfMadeArray(cleanArray);
            return selfMadeArray;
        }
    }
}
