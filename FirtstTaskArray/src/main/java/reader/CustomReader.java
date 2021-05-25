package reader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.CustomValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomReader {

    static Logger logger = LogManager.getLogger();
    private static final String FILE_TO_READ = "resources/info.txt";
    CustomValidator validator = new CustomValidator();

    public String[] dataFromFile(String path) {
        String[] infoFromFile = new String[20];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if(validator.validatorOfString(line)){
                infoFromFile[i] = line;
                i++;}
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return infoFromFile;
    }
}
