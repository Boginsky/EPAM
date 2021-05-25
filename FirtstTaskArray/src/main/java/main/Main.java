package main;

import exception.CustomException;
import reader.CustomReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CustomException {
        String FILE_TO_READ = "src/main/resources/info.txt";

        CustomReader customReader = new CustomReader();
        Object[] a = customReader.dataFromFile(FILE_TO_READ);
        System.out.println(Arrays.toString(a));


    }
}
