package functions.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelperFunctions {
    public static String readTextFile(Path pathToFile) {
        try {
            return Files.readString(pathToFile);
        } catch (IOException e) {
            throw new RuntimeException(pathToFile + "invalid path to file.", e);
        }
    }
}
