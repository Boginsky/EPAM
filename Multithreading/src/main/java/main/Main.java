package main;

import creator.CustomersCreator;
import entity.Customers;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.CustomParser;
import reader.CustomFileReader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_PATH = "src/main/resources/data/info.txt";

    public static void main(String[] args) {
        try {

            List<String> characteristics;
            List<Integer> characteristicsForWork;
            CustomFileReader fileReader = new CustomFileReader();
            CustomParser customParser = new CustomParser();
            characteristics = fileReader.readCharacteristicOfMcDonalds(FILE_PATH);
            characteristicsForWork = customParser.parseListOfCharacteristicsOfMcDonalds(characteristics);

            List<Customers> customersList = CustomersCreator.createCustomers(characteristicsForWork);
            logger.info("INFORMATION ABOUT QUEUES:");
            for (int i = 0; i < customersList.size(); i++) {
                List<Customers> localList = (List) customersList.get(i);
                localList.forEach(logger::info);
                logger.info("END OF INFORMATION");
                logger.info("START OF THREADS");
                ExecutorService executorService = Executors.newFixedThreadPool(localList.size());
                localList.forEach(executorService::submit);
                executorService.shutdown();
            }
        } catch (CustomException e) {
            logger.error("Error in main: ", e);
        }
    }
}
