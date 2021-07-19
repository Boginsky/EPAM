package registrator;

import action.CubeAction;
import entity.Cube;
import entity.WareHouse;
import observer.impl.CubeObserverImpl;
import repository.CubeRepository;

public class CubeRegistrator {
    private final Cube currentElement;

    private CubeRegistrator(Cube cube) {
        this.currentElement = cube;
    }

    public static CubeRegistrator newCreatorFor(Cube cube) {
        return new CubeRegistrator(cube);
    }

    public CubeRegistrator addToRepository() {
        CubeRepository repository = CubeRepository.getInstance();
        repository.add(currentElement);

        WareHouse cubeWarehouse = WareHouse.getInstance();
        cubeWarehouse.put(currentElement.getCubeId(), CubeAction.calculateParameters(currentElement));

        return this;
    }

    public CubeRegistrator attacheObserver() {
        CubeObserverImpl cubeObserver = new CubeObserverImpl();

        currentElement.attach(cubeObserver);
        return this;
    }
}
