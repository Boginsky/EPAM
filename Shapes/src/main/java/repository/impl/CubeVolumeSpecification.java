package repository.impl;

import entity.Cube;
import entity.CubeParameter;
import entity.WareHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.CubeSpecification;

import java.util.Optional;

public class CubeVolumeSpecification implements CubeSpecification {
    private final Logger logger = LogManager.getLogger();

    private final double minVolume;
    private final double maxVolume;

    private CubeVolumeSpecification(double minVolume, double maxVolume) {
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
    }

    public static CubeVolumeSpecification lessThen(double maxVolume) {
        return new CubeVolumeSpecification(0, maxVolume);
    }

    public static CubeVolumeSpecification moreThen(double minVolume) {
        return new CubeVolumeSpecification(minVolume, Double.MAX_VALUE);
    }

    public static CubeVolumeSpecification range(double minVolume, double maxVolume) {
        return new CubeVolumeSpecification(minVolume, maxVolume);
    }

    @Override
    public boolean test(Cube cube) {
        WareHouse cubeWarehouse = WareHouse.getInstance();
        Optional<CubeParameter> cubeParameter = cubeWarehouse.get(cube.getCubeId());

        double volume = cubeParameter.get().getVolume();
        return ((volume >= minVolume) && (volume <= maxVolume));
    }
}
