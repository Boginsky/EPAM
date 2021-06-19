package by.boginsky.main;

import by.boginsky.entity.*;
import by.boginsky.exception.MedicineException;
import by.boginsky.parser.DomXmlParser;
import by.boginsky.parser.SaxXmlParser;
import by.boginsky.parser.StaxXmlParser;
import by.boginsky.parser.factory.impl.DomXmlParserFactory;
import by.boginsky.validator.MedicineXmlAndXsdValidator;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws MedicineException {
        StaxXmlParser staxXmlParser = new StaxXmlParser();
        DomXmlParser domXmlParser = new DomXmlParser();
        SaxXmlParser saxXmlParser = new SaxXmlParser();

        try {
            domXmlParser.parserXml("src/main/resources/data/medicines.xml");
            saxXmlParser.parserXml("src/main/resources/data/medicines.xml");
            staxXmlParser.parserXml("src/main/resources/data/medicines.xml");
        } catch (MedicineException e) {
            e.printStackTrace();
        }
        Set<AbstractCommonMedicine> medicines = domXmlParser.parserXml("src/main/resources/data/medicines.xml");

        System.out.println(medicines);


    }
}
