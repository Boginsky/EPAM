package service;

import entity.SelfMadeArray;

public interface ISortingArray {

    int[] bubbleSort(SelfMadeArray selfMadeArray);

    int[] insertionSort(SelfMadeArray selfMadeArray);

    int[] selectionSort(SelfMadeArray selfMadeArray);
}