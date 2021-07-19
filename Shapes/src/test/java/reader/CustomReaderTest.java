package reader;

import exception.CubeException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CustomReaderTest {

    @Test
    public void readDataFromFileTest() throws CubeException {
        List<String> expected = Arrays.asList(
                "1.0 2.0 3.0 1.0",
                "-1 2 1 4",
                "0 1 2 3",
                "-5.2 0 14 5",

                "0 1 4 6"
        );

        CubeReader reader = new CubeReader();

        List<String> actual = reader.readDataFromFile("data/data.txt");

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CubeException.class)
    public void readDataFromFileExceptionTest() throws CubeException {
        CubeReader reader = new CubeReader();
        reader.readDataFromFile("missing_file.txt");
    }
}
