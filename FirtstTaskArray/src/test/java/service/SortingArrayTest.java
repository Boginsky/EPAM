package service;

import entity.SelfMadeArray;
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
    public void testBubbleSort() {
        assertEquals(sortingArray.bubbleSort(selfMadeArrayActual),arrayExpected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testInsertionSort(){
        assertEquals(sortingArray.insertionSort(selfMadeArrayActual),arrayExpected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testSelectionSort(){
        assertEquals(sortingArray.selectionSort(selfMadeArrayActual),arrayExpected, "Test failed because method isn't working correctly");
    }

    @AfterClass
    public void tierDown(){
        selfMadeArrayActual = null;
        sortingArray = null;
    }
}
