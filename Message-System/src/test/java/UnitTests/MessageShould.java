package UnitTests;

import main.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDateTime;

public class MessageShould {
    private Message message;

    @ParameterizedTest
    @CsvFileSource(delimiter = ';', resources = "/MessageShould Test Values/ToStringTestValues.csv")
    void return_string_representation_of_this_object(LocalDateTime timestamp, String messageText, String key, String expectedOutput){
        message = new Message(timestamp, messageText, key);

        Assertions.assertEquals(expectedOutput, message.toString());
    }


}
