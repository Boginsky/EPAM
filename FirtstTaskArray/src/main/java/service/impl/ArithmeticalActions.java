package service.impl;

import entity.SelfMadeArray;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IArithmeticalActions;

import java.util.Arrays;

public class ArithmeticalActions implements IArithmeticalActions {

    StringBuilder stringBuilder = new StringBuilder();
    static Logger logger = LogManager.getLogger();

    public int findMinimumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        int min = selfMadeArray.getElementByPositionInArray(0);
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (min > selfMadeArray.getElementByPositionInArray(i)) {
                min = selfMadeArray.getElementByPositionInArray(i);
            }
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Minimum array value: ").append(min));
        return min;
    }

    public int findMaximumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        int max = selfMadeArray.getElementByPositionInArray(0);
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (max < selfMadeArray.getElementByPositionInArray(i)) {
                max = selfMadeArray.getElementByPositionInArray(i);
            }
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Maximum array value: ").append(max));
        return max;
    }

    public int[] replacementOfAllNegativeNumbers(SelfMadeArray selfMadeArray) throws CustomException {
        int [] localArray = selfMadeArray.getArray();
        for (int i = 0; i < localArray.length; i++) {
            if (localArray[i] < 0) {
                localArray[i] = 0;
            }
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Array after replacement: ").append(Arrays.toString(localArray)));
        return localArray;
    }

    public double findArrayAverage(SelfMadeArray selfMadeArray) throws CustomException {
        double average = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            average += selfMadeArray.getElementByPositionInArray(i);
        }
        average /= selfMadeArray.getSize();
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Average: ").append(average));
        return average;
    }

    public int findSumOfArrayElements(SelfMadeArray selfMadeArray) throws CustomException {
        int sum = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            sum += selfMadeArray.getElementByPositionInArray(i);
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Sum: ").append(sum));
        return sum;
    }

    public int findAmountOfPositiveNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        int amountOfPositiveNumbersInArray = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (selfMadeArray.getElementByPositionInArray(i) > 0) {
                amountOfPositiveNumbersInArray++;
            }
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Amount of positive numbers: ").append(amountOfPositiveNumbersInArray));
        return amountOfPositiveNumbersInArray;
    }

    public int findAmountOfNegativeNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        int amountOfNegativeNumbersInArray = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (selfMadeArray.getElementByPositionInArray(i) < 0) {
                amountOfNegativeNumbersInArray++;
            }
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Amount of negative numbers: ").append(amountOfNegativeNumbersInArray));
        return amountOfNegativeNumbersInArray;
    }
}
