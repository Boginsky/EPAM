package action;


import entity.Cube;
import factory.CubeFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CubeActionTest {

    @DataProvider(name = "calculateVolumeDataTest")
    public Object[][] calculateVolumeDataTest() {
        return new Object[][]{
                {CubeFactory.getCubeElement(1, 1, 1, 2), 8},
                {CubeFactory.getCubeElement(1, 1, 1, 3), 27},
                {CubeFactory.getCubeElement(1, 1, 1, 4), 64},
                {CubeFactory.getCubeElement(1, 1, 1, 1.8), 5.832}
        };
    }

    @DataProvider(name = "calculateSideSquareDataTest")
    public Object[][] calculateSideSquareDataTest() {
        return new Object[][]{
                {CubeFactory.getCubeElement(1, 1, 1, 2), 4},
                {CubeFactory.getCubeElement(1, 1, 1, 3), 9},
                {CubeFactory.getCubeElement(1, 1, 1, 4), 16},
                {CubeFactory.getCubeElement(1, 1, 1, 1.8), 3.24}
        };
    }

    @DataProvider(name = "calculateCubeSquareDataTest")
    public Object[][] calculateCubeSquareDataTest() {
        return new Object[][]{
                {CubeFactory.getCubeElement(1, 1, 1, 2), 24},
                {CubeFactory.getCubeElement(1, 1, 1, 3), 54},
                {CubeFactory.getCubeElement(1, 1, 1, 4), 96},
                {CubeFactory.getCubeElement(1, 1, 1, 1.8), 19.44}
        };
    }

    @Test(dataProvider = "calculateVolumeDataTest")
    public void calculateVolumeTest(Cube cube, double expected) {
        double actual = CubeAction.calculateCubeVolume(cube);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test(dataProvider = "calculateSideSquareDataTest")
    public void calculateSideSquareTest(Cube cube, double expected) {
        double actual = CubeAction.calculateCubeSideSquare(cube);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test(dataProvider = "calculateCubeSquareDataTest")
    public void calculateCubeSquareTest(Cube cube, double expected) {
        double actual = CubeAction.calculateCubeSquare(cube);

        Assert.assertEquals(actual, expected, 0.0001);
    }
}
