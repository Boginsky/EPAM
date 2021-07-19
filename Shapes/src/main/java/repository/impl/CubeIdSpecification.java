package repository.impl;

import entity.Cube;
import repository.CubeSpecification;

public class CubeIdSpecification implements CubeSpecification {

    private final long id;

    public CubeIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean test(Cube cube) {
        return (cube.getCubeId() == this.id);
    }
}
