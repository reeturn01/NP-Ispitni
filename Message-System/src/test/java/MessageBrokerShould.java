import main.MessageBroker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

public class MessageBrokerShould {
    public static final LocalDateTime STARTING_DATE = LocalDateTime.of(1, 1, 2000, 0, 0);
    private MessageBroker messageBroker;

    @BeforeEach
    void setUp(){
        messageBroker = new MessageBroker(STARTING_DATE, Integer.MAX_VALUE);
    }

    @DisplayName("Method for adding new Topic with predefined num of partitions.")
    @Test
    void addTopic(){
        messageBroker.addTopic("topic1", 3);
// Topics with same name are not allowed to be added
    }


}
