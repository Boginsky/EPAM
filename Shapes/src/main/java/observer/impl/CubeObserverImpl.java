package observer.impl;

import action.CubeAction;
import entity.Cube;
import entity.CubeParameter;
import entity.WareHouse;
import observer.CubeEvent;
import observer.CubeObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CubeObserverImpl implements CubeObserver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void updateParameters(CubeEvent cubeEvent) {
        Cube cube = cubeEvent.getSource();
        WareHouse wareHouse = WareHouse.getInstance();
        Optional<CubeParameter> oldProperties = wareHouse.get(cube.getCubeId());
        CubeParameter newProperties = CubeAction.calculateParameters(cube);
        wareHouse.put(cube.getCubeId(), newProperties);
    }
}
