package UnitTests;

import main.Message;
import main.MessageBroker;
import main.PartitionDoesNotExistException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageBrokerShould {

    @DisplayName("addTopic() should:")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
                                                int partitionsCount){
            // Integer capacityPerTopic = 10;

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic);

            // String topicName = "topic1";
            // int partitionsCount = 6;
            messageBroker.addTopic(topicName, partitionsCount);

            String expectedOutputString = "Broker with  1 topics:\r\n"+
                    String.format("Topic: %10s Partitions: %5d%n", topicName, partitionsCount) +
                    IntStream.range(1, partitionsCount+1)
                            .mapToObj(i -> String.format("%d : Count of messages:%5d%n", i, 0)+"Messages:\r\n")
                            .collect(Collectors.joining());

            Assertions.assertEquals(expectedOutputString, messageBroker.toString());

        }

        @Test
        void fail_to_add_topic_with_same_name(){
            Integer capacityPerTopic = 5;
            String topicName = "topic1";
            int partitionsCount = 3;

            MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic);

            for (int i = 0; i < 10; i++) {
                messageBroker.addTopic(topicName,partitionsCount+i);
            }

            String expectedOutputString = "Broker with  1 topics:\r\n"+
                    String.format("Topic: %10s Partitions: %5d%n", topicName, partitionsCount) +
                    IntStream.range(1, partitionsCount+1)
                            .mapToObj(i -> String.format("%d : Count of messages:%5d%n", i, 0)+"Messages:\r\n")
                            .collect(Collectors.joining());

            Assertions.assertEquals(expectedOutputString, messageBroker.toString());

        }


    }

    @Test
    void add_Message_to_existing_topic(){
        String topic = "topic1";
        Message message = new Message();

        LocalDateTime minimumDate = LocalDateTime.now();

        Integer capacityPerTopic = 5;
        int partitionsCount = 5;
        MessageBroker messageBroker = new MessageBroker(minimumDate, capacityPerTopic);

        messageBroker.addTopic(topic, partitionsCount);

        try {
            messageBroker.addMessage(topic, message);
        } catch (PartitionDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        String expectedOutputString = "Broker with  1 topics:\r\n"+
                String.format("Topic: %10s Partitions: %5d%n", topic, partitionsCount) +
                IntStream.range(1, partitionsCount+1)
                        .mapToObj(i -> String.format("%d : Count of messages:%5d%n", i, 0)+"Messages:\r\n")
                        .collect(Collectors.joining());

    }

}
