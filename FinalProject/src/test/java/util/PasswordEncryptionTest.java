package util;

import by.boginsky.audiostore.util.PasswordEncryption;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PasswordEncryptionTest {

    private String expected;
    private String actual;

    @BeforeClass
    public void setUp() {
        expected = "202cb962ac59075b964b07152d234b70";
        actual = new PasswordEncryption().encryptsPassword("123");
    }

    @Test
    public void encryptsPasswordTest() {
        Assert.assertEquals(expected, actual);
    }

    @AfterClass
    public void tierDown() {
        expected = null;
        actual = null;
    }
}
