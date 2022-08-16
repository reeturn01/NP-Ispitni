package Integration.tests;

import main.GenericCollectionTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class AcceptanceTest {
    private final ByteArrayOutputStream stdOutputCaptor = new ByteArrayOutputStream();
    private final InputStream stdInput = System.in;
    private final PrintStream stdOutput = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(stdOutputCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(stdInput);
        System.setOut(stdOutput);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/pathToInputOutputTestData.csv")
    void test_main(String pathToInputData, String pathToOutputData){
        ByteArrayInputStream stdInputInjector = new ByteArrayInputStream(readFileAsString(pathToInputData).getBytes());
        System.setIn(stdInputInjector);

        GenericCollectionTest.main(null);

        String expectedOutput = readFileAsString(pathToOutputData);
        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
    }

    private String readFileAsString(String pathToFile) {
        Path path = Path.of(pathToFile);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
