package util;

import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.util.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class PropertyReaderTest {

    private Properties expected;
    private Properties actual;

    @BeforeClass
    public void setUp() throws PropertyReaderException {
        expected  = new Properties();
        expected.put("String1","My first servlet project");
        expected.put("String2","for EPAM");
        actual = new PropertyReader().read("database.properties");
    }

    @Test
    public void readDataFromFileTest() {
        Assert.assertEquals(expected,actual);
    }

    @AfterClass
    public void tierDown() {
        expected = null;
        actual = null;
    }
}
