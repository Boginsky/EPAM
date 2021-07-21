package parser;

import creator.CreatorNode;
import entity.Component;
import entity.ComponentType;
import entity.impl.TextComponent;
import exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.impl.ParagraphParser;

import static org.testng.AssertJUnit.assertEquals;

public class ParagraphParserTest {
    private TextParser parser;
    private Component text;
    private String testLine = "    a b. a.";

    @Test
    public void paragraphParserPositiveTest() throws CustomException {
        Component result = parser.parse(testLine);
        assertEquals(result, text);
    }

    @BeforeClass
    public void createParser() {
        parser = new ParagraphParser();
        text = new TextComponent(ComponentType.TEXT);

        Component paragraph = new TextComponent(ComponentType.PARAGRAPH);

        Component sentence = new TextComponent(ComponentType.SENTENCE);
        Component tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("ab"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("bb"));
        tmp.addTextComponent(CreatorNode.createNode("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);

        sentence = new TextComponent(ComponentType.SENTENCE);
        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreatorNode.createNode("ab"));
        tmp.addTextComponent(CreatorNode.createNode("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);

        text.addTextComponent(paragraph);
    }
}
