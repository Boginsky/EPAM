package service;

import entity.SelfMadeArray;
import exception.CustomException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.impl.ArithmeticalActions;

import static org.testng.Assert.assertEquals;

public class ArithmeticalActionsTest {
    ArithmeticalActions arithmeticalActions;
    SelfMadeArray selfMadeArray;

    @BeforeClass
    public void setUp() {
        arithmeticalActions = new ArithmeticalActions();
        int[] arrayForTest = {4, 5, 6, 7, -1};
        selfMadeArray = new SelfMadeArray(arrayForTest);
    }

    @Test
    public void testFindMinimumArrayValue() throws CustomException {
        int actual = arithmeticalActions.findMinimumArrayValue(selfMadeArray);
        int expected = -1;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindMaximumArrayValue() throws CustomException {
        int actual = arithmeticalActions.findMaximumArrayValue(selfMadeArray);
        int expected = 7;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testReplacementOfAllNegativeNumbers() throws CustomException {
        int actual = arithmeticalActions.replacementOfAllNegativeNumbers(selfMadeArray)[4];
        int expected = 0;
        assertEquals(actual,expected, "Test failed because method isn't working correctly");

    }

    @Test
    public void testFindArrayAverage() throws CustomException {
        double actual = arithmeticalActions.findArrayAverage(selfMadeArray);
        double expected = 4.2;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindSumOfArrayElements() throws CustomException {
        int actual = arithmeticalActions.findSumOfArrayElements(selfMadeArray);
        int expected = 21;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindAmountOfPositiveNumbersInArray() throws CustomException {
        int actual = arithmeticalActions.findAmountOfPositiveNumbersInArray(selfMadeArray);
        int expected = 4;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @Test
    public void testFindAmountOfNegativeNumbersInArray() throws CustomException {
        int actual = arithmeticalActions.findAmountOfNegativeNumbersInArray(selfMadeArray);
        int expected = 1;
        assertEquals(actual, expected, "Test failed because method isn't working correctly");
    }

    @AfterClass
    public void tierDown() {
        arithmeticalActions = null;
        selfMadeArray = null;
    }
}
