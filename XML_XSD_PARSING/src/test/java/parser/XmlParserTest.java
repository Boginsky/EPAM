package parser;

import by.boginsky.entity.AbstractCommonMedicine;
import by.boginsky.exception.MedicineException;
import by.boginsky.parser.XmlParser;
import by.boginsky.parser.factory.XmlParserFactory;
import by.boginsky.parser.factory.impl.DomXmlParserFactory;
import by.boginsky.parser.factory.impl.SaxXmlParserFactory;
import by.boginsky.parser.factory.impl.StaxXmlParserFactory;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class XmlParserTest {

    private static final String pathToXml = "src/test/resources/medicines_for_test.xml";
    private static final int AMOUNT_OF_ENTITIES = 16;
    private XmlParserFactory xmlParserFactory;

    @Test
    public void testDomXmlParser() throws MedicineException {
        xmlParserFactory = new DomXmlParserFactory();
        XmlParser xmlParser = xmlParserFactory.newParser();
        Set<AbstractCommonMedicine> medicines = xmlParser.parserXml(pathToXml);
        assertEquals(medicines.size(), AMOUNT_OF_ENTITIES);
    }

    @Test
    public void testSaxXmlParser() throws MedicineException {
        xmlParserFactory = new SaxXmlParserFactory();
        XmlParser xmlParser = xmlParserFactory.newParser();
        Set<AbstractCommonMedicine> medicines = xmlParser.parserXml(pathToXml);
        assertEquals(medicines.size(), AMOUNT_OF_ENTITIES);
    }

    @Test
    public void testStaxXmlParser() throws MedicineException {
        xmlParserFactory = new StaxXmlParserFactory();
        XmlParser xmlParser = xmlParserFactory.newParser();
        Set<AbstractCommonMedicine> medicines = xmlParser.parserXml(pathToXml);
        assertEquals(medicines.size(), AMOUNT_OF_ENTITIES);
    }
}
