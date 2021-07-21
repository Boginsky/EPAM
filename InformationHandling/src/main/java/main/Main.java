package main;

import entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;
import parser.impl.ParagraphParser;
import reader.CustomReader;
import service.ComponentService;
import service.impl.TextComponentServiceImpl;

import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger();
    private static String pathToFile = "src/main/resources/data/text.txt";

    public static void main(String[] args) throws Exception {
        CustomReader reader = new CustomReader();
        String text = reader.readText(pathToFile);

        TextParser parser = new ParagraphParser();
        Component textComponent = parser.parse(text);

        Component sentences = textComponent.getAllComponents().get(0).getAllComponents().get(0);

        ComponentService service = new TextComponentServiceImpl();
        service.countVowels(sentences);
        service.countConsonants(sentences);

        List<Component> list = service.findSentencesOfMaxWord(textComponent);

        service.removeSentencesWithMinWords(textComponent, 25);

        service.countEqualWords(textComponent, "has");
    }
}
