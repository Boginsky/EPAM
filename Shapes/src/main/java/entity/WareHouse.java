package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WareHouse {

    private Map<Long, CubeParameter> map = new HashMap<>();

    private WareHouse() {
    }

    private static class SingletonHolder {
        private static final WareHouse INSTANCE = new WareHouse();
    }

    public static WareHouse getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public CubeParameter put(long id, CubeParameter parameter) {
        return map.put(id, parameter);
    }

    public Optional<CubeParameter> remove(long id) {
        CubeParameter cubeParameter = map.remove(id);
        return (cubeParameter != null ? Optional.of(cubeParameter) : Optional.empty());
    }

    public Optional<CubeParameter> get(long id) {
        CubeParameter cubeParameter = map.get(id);
        return (cubeParameter != null ? Optional.of(cubeParameter) : Optional.empty());
    }


}
