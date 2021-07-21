package parser;

import creator.CreatorNode;
import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.impl.SentenceParser;

import static org.testng.AssertJUnit.assertEquals;

public class SentenceParserTest {
    private TextParser parser;
    private Component paragraph;
    private String testLine = "a b. a.";

    @BeforeClass
    public void createParser() {
        parser = new SentenceParser();
        paragraph = new TextComponent(ComponentType.PARAGRAPH);

        Component sentence = new TextComponent(ComponentType.SENTENCE);
        Component tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("a"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("b"));
        tmp.addTextComponent(CreatorNode.createNode("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);

        sentence = new TextComponent(ComponentType.SENTENCE);
        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("a"));
        tmp.addTextComponent(CreatorNode.createNode("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);
    }

    @Test
    public void sentenceParserPositiveTest() throws CustomException {
        Component result = parser.parse(testLine);
        assertEquals(result, paragraph);
    }
}

