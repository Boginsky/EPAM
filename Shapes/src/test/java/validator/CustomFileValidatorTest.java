package validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomFileValidatorTest {
    @DataProvider(name = "dataForRowValidatorTest")
    public Object[][] dataForRowValidatorTest() {
        return new Object[][]{
                {"-4 4.0 1.4 1", true},
                {"4.0 18.5 79.2 15", true},
                {"75.8 0 15 0.5", true},
                {"-0.1 -0.1 -0.1 1.0", true},
                {"-5 7 1 1", true},
                {"-4 4.0 1.4 -1", false},
                {"-1q 4.0 1.4 -1", false},
                {"4.0,18.5,79.2,15", false},
                {"-0.w", false},
                {"a5", false}
        };
    }

    @Test(
            dataProvider = "dataForRowValidatorTest",
            groups = "validators"
    )
    public void rowValidatorTest(String stringForParsing, boolean expected) {
        boolean actual = CustomFileValidator.rowValidator(stringForParsing);

        Assert.assertEquals(actual, expected);
    }
}

