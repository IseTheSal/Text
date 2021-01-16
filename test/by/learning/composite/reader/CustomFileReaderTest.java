package by.learning.composite.reader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomFileReaderTest {

    final String DEFAULT_PATH = "data\\readText.txt";

    @Test
    public void testParse() {
        CustomFileReader fileReader = new CustomFileReader();
        String actual = fileReader.readFile(DEFAULT_PATH);
        String expected = "    Hello. What are you doing right now?\n" +
                "    Pretty good.";
        Assert.assertEquals(actual,expected);
    }

}