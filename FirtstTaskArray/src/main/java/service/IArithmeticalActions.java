package service;

import entity.SelfMadeArray;
import exception.CustomException;

public interface IArithmeticalActions {

    public int findMinimumArrayValue(SelfMadeArray selfMadeArray) throws CustomException;

    public int findMaximumArrayValue(SelfMadeArray selfMadeArray) throws CustomException;

    public int[] replacementOfAllNegativeNumbers(SelfMadeArray selfMadeArray) throws CustomException;

    public double findArrayAverage(SelfMadeArray selfMadeArray) throws CustomException;

    public int findSumOfArrayElements(SelfMadeArray selfMadeArray) throws CustomException;

    public int findAmountOfPositiveNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException;

    public int findAmountOfNegativeNumbersInArray(SelfMadeArray selfMadeArray) throws CustomException;

}
