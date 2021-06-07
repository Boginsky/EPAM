package service.impl;

import entity.SelfMadeArray;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IArithmeticalActions;

import java.util.Arrays;

public class ArithmeticalActions implements IArithmeticalActions {

    static Logger logger = LogManager.getLogger();

    public int findMinimumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        int min = selfMadeArray.getElementByPositionInArray(0);
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (min > selfMadeArray.getElementByPositionInArray(i)) {
                min = selfMadeArray.getElementByPositionInArray(i);
            }
        }
        logger.debug("Minimum array value: " + min);
        return min;
    }

    public int findMaximumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        int max = selfMadeArray.getElementByPositionInArray(0);
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (max < selfMadeArray.getElementByPositionInArray(i)) {
                max = selfMadeArray.getElementByPositionInArray(i);
            }
        }
        logger.debug("Maximum array value: " + max);
        return max;
    }

    public int[] replacementOfAllNegativeNumbers(SelfMadeArray selfMadeArray) throws CustomException {
        int[] localArray = selfMadeArray.getArray();
        for (int i = 0; i < localArray.length; i++) {
            if (localArray[i] < 0) {
                localArray[i] = 0;
            }
        }
        logger.debug("Array after replacement: " + Arrays.toString(localArray));
        return localArray;
    }

    public double findArrayAverage(SelfMadeArray selfMadeArray) throws CustomException {
        double average = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            average += selfMadeArray.getElementByPositionInArray(i);
        }
        average /= selfMadeArray.getSize();
        logger.debug("Average: " + average);
        return average;
    }

    public int findSumOfArrayElements(SelfMadeArray selfMadeArray) throws CustomException {
        int sum = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            sum += selfMadeArray.getElementByPositionInArray(i);
        }
        logger.debug("Sum: " + sum);
        return sum;
    }

    public int findAmountOfPositiveNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        int amountOfPositiveNumbersInArray = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (selfMadeArray.getElementByPositionInArray(i) > 0) {
                amountOfPositiveNumbersInArray++;
            }
        }
        logger.debug("Amount of positive numbers: " + amountOfPositiveNumbersInArray);
        return amountOfPositiveNumbersInArray;
    }

    public int findAmountOfNegativeNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        int amountOfNegativeNumbersInArray = 0;
        for (int i = 0; i < selfMadeArray.getSize(); i++) {
            if (selfMadeArray.getElementByPositionInArray(i) < 0) {
                amountOfNegativeNumbersInArray++;
            }
        }
        logger.debug("Amount of negative numbers: " + amountOfNegativeNumbersInArray);
        return amountOfNegativeNumbersInArray;
    }
}
