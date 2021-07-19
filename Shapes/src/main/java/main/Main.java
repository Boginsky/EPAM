package main;

import entity.Cube;
import entity.CubeFields;
import exception.CubeException;
import factory.CubeFactory;
import parser.CubeParser;
import reader.CubeReader;
import registrator.CubeRegistrator;
import repository.CubeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            readAndCreateCube();

            CubeRepository repository = CubeRepository.getInstance();
            Collection<Cube> cubes = repository.getAll();
            long cId = cubes.stream().findAny().map(Cube::getCubeId).orElse((long) 0);
            Cube cube = repository.getItem(cId);
            cube.setSideLength(10);

            Cube cube1 = repository.getItem(cId);
            System.out.println(cube1);
        } catch (CubeException e) {
            e.printStackTrace();
        }
    }

    public static void readAndCreateCube() throws CubeException {
        CubeReader reader = new CubeReader();

        List<String> stringList = reader.readDataFromFile("data/data.txt");
        for (String string : stringList) {
            Map<CubeFields, Double> dataForFactory = CubeParser.parseFromString(string);
            Cube cube = CubeFactory.getCubeElement(dataForFactory);

            CubeRegistrator creator = CubeRegistrator.newCreatorFor(cube);
            creator.addToRepository().attacheObserver();
        }
    }
}
