package comparator;

import entity.Cube;

import java.util.Comparator;

public class CubeComparator {
    public static Comparator<Cube> ID = Comparator.comparing(Cube::getCubeId);
    public static Comparator<Cube> COORDINATE_X = Comparator.comparing(
            cube -> cube.getPoint().getX());
    public static Comparator<Cube> COORDINATE_Y = Comparator.comparing(
            cube -> cube.getPoint().getY());
    public static Comparator<Cube> COORDINATE_Z = Comparator.comparing(
            cube -> cube.getPoint().getZ());
    public static Comparator<Cube> SIDE_LENGTH = Comparator.comparing(Cube::getSideLength);
}

