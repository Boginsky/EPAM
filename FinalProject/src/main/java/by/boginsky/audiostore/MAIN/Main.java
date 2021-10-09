package by.boginsky.audiostore.MAIN;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String a = "E:\\EPAM\\Новая папка\\EPAM\\FinalProject\\src\\main\\webapp\\static\\image\\user\\altair___animus_eagle_by_kejablank_d4v0aqu-fullview.jpg";
        System.out.println(a);
        a = "." + a.replace("\\", "/").substring(53);
        System.out.println("." + a);


        HashMap<Integer,String> map = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        map.put(1,"Кефир");
        map.put(1,"Кефир");
        map.put(1,"Молоко");
        System.out.println(map);
        hashSet.add("Кефир");
        hashSet.add("Кефир");
        hashSet.add("Молоко");
        hashSet.add("МОлоко");
        System.out.println(hashSet);

    }
}
