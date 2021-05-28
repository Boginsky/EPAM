package main;

import creator.CustomCreator;
import entity.SelfMadeArray;
import exception.CustomException;
import parser.CustomParser;
import reader.CustomReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CustomException {
        String FILE_TO_READ = "src/main/resources/info.txt";

        CustomReader customReader = new CustomReader();
        CustomParser customParser = new CustomParser();
        CustomCreator customCreator = new CustomCreator();
        ArrayList<String> list = customReader.dataFromFile(FILE_TO_READ);
        int[] list1 = customParser.parserOfRowArray(list);
        SelfMadeArray selfMadeArray = customCreator.creatorOfArray(list1);
        System.out.println(selfMadeArray.toString());


    }
}
