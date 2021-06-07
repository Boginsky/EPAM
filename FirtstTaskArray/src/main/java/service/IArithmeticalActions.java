package service;

import entity.SelfMadeArray;
import exception.CustomException;

public interface IArithmeticalActions {

    int findMinimumArrayValue(SelfMadeArray selfMadeArray) throws CustomException;

    int findMaximumArrayValue(SelfMadeArray selfMadeArray) throws CustomException;

    double findArrayAverage(SelfMadeArray selfMadeArray) throws CustomException;

    int findSumOfArrayElements(SelfMadeArray selfMadeArray) throws CustomException;

    int findAmountOfPositiveNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException;

    int findAmountOfNegativeNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException;

}
