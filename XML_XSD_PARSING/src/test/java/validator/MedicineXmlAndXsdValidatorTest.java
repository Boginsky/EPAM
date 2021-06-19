package validator;

import by.boginsky.validator.MedicineXmlAndXsdValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MedicineXmlAndXsdValidatorTest {

    private static final String CORRECT_XML = "src/test/resources/medicines_for_test.xml";
    private static final String CORRECT_XSD = "src/test/resources/medicines_for_test.xsd";
    private static final String INCORRECT_XML = "src/test/resources/medicines_for_test_incorrect.xml";

    @Test
    public void testCorrectXmlFile() {
        boolean result = MedicineXmlAndXsdValidator.xmlAndXsdValid(CORRECT_XSD, CORRECT_XML);
        assertTrue(result);
    }

    @Test
    public void testIncorrectXmlFile() {
        boolean result = MedicineXmlAndXsdValidator.xmlAndXsdValid(CORRECT_XSD, INCORRECT_XML);
        assertFalse(result);
    }

}

