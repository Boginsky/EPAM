package action;

import comparator.CubeComparator;
import entity.Cube;
import repository.CubeRepository;

import java.util.List;

public class CubeSort {

    private final CubeRepository repository = CubeRepository.getInstance();

    public List<Cube> sortById() {
        return repository.sort(CubeComparator.ID);
    }

    public List<Cube> sortByCoordinateX() {
        return repository.sort(CubeComparator.COORDINATE_X);
    }

    public List<Cube> sortByCoordinateY() {
        return repository.sort(CubeComparator.COORDINATE_Y);
    }

    public List<Cube> sortByCoordinateZ() {
        return repository.sort(CubeComparator.COORDINATE_Z);
    }

    public List<Cube> sortBySideLength() {
        return repository.sort(CubeComparator.SIDE_LENGTH);
    }
}