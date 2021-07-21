package service;

import entity.Component;
import exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.TextParser;
import parser.impl.LexemeParser;
import parser.impl.ParagraphParser;
import service.impl.TextComponentServiceImpl;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class TextComponentServiceImplTest {
    private TextParser textParser;
    private TextComponentServiceImpl service;
    private TextParser sentenceParser;

    @BeforeClass
    public void createParser() {
        textParser = new ParagraphParser();
        sentenceParser = new LexemeParser();
        service = new TextComponentServiceImpl();
    }

    @DataProvider(name = "data_text")
    public Object[][] createParagraphData() throws CustomException {
        String str1 = "It is a long a!=b established fact that a reader will be distracted by the readable";
        String str2 = "content of a page when looking at its layout. The point of using Ipsum is that it has";
        return new Object[][]{
                {textParser.parse(str1), textParser.parse(str2)}
        };
    }

    @Test(dataProvider = "data_text")
    public void sortParagraphsBySentencesTet(Component extend, Component component) throws CustomException {
        service.sortParagraphsBySentences(component);
        assertEquals(extend, component);
    }

    @DataProvider(name = "equal_words")
    public Object[][] countEqualWordsData() throws CustomException {
        String str = "    World is beautiful. There are a lot of countries in world.";
        return new Object[][]{
                {2, textParser.parse(str), "world"}
        };
    }

    @Test(dataProvider = "equal_words")
    public void countEqualWordsTest(Integer extend, Component component, String word) throws CustomException {
        Integer result = service.countEqualWords(component, word);
        assertEquals(extend, result);
    }

    @DataProvider(name = "vowels")
    public Object[][] vowelsData() throws CustomException {
        String str = "I love Java.";
        return new Object[][]{
                {5, sentenceParser.parse(str)}
        };
    }

    @Test(dataProvider = "vowels")
    public void countVowelsTest(long extend, Component component) throws CustomException {
        long result = service.countVowels(component);
        assertEquals(extend, result);
    }

    @DataProvider(name = "consonants")
    public Object[][] consonantsData() throws CustomException {
        String str = "Cat is big.";
        return new Object[][]{
                {5, sentenceParser.parse(str)}
        };
    }

    @Test(dataProvider = "consonants")
    public void countConsonantsTest(long extend, Component component) throws CustomException {
        long result = service.countConsonants(component);
        assertEquals(extend, result);
    }

    @DataProvider(name = "find_sentences")
    public Object[][] findSentencesData() throws CustomException {
        String str = " Cat is big.";
        String text = "    Cat is big. Cat is big. I am.";
        Component sentence1 = sentenceParser.parse(str);
        Component sentence2 = sentenceParser.parse(str);
        List<Component> sentences = List.of(sentence1, sentence2);
        return new Object[][]{
                {sentences, textParser.parse(text)}
        };
    }

    @Test(dataProvider = "find_sentences")
    public void findSentencesOfMaxWordTest(List<Component> extend, Component component) throws CustomException {
        List<Component> result = service.findSentencesOfMaxWord(component);
        assertEquals(extend, result);
    }

}