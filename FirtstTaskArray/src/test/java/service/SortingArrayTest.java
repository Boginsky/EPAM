package service;

import entity.SelfMadeArray;
import exception.CustomException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.impl.SortingArray;

import static org.testng.Assert.assertEquals;

public class SortingArrayTest {

    SelfMadeArray selfMadeArrayActual;
    SortingArray sortingArray;
    int [] arrayExpected = {-1, 4, 5, 6, 7, 8, 9, 30, 30};

    @BeforeClass
    public void setUp() {
        int[] arrayForTest = {4, 5, 6, 7, 8, 9, 30, 30, -1};
        sortingArray = new SortingArray();
        selfMadeArrayActual = new SelfMadeArray(arrayForTest);
    }

    @Test
    public void testBubbleSort() throws CustomException {
        int [] arrayForTestActual = sortingArray.bubbleSort(selfMadeArrayActual);
        assertEquals(arrayForTestActual,arrayExpected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testInsertionSort() throws CustomException{
        int [] arrayForTestActual = sortingArray.insertionSort(selfMadeArrayActual);
        assertEquals(arrayForTestActual,arrayExpected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testSelectionSort() throws CustomException{
        int [] arrayForTestActual = sortingArray.selectionSort(selfMadeArrayActual);
        assertEquals(arrayForTestActual,arrayExpected, "Test failed because method isn't working correctly");
    }

    @AfterClass
    public void tierDown(){
        selfMadeArrayActual = null;
        sortingArray = null;
    }
}
