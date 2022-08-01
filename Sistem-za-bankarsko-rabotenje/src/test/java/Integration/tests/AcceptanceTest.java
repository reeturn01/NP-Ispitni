package Integration.tests;

import functions.helper.HelperFunctions;
import main.BankTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class AcceptanceTest {
    public static final Path SAMPLE_INPUT_PATH = Path.of("src/test/resources/SampleInput.txt");
    public static final Path SAMPLE_OUTPUT_PATH = Path.of("src/test/resources/SampleOutput.txt");
    private final InputStream stdInput = System.in;
    private final PrintStream stdOutput = System.out;

    private final ByteArrayInputStream stdInputInjector = new ByteArrayInputStream(HelperFunctions.readTextFile(SAMPLE_INPUT_PATH).getBytes(StandardCharsets.UTF_8));
    private final ByteArrayOutputStream stdOutputCaptor = new ByteArrayOutputStream();
    private final String expectedOutput = HelperFunctions.readTextFile(SAMPLE_OUTPUT_PATH);

    @BeforeEach
    void setUp() {
        System.setIn(stdInputInjector);
        System.setOut(new PrintStream(stdOutputCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(stdInput);
        System.setOut(stdOutput);
    }

    @Test
    void acceptanceTest(){
        BankTester.main(null);

        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
    }
}
