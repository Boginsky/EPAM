package validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomDataValidatorTest {

    @DataProvider(name = "dataForCoordinateValidatorTest")
    public Object[][] dataForCoordinateValidatorTest() {
        return new Object[][]{
                {"-4", true},
                {"4.0", true},
                {"75.8", true},
                {"-0.1", true},
                {"-5", true},
                {"-4z", false},
                {"asa", false},
                {"VI", false},
                {"-0.w", false},
                {"a5", false}
        };
    }

    @DataProvider(name = "dataForSideLengthValidatorTest")
    public Object[][] dataForSideLengthValidatorTest() {
        return new Object[][]{
                {"4", true},
                {"4.0", true},
                {"75.8", true},
                {"0.1", true},
                {"5", true},
                {"-4", false},
                {"-0.1", false},
                {"VI", false},
                {"-0.w", false},
                {"a5", false}
        };
    }

    @Test(
            dataProvider = "dataForCoordinateValidatorTest",
            groups = "validators"
    )
    public void isCoordinateTest(String stringForParsing, boolean expected) {
        boolean actual = CustomStringValidator.isCoordinate(stringForParsing);

        Assert.assertEquals(actual, expected);
    }

    @Test(
            dataProvider = "dataForSideLengthValidatorTest",
            groups = "validators"
    )
    public void isSideLengthTest(String stringForParsing, boolean expected) {
        boolean actual = CustomStringValidator.isSideLength(stringForParsing);

        Assert.assertEquals(actual, expected);
    }
}
