package action;

import entity.Cube;
import entity.CubeParameter;
import entity.Point;
import entity.WareHouse;

import java.util.Optional;

public class CubeAnalysis {

        public static boolean isCube(Cube o) {
            return o.getSideLength() > 0;
        }

        public static boolean onCoordinateAxis(Cube cube) {
            return onCoordinateAxeX(cube) || onCoordinateAxeY(cube) || onCoordinateAxeZ(cube);
        }

        public static boolean onCoordinateAxeX(Cube cube) {
            Point point = cube.getPoint();
            return point.getX() == 0;
        }

        public static boolean onCoordinateAxeY(Cube cube) {
            Point point = cube.getPoint();
            return point.getY() == 0;
        }

        public static boolean onCoordinateAxeZ(Cube cube) {
            Point point = cube.getPoint();
            return point.getZ() == 0;
        }

        public static boolean isCubeCuttedByAxes(Cube cube) {
            return (isCuttedByCoordinateAxeX(cube) || isCuttedByCoordinateAxeY(cube)
                    || isCuttedByCoordinateAxeY(cube));
        }

        public static boolean isCuttedByCoordinateAxeX(Cube cube) {
            Optional<CubeParameter> cubeParameter = WareHouse.getInstance().get(cube.getCubeId());

            Point basePoint = cube.getPoint();
            Point oppositePoint = cubeParameter.get().getOppositePoint();

            return (basePoint.getX() < 0 && oppositePoint.getX() > 0);
        }

        public static boolean isCuttedByCoordinateAxeY(Cube cube) {
            Optional<CubeParameter> cubeParameter = WareHouse.getInstance().get(cube.getCubeId());

            Point basePoint = cube.getPoint();
            Point oppositePoint = cubeParameter.get().getOppositePoint();

            return (basePoint.getY() < 0 && oppositePoint.getY() > 0);
        }

        public static boolean isCuttedByCoordinateAxeZ(Cube cube) {
            Optional<CubeParameter> cubeParameter = WareHouse.getInstance().get(cube.getCubeId());

            Point basePoint = cube.getPoint();
            Point oppositePoint = cubeParameter.get().getOppositePoint();

            return (basePoint.getZ() < 0 && oppositePoint.getZ() > 0);
        }
    }
