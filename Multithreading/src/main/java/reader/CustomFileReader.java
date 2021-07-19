package reader;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger();

    public List<String> readCharacteristicOfMcDonalds(String filePath) throws CustomException {
        List<String> infoList;
        Path path = Paths.get(filePath);
        try(Stream<String> lineStream = Files.lines(path)){
            infoList = lineStream.collect(Collectors.toList());
            logger.info("Info from file was read successfully");
        }catch (IOException e) {
            logger.error("Error reading from file: " + e.getLocalizedMessage());
            throw new CustomException("Something happened in reading", e);
        }
        return infoList;
    }
}
