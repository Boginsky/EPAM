package service.impl;

import entity.SelfMadeArray;
import exception.CustomException;
import service.IArithmeticalActions;

import java.util.Arrays;


public class ArithmeticalActionsByIntStream implements IArithmeticalActions {

    public int findMinimumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        return Arrays.stream(selfMadeArray.getArray())
                .min()
                .orElseThrow(CustomException::new);
    }

    public int findMaximumArrayValue(SelfMadeArray selfMadeArray) throws CustomException {
        return Arrays.stream(selfMadeArray.getArray())
                .max()
                .orElseThrow(CustomException::new);
    }

    public double findArrayAverage(SelfMadeArray selfMadeArray) throws CustomException {
        return Arrays.stream(selfMadeArray.getArray())
                .average()
                .orElseThrow(CustomException::new);
    }

    public int findSumOfArrayElements(SelfMadeArray selfMadeArray) throws CustomException {
        return Arrays.stream(selfMadeArray.getArray())
                .sum();
    }

    public int findAmountOfPositiveNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        return (int) Arrays.stream(selfMadeArray.getArray())
                .filter(i -> i > 0)
                .count();
    }

    public int findAmountOfNegativeNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException {
        return (int) Arrays.stream(selfMadeArray.getArray())
                .filter(i -> i < 0)
                .count();
    }
}
