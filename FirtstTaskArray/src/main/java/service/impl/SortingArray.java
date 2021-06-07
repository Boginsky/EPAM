package service.impl;

import entity.SelfMadeArray;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ISortingArray;

import java.util.Arrays;

public class SortingArray implements ISortingArray {

    static Logger logger = LogManager.getLogger();

    @Override
    public int[] bubbleSort(SelfMadeArray selfMadeArray) throws CustomException {
        if (selfMadeArray == null) {
            throw new CustomException("Array is null");
        }
        int[] localArray = selfMadeArray.getArray();
        boolean arraySorted = false;
        int temp;
        while (!arraySorted) {
            arraySorted = true;
            for (int i = 0; i < localArray.length - 1; i++) {
                if (localArray[i] > localArray[i + 1]) {
                    temp = localArray[i];
                    localArray[i] = localArray[i + 1];
                    localArray[i + 1] = temp;
                    arraySorted = false;
                }
            }
        }
        logger.debug("Array sorted by bubbleSort: " + Arrays.toString(localArray));
        return localArray;
    }

    @Override
    public int[] insertionSort(SelfMadeArray selfMadeArray) throws CustomException {
        if (selfMadeArray == null) {
            throw new CustomException("Array is null");
        }
        int[] localArray = selfMadeArray.getArray();
        for (int i = 0; i < localArray.length; i++) {
            int current = localArray[i];
            int j = i - 1;
            while (j >= 0 && current < localArray[j]) {
                localArray[j + 1] = localArray[j];
                j--;
            }
            localArray[j + 1] = current;
        }
        logger.debug("Array sorted by insertionSort: " + Arrays.toString(localArray));
        return localArray;
    }

    @Override
    public int[] selectionSort(SelfMadeArray selfMadeArray) throws CustomException {
        if (selfMadeArray == null) {
            throw new CustomException("Array is null");
        }
        int[] localArray = selfMadeArray.getArray();
        for (int i = 0; i < localArray.length; i++) {
            int pos = i;
            int min = localArray[i];
            for (int j = i + 1; j < localArray.length; j++) {
                if (localArray[j] < min) {
                    pos = j;
                    min = localArray[j];
                }
            }
            localArray[pos] = localArray[i];
            localArray[i] = min;
        }
        logger.debug("Array sorted by selectionSort:" + Arrays.toString(localArray));
        return localArray;
    }
}
