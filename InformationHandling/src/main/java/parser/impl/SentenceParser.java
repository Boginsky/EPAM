package parser.impl;

import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = ".+?([.]{3}|[.!?])((\\r\\n)|$|\\s)";
    private TextParser parser = new LexemeParser();

    @Override
    public Component parse(String data) throws CustomException {
        if (data == null || data.isBlank()) {
            throw new CustomException("Input data is not correct...");
        }
        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);
        TextComponent paragraph = new TextComponent(ComponentType.PARAGRAPH);
        while (matcher.find()) {
            String s = matcher.group();
            s = s.replaceAll("\\r\\n", " ");
            Component sentence = parser.parse(s);
            paragraph.addTextComponent(sentence);
        }
        logger.info("Sentences parsing is finished...");
        return paragraph;
    }
}
