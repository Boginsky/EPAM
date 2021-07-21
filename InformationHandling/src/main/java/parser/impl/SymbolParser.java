package parser.impl;

import creator.CreatorNode;
import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser implements TextParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SYMBOL_REGEX = ".";

    @Override
    public Component parse(String lexeme) throws CustomException {
        Pattern pattern = Pattern.compile(SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(lexeme);
        TextComponent word = new TextComponent(ComponentType.WORD);
        while (matcher.find()) {
            Component node = CreatorNode.createNode(matcher.group());
            word.addTextComponent(node);
        }
        logger.info("Symbols parsing is finished...");
        return word;
    }
}
