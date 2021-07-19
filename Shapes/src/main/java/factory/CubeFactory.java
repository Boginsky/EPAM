package factory;

import entity.Cube;
import entity.CubeFields;
import entity.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ShapeIdGenerator;

import java.util.Map;

public class CubeFactory {
    private static final Logger logger = LogManager.getLogger();

    public static Cube getCubeElement(Map<CubeFields, Double> cubeMapData) {
        Point point = new Point(
                cubeMapData.get(CubeFields.POINT_X),
                cubeMapData.get(CubeFields.POINT_Y),
                cubeMapData.get(CubeFields.POINT_Z));
        Cube cube = new Cube(ShapeIdGenerator.generateId(), point,
                cubeMapData.get(CubeFields.SIDE_LENGTH));
        logger.info("Created Cube: " + cube + " from Map: " + cubeMapData);

        return cube;
    }

    public static Cube getCubeElement(double x, double y, double z, double sideLength) {
        Point point = new Point(x, y, z);
        Cube cube = new Cube(ShapeIdGenerator.generateId(), point, sideLength);
        logger.info("Created Cube: " + cube
                + " with data: x=" + x + ", y=" + y + ", z=" + z + ", sideLength=" + sideLength);

        return cube;
    }
}
