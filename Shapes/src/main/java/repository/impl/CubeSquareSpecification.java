package repository.impl;

import entity.Cube;
import entity.CubeParameter;
import entity.WareHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.CubeSpecification;

import java.util.Optional;

public class CubeSquareSpecification implements CubeSpecification {

    private final Logger logger = LogManager.getLogger();

    private final double minCubeSquare;
    private final double maxCubeSquare;

    private CubeSquareSpecification(double minCubeSquare, double maxCubeSquare) {
        this.minCubeSquare = minCubeSquare;
        this.maxCubeSquare = maxCubeSquare;
    }

    public static CubeSquareSpecification lessThen(double maxCubeSquare) {
        return new CubeSquareSpecification(0, maxCubeSquare);
    }

    public static CubeSquareSpecification moreThen(double minCubeSquare) {
        return new CubeSquareSpecification(minCubeSquare, Double.MAX_VALUE);
    }

    public static CubeSquareSpecification range(double minCubeSquare, double maxCubeSquare) {
        return new CubeSquareSpecification(minCubeSquare, maxCubeSquare);
    }

    @Override
    public boolean test(Cube cube) {
        WareHouse cubeWarehouse = WareHouse.getInstance();
        Optional<CubeParameter> cubeParameter = cubeWarehouse.get(cube.getCubeId());

        double cubeSquare = cubeParameter.get().getVolume();
        return ((cubeSquare >= minCubeSquare) && (cubeSquare <= maxCubeSquare));
    }
}
