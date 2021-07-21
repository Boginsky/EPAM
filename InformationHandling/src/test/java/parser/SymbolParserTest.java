package parser;

import creator.CreatorNode;
import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.impl.SymbolParser;

import static org.testng.AssertJUnit.assertEquals;

public class SymbolParserTest {
    private TextParser parser;
    private Component word;
    private String testLine = "b";

    @BeforeClass
    public void createParser() {
        parser = new SymbolParser();
        word = new TextComponent(ComponentType.WORD);
        word.addTextComponent(CreatorNode.createNode("b"));
    }

    @Test
    public void symbolParserPositiveTest() throws CustomException {
        Component result = parser.parse(testLine);
        assertEquals(result, word);
    }
}
