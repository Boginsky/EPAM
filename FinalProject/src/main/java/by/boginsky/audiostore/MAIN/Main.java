package by.boginsky.audiostore.MAIN;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Collections.shuffle(list);
        System.out.println(list);
        String a = "sdadsasd";
        String b = "sdadsasd";
        String c = format("%s %s",a,b);
        System.out.println(c);

    }
}
