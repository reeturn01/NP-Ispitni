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

    @Disabled
    @Test
    void return_String_representation_of_this_topic(){

        String topicName = "topic1";
        int partitionsCount = 6;
        topic = new Topic(topicName, partitionsCount);
        LocalDateTime timestamp = LocalDateTime.now();
        String messageText = "Message from the system with id15273";
        Integer messagePartition = 3;
        String key = "DHCFB";
        try {
            topic.addMessage(new Message(timestamp, messageText, messagePartition, key));
        } catch (PartitionDoesNotExistException e) {
            throw new RuntimeException(e);
        }

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
        //        @Mock
//        private PartitionedMessageCollection partitionedMessageCollectionMock;

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
            topic = new Topic(topicName, topicPartitionsCount);


            Message message = new Message(messageTimestamp, messageText, messagePartition, messageKey);

            Assertions.assertTrue(topicPartitionsCount <= messagePartition +1, "Invalid messagePartition value given for test!!!");
            Assertions.assertThrows(PartitionDoesNotExistException.class, ()-> topic.addMessage(message));
        }

        @Test
        void add_Message_to_messagePartition_if_it_is_contained() throws PartitionDoesNotExistException {
            Partition partitionToAddMessageToMock = mock(Partition.class);
            injectedPartitions.put(indexOfPartitionForAddingMessages, partitionToAddMessageToMock);

            topic = new Topic(topicName, topicPartitionsCount,injectedPartitions);


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

            topic = new Topic(topicName, topicPartitionsCount, injectedPartitions);

            topic.addMessage(messageToAdd);

            for (int i = 0; i < partitions.length; i++) {
                if (partitionIndexToAddMessageTo-1 == i)
                    verify(partitions[i]).addMessage(messageToAdd);
                else verify(partitions[i], never()).addMessage(any());
            }

        }
    }


}
