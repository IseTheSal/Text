package by.learning.project.composite.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFileReader {

    private static final Logger logger = LogManager.getLogger(CustomFileReader.class);

    private static final String DELIMITER = "/n";

    public String readFile(String path) {
        String result = "";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> textList = bufferedReader.lines().collect(Collectors.toList());
            result = String.join(DELIMITER, textList);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARN, e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.log(Level.WARN, "file was not close", e);
                }
            }
        }
        return result;
    }
}
