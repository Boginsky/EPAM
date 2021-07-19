package util;

public class ShapeIdGenerator {

    private static long idCounter;

    public static long generateId(){
        return ++idCounter;
    }
}
