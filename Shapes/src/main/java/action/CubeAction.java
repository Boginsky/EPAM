package action;

import entity.Cube;
import entity.CubeParameter;
import entity.Point;
import entity.WareHouse;
import exception.CubeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CubeAction {
    private static final Logger logger = LogManager.getLogger();

    public static double calculateCubeVolume(Cube cube) {
        double volume = Math.pow(cube.getSideLength(), 3);
        logger.info("Volume for Cube(" + cube + ") is " + volume);

        return volume;
    }

    public static double calculateCubeSideSquare(Cube cube) {
        double sideLength = cube.getSideLength();
        double sideSquare = sideLength * sideLength;
        logger.info("Side square for Cube(" + cube + ") is " + sideSquare);

        return sideSquare;
    }

    public static double calculateCubeSquare(Cube cube) {
        double sideSquare = calculateCubeSideSquare(cube);
        double cubeSquare = 6 * sideSquare;
        logger.info("Cube square for Cube(" + cube + ") is " + cubeSquare);

        return cubeSquare;
    }

    public static Point calculateOppositePoint(Cube cube) {
        Point basePoint = cube.getPoint();
        double sideLength = cube.getSideLength();

        double new_x = basePoint.getX() + sideLength;
        double new_y = basePoint.getY() + sideLength;
        double new_z = basePoint.getZ() + sideLength;

        return new Point(new_x, new_y, new_z);
    }

    public static double calculateRatioOfCuboidAfterCutByAxel(Cube cube) throws CubeException {
        Optional<CubeParameter> cubeParameter = WareHouse.getInstance().get(cube.getCubeId());
        Point basePoint = cube.getPoint();
        Point oppositePoint = cubeParameter.get().getOppositePoint();

        double cuttedSideLengthX1, cuttedSideLengthX2,
                cuttedSideLengthY1, cuttedSideLengthY2,
                cuttedSideLengthZ1, cuttedSideLengthZ2;

        if (CubeAnalysis.isCuttedByCoordinateAxeX(cube)) {
            cuttedSideLengthX1 = Math.abs(basePoint.getX());
            cuttedSideLengthX2 = Math.abs(oppositePoint.getX());
        } else {
            cuttedSideLengthX1 = cube.getSideLength();
            cuttedSideLengthX2 = cube.getSideLength();
        }

        if (CubeAnalysis.isCuttedByCoordinateAxeY(cube)) {
            cuttedSideLengthY1 = Math.abs(basePoint.getY());
            cuttedSideLengthY2 = Math.abs(oppositePoint.getY());
        } else {
            cuttedSideLengthY1 = cube.getSideLength();
            cuttedSideLengthY2 = cube.getSideLength();
        }

        if (CubeAnalysis.isCuttedByCoordinateAxeZ(cube)) {
            cuttedSideLengthZ1 = Math.abs(basePoint.getZ());
            cuttedSideLengthZ2 = Math.abs(oppositePoint.getZ());
        } else {
            cuttedSideLengthZ1 = cube.getSideLength();
            cuttedSideLengthZ2 = cube.getSideLength();
        }

        double volumeForCuboid1 = calculateCuboidVolume(cuttedSideLengthX1, cuttedSideLengthY1,
                cuttedSideLengthZ1);
        double volumeForCuboid2 = calculateCuboidVolume(cuttedSideLengthX2, cuttedSideLengthY2,
                cuttedSideLengthZ2);

        return volumeForCuboid1 / volumeForCuboid2;
    }

    public static double calculateCuboidVolume(double lengthByX, double lengthByY, double lengthByZ) {
        return lengthByX * lengthByY * lengthByZ;
    }

    public static CubeParameter calculateParameters(Cube cube) {
        double cubeVolume = CubeAction.calculateCubeVolume(cube);
        double cubeSideSquare = CubeAction.calculateCubeSideSquare(cube);
        double cubeSquare = CubeAction.calculateCubeSquare(cube);
        Point oppositePoint = CubeAction.calculateOppositePoint(cube);

        CubeParameter.Builder builder = new CubeParameter.Builder();
        builder.setVolume(cubeVolume)
                .setSideSquare(cubeSideSquare)
                .setCubeSquare(cubeSquare)
                .setOppositePoint(oppositePoint);
        return builder.build();
    }
}
