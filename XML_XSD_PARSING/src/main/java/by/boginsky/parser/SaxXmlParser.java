package by.boginsky.parser;

import by.boginsky.entity.AbstractCommonMedicine;
import by.boginsky.exception.MedicineException;
import by.boginsky.validator.CustomFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaxXmlParser implements XmlParser {

    public static Logger logger = LogManager.getLogger();
    private SAXParserFactory factory;
    private SAXParser parser;
    private Set<AbstractCommonMedicine> medicines;

    public SaxXmlParser() {
        medicines = new HashSet<>();
        try {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            logger.log(Level.ERROR, "Error in configuration: " + e.getMessage());
        }
    }

    @Override
    public Set<AbstractCommonMedicine> parserXml(String filePath) throws MedicineException {
        if (!CustomFileValidator.isFileValid(filePath)) {
            logger.log(Level.ERROR, "Invalid file");
            throw new MedicineException("Invalid file");
        }
        MedicineHandler medicineHandler = new MedicineHandler();
        medicineHandler.setMedicines(medicines);
        try {
            parser.parse(new File(filePath), medicineHandler);
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "Invalid file" + e.getMessage());
            throw new MedicineException("Invalid file");
        }
        return medicines;
    }
}
