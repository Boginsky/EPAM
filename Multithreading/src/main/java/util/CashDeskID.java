package util;

public class CashDeskID {
    private static long counter = 0;

    public CashDeskID() {
    }

    public static long generateID(){
        return ++counter;
    }
}

