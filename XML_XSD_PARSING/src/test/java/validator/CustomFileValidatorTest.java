package validator;

import by.boginsky.validator.CustomFileValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomFileValidatorTest {
    private static final String PATH_TO_XML = "src/test/resources/medicines_for_test.xml";

    @Test
    public void testPositiveExistAndValidFile(){
        boolean result = CustomFileValidator.isFileValid(PATH_TO_XML);
        assertTrue(result);
    }

    @Test
    public void testNegativeExistAndValidFile(){
        boolean result = CustomFileValidator.isFileValid(null);
        assertFalse(result);
    }


}
