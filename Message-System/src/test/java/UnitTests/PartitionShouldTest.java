package UnitTests;

import main.Message;
import main.Partition;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PartitionShouldTest {

    private final int partitionIndex = 10;
    private List<Message> injectedMessageList;
    private Message other_message_1;
    private Message other_message_2;
    private Message other_message_3;
    private Partition partition = null;
    private Message messageToBeAdded;

    @BeforeEach
    void setUp() {
        injectedMessageList = new ArrayList<>();
        other_message_1 = mock(Message.class);
        other_message_2 = mock(Message.class);
        other_message_3 = mock(Message.class);
        when(other_message_1.getTimestamp()).thenReturn(LocalDateTime.of(2000,1,1,0,0));
        when(other_message_2.getTimestamp()).thenReturn(LocalDateTime.of(2000,1,2,0,0));
        when(other_message_3.getTimestamp()).thenReturn(LocalDateTime.of(2000,1,3,0,0));

        injectedMessageList.add(other_message_1);
        injectedMessageList.add(other_message_2);
        injectedMessageList.add(other_message_3);
        messageToBeAdded = mock(Message.class);
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("addMessage() should:")
    @Nested
    class AddMessageFunctions{
        @Test
        void add_message_to_partition_if_it_does_not_excede_partition_limit(){
            int partitionsLimit = 100;
            partition = new Partition(partitionIndex, partitionsLimit, injectedMessageList);
            when(messageToBeAdded.getTimestamp()).thenReturn(LocalDateTime.now());
            partition.addMessage(messageToBeAdded);
            Assertions.assertTrue(partition.contains(messageToBeAdded));
            injectedMessageList.forEach(m -> Assertions.assertTrue(partition.contains(m)));
        }

        @Test
        void replace_oldest_message_in_partition_if_adding_message_and_partition_limit_is_exceded(){
            int partitionsLimit = 3;
            partition = new Partition(partitionIndex, partitionsLimit, injectedMessageList);
            when(messageToBeAdded.getTimestamp()).thenReturn(LocalDateTime.now());

            partition.addMessage(messageToBeAdded);

            Assertions.assertTrue(partition.contains(messageToBeAdded));

            Assertions.assertFalse(partition.contains(other_message_1));
            injectedMessageList.stream().skip(1).forEach(message -> Assertions.assertTrue(partition.contains(message)));

        }

    }

    @Test
    void return_true_if_message_is_contained(){
        int partitionsLimit = 100;
        injectedMessageList.add(messageToBeAdded);
        partition = new Partition(partitionIndex,partitionsLimit, injectedMessageList);

        Assertions.assertTrue(partition.contains(messageToBeAdded));
    }
    @Test
    void return_false_if_message_is_not_contained(){
        int partitionsLimit = 100;
        partition = new Partition(partitionIndex, partitionsLimit, injectedMessageList);
        Assertions.assertFalse(partition.contains(messageToBeAdded));
    }

    @DisplayName("Return String representation of this object")
    @Test
    void _toString(){
        int partitionsLimit = 100;
        partition = new Partition(partitionIndex, partitionsLimit, injectedMessageList);

        String outputOfToString = partition.toString();

        String expectedOutput = String.format("%2d : Count of messages: %5d%n",partitionIndex, injectedMessageList.size()) +
                "Messages:\r\n" +
                String.format("%s%n%s%n%s", other_message_1, other_message_2, other_message_3);

        Assertions.assertEquals(expectedOutput, outputOfToString);
    }
}
