package main;

import java.time.LocalDateTime;

public class MessageBroker {
    public MessageBroker(LocalDateTime localDateTime, Integer partitionsLimit) {
    }

    public void addTopic(String topicName, int partitionsCount) {
    }

    public void addMessage(String topic, Message message) throws PartitionDoesNotExistException{
    }

    public void changeTopicSettings(String topicName, Integer partitions) {
    }
}