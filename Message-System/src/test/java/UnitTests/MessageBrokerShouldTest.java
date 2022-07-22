package UnitTests;

import main.Message;
import main.MessageBroker;
import main.PartitionDoesNotExistException;
import main.Topic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MessageBrokerShouldTest {

    @Mock
    private HashMap<String, Topic> topicHashMapMock;
    private HashMap<String, Topic> injectedMap;

    @DisplayName("addTopic() should:")
    @Nested
    class AddTopic{
        private LocalDateTime minimumDate;

        @BeforeEach
        void setUp() {
            minimumDate = LocalDateTime.of(2000,1,1,0,0);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/AddNewTopicWithPartitionCountTestValues.csv")
        void add_new_topic_with_partition_count(Integer capacityPerTopic,
                                                String topicName,
                                                int topicPartitionsCount){

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, topicHashMapMock);


            messageBroker.addTopic(topicName, topicPartitionsCount);

            //verify(topicHashMapMock).put(topicName, any(Topic.class));
           verify(topicHashMapMock).putIfAbsent(topicName, new Topic(topicName, topicPartitionsCount));

//            String expectedOutputString = "Broker with  1 topics:\r\n"+
//                    String.format("Topic: %10s Partitions: %5d%n", topicName, partitionsCount) +
//                    IntStream.range(1, partitionsCount+1)
//                            .mapToObj(i -> String.format("%d : Count of messages:%5d%n", i, 0)+"Messages:\r\n")
//                            .collect(Collectors.joining());


        }

        @Test
        void fail_to_add_topic_with_same_name(){
            Integer capacityPerTopic = 5;
            String topicName = "topic1";
            int partitionsCount = 3;

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, topicHashMapMock);

            for (int i = 0; i < 10; i++) {
                messageBroker.addTopic(topicName,partitionsCount+i);
            }

            verify(topicHashMapMock, never()).put(ArgumentMatchers.same(topicName), any());
            verify(topicHashMapMock, never()).computeIfPresent(eq(topicName), any());
        }


    }
    @DisplayName("addMessage() should:")
    @Nested
    class AddMessage{
        @Test
        void add_Message_to_existing_topic() throws PartitionDoesNotExistException {
            String name_of_topic_to_add_message_to = "name of topic to add message to";
            Message messageToAdd = new Message();

            LocalDateTime minimumDate = LocalDateTime.now();

            Integer capacityPerTopic = 5;
            int partitionsCount = 5;

            HashMap<String, Topic> injectedMap = new HashMap<>();
            Topic topic_to_add_message_to = mock(Topic.class);
            Topic other_topic_mock_1 = mock(Topic.class);
            Topic other_topic_mock_2 = mock(Topic.class);
            String other_topic_name_1 = "other topic 1";
            String other_topic_name_2 = "other topic 2";

            injectedMap.put(name_of_topic_to_add_message_to, topic_to_add_message_to);
            injectedMap.put(other_topic_name_1, other_topic_mock_1);
            injectedMap.put(other_topic_name_2, other_topic_mock_2);

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, injectedMap);
            try {
                messageBroker.addMessage(name_of_topic_to_add_message_to, messageToAdd);
            } catch (PartitionDoesNotExistException e) {
                Assertions.fail();
            }
            verify(topic_to_add_message_to).addMessage(messageToAdd);
            verify(other_topic_mock_1,never()).addMessage(any());
            verify(other_topic_mock_2, never()).addMessage(any());

        }

        @Test
        void not_add_Message_if_bad_topic_name_is_provided() throws PartitionDoesNotExistException {
            LocalDateTime minimumDate = LocalDateTime.now();
            Integer capacityPerTopic = 5;
            String invalid_topic_name = "invalid topic name";

            HashMap<String, Topic> injectedMap = new HashMap<>();
            Topic topic_mock_1 = mock(Topic.class);
            Topic topic_mock_2 = mock(Topic.class);
            Topic topic_mock_3 = mock(Topic.class);
            String topic_mock_1_name = "topic1";
            String topic_mock_2_name = "topic2";
            String topic_mock_3_name = "topic3";

            injectedMap.put(topic_mock_1_name, topic_mock_1);
            injectedMap.put(topic_mock_2_name, topic_mock_2);
            injectedMap.put(topic_mock_3_name, topic_mock_3);

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, injectedMap);

            Message messageToAdd = new Message();

            messageBroker.addMessage(invalid_topic_name, messageToAdd);

            verify(topic_mock_1, never()).addMessage(any());
            verify(topic_mock_2, never()).addMessage(any());
            verify(topic_mock_3, never()).addMessage(any());

        }
    }

    @BeforeEach
    void setUp() {
        injectedMap = new HashMap<>();
    }

    @Test
    void change_partitionsCount_of_existing_topic(){
        LocalDateTime minimumDate = LocalDateTime.now();
        Integer capacityPerTopic = 5;


        Topic topic_to_change_settings_to = mock(Topic.class);
        Topic other_topic_mock_1 = mock(Topic.class);
        Topic other_topic_mock_2 = mock(Topic.class);
        String topic_to_change_settings_to_name = "change settings to this topic";
        String other_topic_name_1 = "other topic 1";
        String other_topic_name_2 = "other topic 2";

        injectedMap.put(other_topic_name_1, other_topic_mock_1);
        injectedMap.put(other_topic_name_2, other_topic_mock_2);
        injectedMap.put(topic_to_change_settings_to_name, topic_to_change_settings_to);

        MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, injectedMap);

        int new_partitions_count_for_topic = 100;

        messageBroker.changeTopicSettings(topic_to_change_settings_to_name, new_partitions_count_for_topic);

        verify(topic_to_change_settings_to).changeNumberOfPartitions(new_partitions_count_for_topic);
        verify(other_topic_mock_1, never()).changeNumberOfPartitions(anyInt());
        verify(other_topic_mock_2, never()).changeNumberOfPartitions(anyInt());
    }

}
