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

public class LexemeParser implements TextParser {
    private static Logger logger = LogManager.getLogger();
    private static final String LEXEME_REGEX = "\\s?(.*?)($|\\s)";
    private TextParser parser = new SymbolParser();

    @Override
    public Component parse(String data) throws CustomException {
        if (data == null || data.isBlank()) {
            throw new CustomException("Input data is not correct...");
        }
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(data);
        TextComponent sentence = new TextComponent(ComponentType.SENTENCE);
        String lexeme;
        while (matcher.find()) {
            lexeme = matcher.group(1);
            if (lexeme.length() > 0) {
                Component word = parser.parse(lexeme);
                sentence.addTextComponent(word);
            }
        }
        logger.info("Lexemes parsing is finished...");
        return sentence;
    }
}
