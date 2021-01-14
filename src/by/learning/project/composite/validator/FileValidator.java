package by.learning.project.composite.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {

    private static final Logger logger = LogManager.getLogger(FileValidator.class);

    public static boolean isFileValid(String path) {
        Path filePath = Paths.get(path);
        boolean fileExist = Files.exists(filePath);
        boolean isEmpty = false;
        try {
            long size = Files.size(filePath);
            isEmpty = size > 0;
        } catch (IOException e) {
            logger.error("Can`t read file", e);
        }
        return (isEmpty && fileExist);
    }
}
