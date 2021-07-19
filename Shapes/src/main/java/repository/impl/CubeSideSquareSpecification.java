package repository.impl;

import entity.Cube;
import entity.CubeParameter;
import entity.WareHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.CubeSpecification;

import java.util.Optional;

public class CubeSideSquareSpecification implements CubeSpecification {

    private final Logger logger = LogManager.getLogger();

    private final double minSideSquare;
    private final double maxSideSquare;

    private CubeSideSquareSpecification(double minSideSquare, double maxSideSquare) {
        this.minSideSquare = minSideSquare;
        this.maxSideSquare = maxSideSquare;
    }

    public static CubeSideSquareSpecification lessThen(double maxSideSquare) {
        return new CubeSideSquareSpecification(0, maxSideSquare);
    }

    public static CubeSideSquareSpecification moreThen(double minSideSquare) {
        return new CubeSideSquareSpecification(minSideSquare, Double.MAX_VALUE);
    }

    public static CubeSideSquareSpecification range(double minSideSquare, double maxSideSquare) {
        return new CubeSideSquareSpecification(minSideSquare, maxSideSquare);
    }

    @Override
    public boolean test(Cube cube) {
        WareHouse cubeWarehouse = WareHouse.getInstance();
        Optional<CubeParameter> cubeParameter = cubeWarehouse.get(cube.getCubeId());

        double sideSquare = cubeParameter.get().getVolume();
        return ((sideSquare >= minSideSquare) && (sideSquare <= maxSideSquare));
    }
}