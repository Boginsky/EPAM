package action;

import entity.Cube;
import factory.CubeFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CubeAnalysisTest {
    @DataProvider(name = "onCoordinateAxisDataTest")
    public Object[][] onCoordinateAxisDataTest() {
        return new Object[][]{
                {CubeFactory.getCubeElement(0, 1, 1, 2), true},
                {CubeFactory.getCubeElement(1, 0, 1, 3), true},
                {CubeFactory.getCubeElement(1, 1, 0, 4), true},
                {CubeFactory.getCubeElement(1, 1, 1, 1.8), false}
        };
    }

    @Test(dataProvider = "onCoordinateAxisDataTest")
    public void onCoordinateAxisTest(Cube cube, boolean expected) {
        boolean actual = CubeAnalysis.onCoordinateAxis(cube);

        Assert.assertEquals(actual, expected);
    }
}
