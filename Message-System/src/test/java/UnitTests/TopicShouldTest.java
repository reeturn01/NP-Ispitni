package UnitTests;

import main.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class TopicShouldTest {
    private Topic topic;
    private HashMap<Integer, Partition> injectedPartitions = new HashMap<>();

    @Mock
    Partition other_partition_mock_1;
    @Mock
    Partition other_partition_mock_2;
    @Mock
    Partition other_partition_mock_3;
    private int indexOfPartitionForAddingMessages = 4;

    @BeforeEach
    void setUp() {
        injectedPartitions.put(1, other_partition_mock_1);
        injectedPartitions.put(2, other_partition_mock_2);
        injectedPartitions.put(3, other_partition_mock_3);
    }

    @Test
    void return_String_representation_of_this_topic(){

        String topicName = "topic1";
        int partitionsCount = 6;
        int partitionsLimit = 100;
        topic = new Topic(topicName,partitionsLimit, partitionsCount, injectedPartitions);

        String outputOfToString = topic.toString();

        String expectedOutput = String.format("Topic: %10s Partitions: %5d%n", topicName, partitionsCount)+
                String.format("%s%n%s%n%s", other_partition_mock_1, other_partition_mock_2, other_partition_mock_3);

        Assertions.assertEquals(expectedOutput, outputOfToString);

    }

    @DisplayName("addMessage() should:")
    @Nested
    class AddMessage{
        private String messageText;
        private String messageKey;
        private Integer messagePartition;
        private String topicName;
        private int topicPartitionsCount;
        private LocalDateTime messageTimestamp;

        @BeforeEach
        void setUp() {
            messageText = "";
            messageKey = "";
            messagePartition = 12;
            topicName = "topic1";
            topicPartitionsCount = 10;
            messageTimestamp = LocalDateTime.now();
        }

        @Test
        void throw_PartitionDoesNotExistException_if_message_partition_does_not_match_any_in_topic(){
            int partitionsLimit = 100;
            topic = new Topic(topicName, partitionsLimit, topicPartitionsCount);


            Message message = new Message(messageTimestamp, messageText, messagePartition, messageKey);

            Assertions.assertTrue(topicPartitionsCount <= messagePartition +1, "Invalid messagePartition value given for test!!!");
            Assertions.assertThrows(PartitionDoesNotExistException.class, ()-> topic.addMessage(message));
        }

        @Test
        void add_Message_to_messagePartition_if_it_is_contained() throws PartitionDoesNotExistException {
            Partition partitionToAddMessageToMock = mock(Partition.class);
            injectedPartitions.put(indexOfPartitionForAddingMessages, partitionToAddMessageToMock);

            int partitionsLimit = 100;
            topic = new Topic(topicName, partitionsLimit, topicPartitionsCount,injectedPartitions);


            messagePartition = indexOfPartitionForAddingMessages;
            Message message = new Message(messageTimestamp, messageText, messagePartition, messageKey);

            Assertions.assertTrue(messagePartition <= topicPartitionsCount +1, "Invalid messagePartition value given for test!!!");

            topic.addMessage(message);

            verify(partitionToAddMessageToMock).addMessage(message);
            verify(other_partition_mock_1, never()).addMessage(any());
            verify(other_partition_mock_2, never()).addMessage(any());
            verify(other_partition_mock_3, never()).addMessage(any());

        }
        @Test
        void add_Message_to_partition_given_by_assignPartition_from_PartitionAssigner_class_if_not_specified_in_the_message() throws PartitionDoesNotExistException {
            Partition [] partitions = new Partition[3];
            partitions[0] = other_partition_mock_1;
            partitions[1] = other_partition_mock_2;
            partitions[2] = other_partition_mock_3;

            Message messageToAdd = new Message(messageTimestamp, messageText, messageKey);

            Integer partitionIndexToAddMessageTo = PartitionAssigner.assignPartition(messageToAdd, partitions.length);
            int partitionsLimit = 100;
            topic = new Topic(topicName, partitionsLimit, topicPartitionsCount, injectedPartitions);

            topic.addMessage(messageToAdd);

            for (int i = 0; i < partitions.length; i++) {
                if (partitionIndexToAddMessageTo-1 == i)
                    verify(partitions[i]).addMessage(messageToAdd);
                else verify(partitions[i], never()).addMessage(any());
            }

        }
    }

    @Test
    void change_number_of_partitions_if_new_number_is_greater_than_previous_partitions_count(){
        String topicName = "topic1";
        int oldPartitionCount = 5;
        int partitionsLimit = 100;
        topic = new Topic(topicName, partitionsLimit, oldPartitionCount);

        int newPartitionCount = 10;
        Assertions.assertTrue(oldPartitionCount < newPartitionCount);

        Assertions.assertDoesNotThrow(()->topic.changeNumberOfPartitions(newPartitionCount));
        Assertions.assertTrue(topic.getPartitionsCount() == newPartitionCount);
    }

    @Test
    void throw_UnsupportedOperationException_if_new_number_is_lower_than_previous_partitions_count(){
        String topicName = "topic1";
        int oldPartitionCount = 5;
        int partitionsLimit = 100;
        topic = new Topic(topicName, partitionsLimit, oldPartitionCount);

        int newPartitionCount = 1;

        Assertions.assertTrue(oldPartitionCount >= newPartitionCount);
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                topic.changeNumberOfPartitions(newPartitionCount));
        Assertions.assertFalse(topic.getPartitionsCount() == newPartitionCount);
    }
}
