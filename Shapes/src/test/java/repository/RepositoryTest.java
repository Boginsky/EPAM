package repository;

import comparator.CubeComparator;
import entity.Cube;
import entity.CubeParameter;
import entity.Point;
import entity.WareHouse;
import factory.CubeFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import repository.impl.CubeIdSpecification;
import repository.impl.CubeSideLengthSpecification;
import repository.impl.CubeSquareSpecification;
import repository.impl.CubeVolumeSpecification;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static repository.CubePredicateFactory.*;

public class RepositoryTest {

    private final CubeRepository repository = CubeRepository.getInstance();

    private final Cube cube1 = newCube(1.0, 2.4, 2.0, 3);
    private final Cube cube2 = newCube(-1, 2, 1, 4);
    private final Cube cube3 = newCube(0, 1, 4, 6);
    private final Cube cube4 = newCube(-5.2, 0, 14, 5);
    private final Cube cube5 = newCube(5.6, 1.7, 0, 4);
    private final Cube cube6 = newCube(12, -12, -12, 20);

    @BeforeClass
    public void setUp() {
        WareHouse warehouse = WareHouse.getInstance();
        repository.clear();
        repository.addAll(Arrays.asList(cube1, cube2, cube3, cube4, cube5, cube6));

        warehouse.put(1, new CubeParameter.Builder().setVolume(27)
                .setSideSquare(9)
                .setCubeSquare(54)
                .setOppositePoint(new Point(4, 5.4, 5))
                .build());
        warehouse.put(2, new CubeParameter.Builder().setVolume(64)
                .setSideSquare(16)
                .setCubeSquare(94)
                .setOppositePoint(new Point(3, 6, 5))
                .build());
        warehouse.put(3, new CubeParameter.Builder().setVolume(216)
                .setSideSquare(36)
                .setCubeSquare(216)
                .setOppositePoint(new Point(6, 7, 10))
                .build());
        warehouse.put(4, new CubeParameter.Builder().setVolume(125)
                .setSideSquare(25)
                .setCubeSquare(150)
                .setOppositePoint(new Point(-0.2, 5, 19))
                .build());
        warehouse.put(5, new CubeParameter.Builder().setVolume(64)
                .setSideSquare(16)
                .setCubeSquare(96)
                .setOppositePoint(new Point(9.6, 5.7, 4))
                .build());
        warehouse.put(6, new CubeParameter.Builder().setVolume(8000)
                .setSideSquare(400)
                .setCubeSquare(2400)
                .setOppositePoint(new Point(32, 8, 10))
                .build());
    }

    @DataProvider(name = "sortDataTest")
    public Object[][] sortDataTest() {
        return new Object[][]{
                {CubeComparator.ID, Arrays.asList(cube1, cube2, cube3, cube4, cube5, cube6)},
                {CubeComparator.COORDINATE_X, Arrays.asList(cube4, cube2, cube3, cube1, cube5, cube6)},
                {CubeComparator.COORDINATE_Y, Arrays.asList(cube6, cube4, cube3, cube5, cube2, cube1)},
                {CubeComparator.COORDINATE_Z, Arrays.asList(cube6, cube5, cube2, cube1, cube3, cube4)},
                {CubeComparator.SIDE_LENGTH, Arrays.asList(cube1, cube2, cube5, cube4, cube3, cube6)}
        };
    }

    @DataProvider(name = "queryDataTest")
    public Object[][] queryDataTest() {
        return new Object[][]{
                {new CubeIdSpecification(3), Arrays.asList(cube3)},
                {CubeSideLengthSpecification.lessThen(5), Arrays.asList(cube1, cube2, cube4, cube5)},
                {CubeSideLengthSpecification.moreThen(5), Arrays.asList(cube3, cube4, cube6)},
                {CubeSideLengthSpecification.range(3, 8), Arrays.asList(cube1, cube2, cube3, cube4, cube5)},
                {CubeVolumeSpecification.lessThen(30), Arrays.asList(cube1)},
                {CubeSquareSpecification.range(100, 300), Arrays.asList(cube3, cube4)},
        };
    }

    @DataProvider(name = "queryPredicateDataTest")
    public Object[][] queryPredicateDataTest() {
        return new Object[][]{
                {
                        createPredicate(
                                Cube::getCubeId,
                                valueEqualTo((long) 3)),
                        Arrays.asList(cube3)
                },
                {
                        createPredicate(
                                Cube::getSideLength,
                                valueLessThen((double) 5)),
                        Arrays.asList(cube1, cube2, cube4, cube5)
                },
                {
                        createPredicate(
                                Cube::getSideLength,
                                valueMoreThen((double) 5)),
                        Arrays.asList(cube3, cube4, cube6)
                },
                {
                        createPredicate(
                                Cube::getSideLength,
                                valueMoreThen((double) 3)
                                        .and(valueLessThen((double) 8))
                        ),
                        Arrays.asList(cube1, cube2, cube3, cube4, cube5)
                }
        };
    }

    @Test(dataProvider = "sortDataTest")
    public void sortTest(Comparator<Cube> cubeComparator, List<Cube> expected) {
        List<Cube> actual = repository.sort(cubeComparator);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "queryDataTest")
    public void findTest(CubeSpecification specification, List<Cube> expected) {
        List<Cube> actual = repository.query(specification);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "queryPredicateDataTest")
    public void findPredicateTest(Predicate<Cube> specification, List<Cube> expected) {
        List<Cube> actual = repository.query(specification);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "queryDataTest")
    public void findStreamTest(CubeSpecification specification, List<Cube> expected) {
        List<Cube> actual = repository.queryStream(specification);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "queryPredicateDataTest")
    public void findStreamPredicateTest(Predicate<Cube> specification, List<Cube> expected) {
        List<Cube> actual = repository.queryStream(specification);

        Assert.assertEquals(actual, expected);
    }

    private Cube newCube(double x, double y, double z, double sideLength) {
        return CubeFactory.getCubeElement(x, y, z, sideLength);
    }
}