package parser;

import creator.CreatorNode;
import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.impl.LexemeParser;

import static org.testng.AssertJUnit.assertEquals;

public class LexemeParserTest {
    private TextParser parser;
    private Component sentence;
    private String testLine = "a b c";

    @BeforeClass
    public void createParser() {
        parser = new LexemeParser();
        sentence = new TextComponent(ComponentType.SENTENCE);
        Component tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("a"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("b"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("c"));
        sentence.addTextComponent(tmp);
    }

    @Test
    public void lexemeParserPositiveTest() throws CustomException {
        Component result = parser.parse(testLine);
        assertEquals(result, sentence);
    }
}
