package creator;

import entity.Component;
import entity.ComponentType;
import entity.impl.LetterNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatorNode {
    private static final String ALPHABET_REGEX = "\\p{IsAlphabetic}";

    public static Component createNode(String symbol) {

        Pattern pattern = Pattern.compile(ALPHABET_REGEX);
        Matcher matcher = pattern.matcher(symbol);
        while (matcher.find()) {
            return new LetterNode(ComponentType.SYMBOL_NODE, symbol.charAt(0));
        }
        return new LetterNode(ComponentType.PUNCTUATION_NODE, symbol.charAt(0));
    }
}
