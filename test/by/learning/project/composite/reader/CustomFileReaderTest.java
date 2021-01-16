package by.learning.project.composite.reader;

import by.learning.project.composite.parser.ParseChain;
import by.learning.project.composite.parser.TextParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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