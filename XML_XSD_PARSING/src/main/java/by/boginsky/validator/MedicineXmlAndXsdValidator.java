package by.boginsky.validator;

import by.boginsky.handler.MedicineErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class MedicineXmlAndXsdValidator {

    private static Logger logger = LogManager.getLogger();

    public static boolean xmlAndXsdValid(String pathToXsd, String pathToXml){
        boolean result = false;
        if (!(CustomFileValidator.isFileValid(pathToXml) && CustomFileValidator.isFileValid(pathToXsd))) {
            return false;
        }
        Source xmlSourse = new StreamSource(pathToXml);
        Source xsdSourse = new StreamSource(pathToXsd);
        File file = new File(pathToXsd);

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(file);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new MedicineErrorHandler());
            validator.validate(xmlSourse);
            result = true;
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "Xml of Xsd are not valid" + e.getMessage());
        }
        return result;
    }
}
