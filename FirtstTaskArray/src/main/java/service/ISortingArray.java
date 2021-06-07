package service;

import entity.SelfMadeArray;
import exception.CustomException;

public interface ISortingArray {

    int[] bubbleSort(SelfMadeArray selfMadeArray) throws CustomException;

    int[] insertionSort(SelfMadeArray selfMadeArray) throws CustomException;

    int[] selectionSort(SelfMadeArray selfMadeArray) throws CustomException;
}