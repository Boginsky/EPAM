package service;

import entity.SelfMadeArray;
import exception.CustomException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.impl.ArithmeticalActionsByIntStream;

import static org.testng.Assert.assertEquals;

public class ArithmeticalActionsByStreamTest {

    ArithmeticalActionsByIntStream arithmeticalActionsByIntStream;
    SelfMadeArray selfMadeArray;

    @BeforeClass
    public void setUp() {
        arithmeticalActionsByIntStream = new ArithmeticalActionsByIntStream();
        int[] arrayForTest = {4, 5, 6, 7, -1};
        selfMadeArray = new SelfMadeArray(arrayForTest);
    }

    @Test
    public void testFindMinimumArrayValue() throws CustomException {
        int actual = arithmeticalActionsByIntStream.findMinimumArrayValue(selfMadeArray);
        int expected = -1;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindMaximumArrayValue() throws CustomException {
        int actual = arithmeticalActionsByIntStream.findMaximumArrayValue(selfMadeArray);
        int expected = 7;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }


    @Test
    public void testFindArrayAverage() throws CustomException {
        double actual = arithmeticalActionsByIntStream.findArrayAverage(selfMadeArray);
        double expected = 4.2;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindSumOfArrayElements() throws CustomException {
        int actual = arithmeticalActionsByIntStream.findSumOfArrayElements(selfMadeArray);
        int expected = 21;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindAmountOfPositiveNumbersInArray() throws CustomException {
        int actual = arithmeticalActionsByIntStream.findAmountOfPositiveNumbersInArray(selfMadeArray);
        int expected = 4;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindAmountOfNegativeNumbersInArray() throws CustomException {
        int actual = arithmeticalActionsByIntStream.findAmountOfNegativeNumbersInArray(selfMadeArray);
        int expected = 1;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @AfterClass
    public void tierDown() {
        arithmeticalActionsByIntStream = null;
        selfMadeArray = null;
    }
}
