package repository;

import entity.Cube;
import entity.CubeParameter;
import entity.WareHouse;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class CubePredicateFactory {
    // Primitive predicates
    public static <T extends Comparable<T>> Predicate<T> valueEqualTo(T equalValue) {
        return value -> value.compareTo(equalValue) == 0;
    }

    public static <T extends Comparable<T>> Predicate<T> valueLessThen(T max) {
        return value -> value.compareTo(max) <= 0;
    }

    public static <T extends Comparable<T>> Predicate<T> valueMoreThen(T min) {
        return value -> value.compareTo(min) >= 0;
    }

    // Predicate for elements from Warehouse
    public static Predicate<Cube> forCubeProperties(
            Predicate<Optional<CubeParameter>> cubePropertiesPredicate) {
        return (cube) -> {
            Optional<CubeParameter> cubeProperty = WareHouse.getInstance().get(cube.getCubeId());
            return cubePropertiesPredicate.test(cubeProperty);
        };
    }

    // Main predicate creator
    public static <T, K> Predicate<K> createPredicate(Function<K, T> fieldExtractor,
                                                      Predicate<T> predicate) {
        return value -> predicate.test(fieldExtractor.apply(value));
    }
}
