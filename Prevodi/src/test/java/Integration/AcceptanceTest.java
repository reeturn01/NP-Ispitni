package Integration;

import main.SubtitlesTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import other.HelperFunctions;

import java.io.*;

public class AcceptanceTest {

    private ByteArrayOutputStream stdOutputCaptor = new ByteArrayOutputStream();
    private final PrintStream stdOutput = System.out;
    private final InputStream stdInput = System.in;
    private ByteArrayInputStream stdInputInjector;


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
    @CsvSource({
            "src/test/resources/TestCase1_Input, src/test/resources/TestCase1_Output"
            ,"src/test/resources/TestCase2_Input, src/test/resources/TestCase2_Output"
            ,"src/test/resources/TestCase3_Input, src/test/resources/TestCase3_Output"
    })
    void main_method_test(String pathToInputFile, String pathToOutputFile){
        stdInputInjector = new ByteArrayInputStream(
                HelperFunctions.readFile(pathToInputFile)
                        .getBytes());
        System.setIn(stdInputInjector);

        SubtitlesTester.main(null);

        String expectedOutput = HelperFunctions.readFile(pathToOutputFile);
        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
    }

}
