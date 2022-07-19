import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelperGenerationFunctions {
    public static Stream<Arguments> inputAndOutputGeneratorForAcceptanceTest() {
        String userInput = readUserInputFIle();
        String expectedOutput = readExpectedOutputFile();
        return Stream.of(
                Arguments.of(userInput, expectedOutput)
        );
    }

    private static String readExpectedOutputFile() {
        try {
            return Files.lines(Path.of("src/test/resources/expectedOutputFile.txt"))
                    .collect(Collectors.joining("\r\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readUserInputFIle() {
        try {
            return Files.lines(Path.of("src/test/resources/userInputFile.txt"))
                    .collect(Collectors.joining("\r\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
