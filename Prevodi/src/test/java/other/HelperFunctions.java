package other;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelperFunctions {
    public static String readFile(String pathToFile) {
        try {
            return Files.readString(Path.of(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
