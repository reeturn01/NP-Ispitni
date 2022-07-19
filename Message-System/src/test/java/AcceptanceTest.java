import main.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;

public class AcceptanceTest {
    private ByteArrayOutputStream stdOutputCaptor = new ByteArrayOutputStream();
    private final PrintStream stdOutput = System.out;
    private InputStream stdInput= System.in;
    private ByteArrayInputStream stdInputCaptor;


    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(stdOutputCaptor));
    }

    @AfterEach
    void tearDown(){
        System.setOut(stdOutput);
        System.setIn(stdInput);
    }


    @ParameterizedTest
    @MethodSource("HelperGenerationFunctions#inputAndOutputGeneratorForAcceptanceTest")
    void mainMethodIntegrationTest(String userInput, String expectedOutput){
        stdInputCaptor = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(stdInputCaptor);

        Main.main(null);
        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
    }
}
