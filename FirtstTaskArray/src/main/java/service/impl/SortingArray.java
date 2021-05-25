package service.impl;

import entity.SelfMadeArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ISortingArray;

import java.util.Arrays;

public class SortingArray implements ISortingArray {

    StringBuilder stringBuilder = new StringBuilder();
    static Logger logger = LogManager.getLogger();

    @Override
    public int[] bubbleSort(SelfMadeArray selfMadeArray) {
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
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Array sorted by bubbleSort: ").append(Arrays.toString(localArray)));
        return localArray;
    }

    @Override
    public int[] insertionSort(SelfMadeArray selfMadeArray) {
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
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Array sorted by insertionSort: ").append(Arrays.toString(localArray)));
        return localArray;
    }

    @Override
    public int[] selectionSort(SelfMadeArray selfMadeArray) {
        int[] localArray = selfMadeArray.getArray();
        for (int i = 0; i <localArray.length; i++) {
            int pos = i;
            int min = localArray[i];
            for (int j = i+1; j < localArray.length; j++) {
                if(localArray[j]<min){
                    pos = j;
                    min = localArray[j];
                }
            }
            localArray[pos] = localArray[i];
            localArray[i] = min;
        }
        stringBuilder.setLength(0);
        logger.debug(stringBuilder.append("Array sorted by selectionSort:").append(Arrays.toString(localArray)));
        return localArray;
    }
}
