package main;

import java.time.LocalDateTime;
import java.util.HashSet;

public class MessageBroker {
    private LocalDateTime minimumDate;  //Poceten datum
    private Integer capacityPerTopic;   //Maksimalen kapacitet na sekoja particija za site temi
    private HashSet<Topic> topicList;
    public MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic) {
        this.minimumDate = minimumDate;
        this.capacityPerTopic = capacityPerTopic;
        this.topicList = new HashSet<Topic>();
    }

    public void addTopic(String topicName, int partitionsCount) {
        topicList.add(new Topic(topicName, partitionsCount));
    }

    public void addMessage(String topic, Message message) throws PartitionDoesNotExistException{
        throw new UnsupportedOperationException();
    }

    public void changeTopicSettings(String topicName, Integer partitions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Broker with  ").append(topicList.size()).append(" topics:\r\n");
        topicList.forEach(builder::append);
        return builder.toString();
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
