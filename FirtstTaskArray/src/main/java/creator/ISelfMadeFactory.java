package creator;

import entity.SelfMadeArray;
import exception.CustomException;

public interface ISelfMadeFactory {

    SelfMadeArray getSelfMadeArray(int[] cleanArray) throws CustomException;
}
