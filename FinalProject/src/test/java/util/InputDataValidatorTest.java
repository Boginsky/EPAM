package util;

import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class InputDataValidatorTest {

    private InputDataValidator inputDataValidator;
    private String emailForTest;
    private String incorrectEmailForTest;
    private String firstPasswordForTest;
    private String secondPasswordForTest;
    private String incorrectPasswordForTest;
    private BigDecimal priceForTest;
    private BigDecimal incorrectPriceForTest;
    private String userNameForTest;
    private String incorrectUserNameForTest;

    @BeforeClass
    public void setUp(){
        inputDataValidator = InputDataValidatorImpl.getInstance();
        emailForTest = "boginsky.kirill@gmail.com";
        incorrectEmailForTest = "boginsky.kirillgmail.com";
        firstPasswordForTest = "13213dsdasd2e1323";
        secondPasswordForTest = "13213dsdasd2e1323";
        incorrectPasswordForTest = "13213d@sdasd2e1323";
        priceForTest = BigDecimal.valueOf(10L);
        incorrectPriceForTest = BigDecimal.ZERO;
        userNameForTest =  "Kirill";
        incorrectUserNameForTest = "KirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKirillKiri";
    }

    @Test
    public void isCorrectEmailTest(){
        Assert.assertTrue(inputDataValidator.isCorrectEmail(emailForTest));
    }

    @Test
    public void isIncorrectEmailTest(){
        Assert.assertFalse(inputDataValidator.isCorrectEmail(incorrectEmailForTest));
    }

    @Test
    public void isCorrectPasswordTest(){
        Assert.assertTrue(inputDataValidator.isCorrectPassword(firstPasswordForTest));
    }

    @Test
    public void isIncorrectPasswordTest(){
        Assert.assertFalse(inputDataValidator.isCorrectPassword(incorrectPasswordForTest));
    }

    @Test
    public void arePasswordsEqualTest(){
        Assert.assertTrue(inputDataValidator.arePasswordsEqual(firstPasswordForTest, secondPasswordForTest));
    }

    @Test
    public void arePasswordsNotEqualTest(){
        Assert.assertFalse(inputDataValidator.arePasswordsEqual(incorrectPasswordForTest, secondPasswordForTest));
    }

    @Test
    public void isCorrectPriceTest(){
        Assert.assertTrue(inputDataValidator.isCorrectPrice(priceForTest));
    }

    @Test
    public void isIncorrectPriceTest(){
        Assert.assertFalse(inputDataValidator.isCorrectPrice(incorrectPriceForTest));
    }

    @Test
    public void isCorrectUserNameTest(){
        Assert.assertTrue(inputDataValidator.isCorrectUserName(userNameForTest));
    }

    @Test
    public void isIncorrectUserNameTest(){
        Assert.assertFalse(inputDataValidator.isCorrectUserName(incorrectUserNameForTest));
    }

    @AfterClass
    public void tierDown(){
        inputDataValidator = null;
        emailForTest = null;
        incorrectEmailForTest = null;
        firstPasswordForTest = null;
        secondPasswordForTest = null;
        incorrectPasswordForTest = null;
        priceForTest = null;
        incorrectPriceForTest = null;
        userNameForTest = null;
        incorrectUserNameForTest = null;
    }
}
