package Integration.tests;

import main.StudentRecordsTest;
import org.junit.jupiter.api.*;
import other.HelperFunctions;

import java.io.*;

public class StudentRecordsIT {
    public static final String EXPECTED_OUTPUT = HelperFunctions.getSampleOutput();
    private final InputStream stdInputStream = System.in;
    private final PrintStream stdOutputStream = System.out;
    private ByteArrayInputStream stdInputStreamInjector;
    private ByteArrayOutputStream stdOutputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        stdInputStreamInjector = new ByteArrayInputStream(HelperFunctions.getSampleInput().getBytes());
        System.setIn(stdInputStreamInjector);
        System.setOut(new PrintStream(stdOutputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(stdInputStream);
        System.setOut(stdOutputStream);
    }

    @DisplayName("Integration Testing all classes")
    @Test
    void AcceptanceTest(){

        StudentRecordsTest.main(null);

        Assertions.assertEquals(EXPECTED_OUTPUT, stdOutputStreamCaptor.toString().trim());
    }

}
