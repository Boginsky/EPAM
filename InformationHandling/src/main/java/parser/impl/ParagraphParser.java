package parser.impl;

import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParser implements TextParser {
    private static Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "(\\s{4})";
    private static final int EMPTY_PARAGRAPH_POSITION = 0;
    private TextParser parser = new SentenceParser();

    @Override
    public Component parse(String data) throws CustomException {
        if (data == null || data.isBlank()) {
            throw new CustomException("Input data is not correct...");
        }
        String[] paragraphs = data.split(PARAGRAPH_REGEX);
        List<String> paragraphsList = new ArrayList<>(Arrays.asList(paragraphs));
        paragraphsList.remove(EMPTY_PARAGRAPH_POSITION);
        TextComponent text = new TextComponent(ComponentType.TEXT);
        for (String paragraph : paragraphsList) {
            Component tmpParagraph = parser.parse(paragraph);
            text.addTextComponent(tmpParagraph);
        }
        logger.info("Paragraphs was parsed");
        return text;
    }
}