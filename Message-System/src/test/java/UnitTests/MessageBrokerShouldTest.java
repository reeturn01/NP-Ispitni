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
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MessageBrokerShouldTest {

    @Mock
    private TreeMap<String, Topic> topicHashMapMock;
    private TreeMap<String, Topic> injectedMap;

    @Mock
    private Topic OTHER_TOPIC_1;
    private final String OTHER_TOPIC_1_NAME = "OTHER_TOPIC_1_NAME";

    @Mock
    private Topic OTHER_TOPIC_2;
    private final String OTHER_TOPIC_2_NAME = "OTHER_TOPIC_2_NAME";

    @Mock
    private Topic OTHER_TOPIC_3;
    private final String OTHER_TOPIC_3_NAME = "OTHER_TOPIC_3_NAME";

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

            injectedMap.put(OTHER_TOPIC_1_NAME, OTHER_TOPIC_1);
            injectedMap.put(OTHER_TOPIC_2_NAME, OTHER_TOPIC_2);
            injectedMap.put(OTHER_TOPIC_3_NAME, OTHER_TOPIC_3);

            injectedMap.keySet().forEach(tName -> Assertions.assertNotEquals(tName, topicName));

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, injectedMap);


            messageBroker.addTopic(topicName, topicPartitionsCount);

            Assertions.assertTrue(messageBroker.containsTopic(topicName));

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

            TreeMap<String, Topic> injectedMap = new TreeMap<>();
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

            TreeMap<String, Topic> injectedMap = new TreeMap<>();
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

        @DisplayName("Fail to add Message with timestamp older than the minimum date for this broker")
        @Test
        void failToAddMessage() throws PartitionDoesNotExistException {
            injectedMap.put(OTHER_TOPIC_1_NAME, OTHER_TOPIC_1);
            injectedMap.put(OTHER_TOPIC_2_NAME, OTHER_TOPIC_2);
            injectedMap.put(OTHER_TOPIC_3_NAME, OTHER_TOPIC_3);

            LocalDateTime minimumDate = LocalDateTime.of(2000,1,1,0,0);
            Integer capacityPerTopic = 50;
            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic, injectedMap);

            Message messageToAdd = mock(Message.class);
            when(messageToAdd.getTimestamp()).thenReturn(LocalDateTime.of(1999, 1, 1, 0, 0));

            messageBroker.addMessage(OTHER_TOPIC_1_NAME, messageToAdd);

            verifyNoInteractions(OTHER_TOPIC_1, OTHER_TOPIC_2, OTHER_TOPIC_3);
        }
    }

    @BeforeEach
    void setUp() {
        injectedMap = new TreeMap<>();
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

    @DisplayName("Return String representation of this MessageBroker by calling all topic toString() methods")
    @Test
    void _toString(){
        injectedMap.put(OTHER_TOPIC_1_NAME, OTHER_TOPIC_1);
        injectedMap.put(OTHER_TOPIC_2_NAME, OTHER_TOPIC_2);
        injectedMap.put(OTHER_TOPIC_3_NAME, OTHER_TOPIC_3);

        MessageBroker broker = new MessageBroker(LocalDateTime.now(), 50, injectedMap);

        String outputOfToString = broker.toString();

        String expectedOutput = String.format("Broker with  %d topics:%n", injectedMap.size()) +
                OTHER_TOPIC_1 + OTHER_TOPIC_2 + OTHER_TOPIC_3;

        Assertions.assertEquals(expectedOutput, outputOfToString);

    }

}
