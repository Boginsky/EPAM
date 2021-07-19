package parser;


import java.util.EnumMap;
import java.util.Map;

import entity.CubeFields;
import exception.CubeException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataParserTest {
    @DataProvider(name = "parseCubeDataFromStringDataTest")
    public Object[][] parseCubeDataFromStringDataTest() {
        return new Object[][]{
                {"1.0 2.0 3.0 1.0", buildExpectedDataForTest(1.0, 2.0, 3.0, 1)},
                {"-1 2 1 4", buildExpectedDataForTest(-1, 2, 1, 4)},
                {"0 1 2 3", buildExpectedDataForTest(0, 1, 2, 3)},
                {"-5.2 0 14 5", buildExpectedDataForTest(-5.2, 0, 14, 5)},
                {"0 1 4 6", buildExpectedDataForTest(0, 1, 4, 6)},
        };
    }

    @Test(dataProvider = "parseCubeDataFromStringDataTest")
    public void parseCubeDataFromStringTest(String stringForParsing, Map<CubeFields, Double> expected)
            throws CubeException {
        Map<CubeFields, Double> actual = CubeParser.parseFromString(stringForParsing);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CubeException.class)
    public void parseCubeDataFromStringNullTest() throws CubeException {
        CubeParser.parseFromString(null);
    }

    @Test(expectedExceptions = CubeException.class)
    public void parseCubeDataFromStringWrongParametersTest() throws CubeException {
        CubeParser.parseFromString("-1 2 1");
    }

    @Test(expectedExceptions = CubeException.class)
    public void parseCubeDataFromStringWithValidatingWrongParametersTest() throws CubeException {
        CubeParser.parseFromString("12w 1 1 2");
    }

    private Map<CubeFields, Double> buildExpectedDataForTest(double x, double y, double z, double sideLength) {
        Map<CubeFields, Double> expectedMap = new EnumMap<>(CubeFields.class);
        expectedMap.put(CubeFields.POINT_X, x);
        expectedMap.put(CubeFields.POINT_Y, y);
        expectedMap.put(CubeFields.POINT_Z, z);
        expectedMap.put(CubeFields.SIDE_LENGTH, sideLength);

        return expectedMap;
    }
}
