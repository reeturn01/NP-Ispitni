package main;

import java.time.LocalDateTime;

public class MessageBroker {
    public MessageBroker(LocalDateTime localDateTime, Integer partitionsLimit) {
        throw new UnsupportedOperationException();
    }

    public void addTopic(String topicName, int partitionsCount) {
        throw new UnsupportedOperationException();
    }

    public void addMessage(String topic, Message message) throws PartitionDoesNotExistException{
        throw new UnsupportedOperationException();
    }

    public void changeTopicSettings(String topicName, Integer partitions) {
        throw new UnsupportedOperationException();
    }
//    public MessageBroker(LocalDateTime localDateTime, Integer partitionsLimit) {
//    }
//
//    public void addTopic(String topicName, int partitionsCount) {
//    }
//
//    public void addMessage(String topic, Message message) throws PartitionDoesNotExistException{
//    }
//
//    public void changeTopicSettings(String topicName, Integer partitions) {
//    }
}
