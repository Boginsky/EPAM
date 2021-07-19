package util;

public class CustomerID {
    private static long counter = 0;

    public CustomerID() {
    }

    public static long generateID(){
        return ++counter;
    }
}
