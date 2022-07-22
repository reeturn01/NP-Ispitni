package main;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageBroker {
    private LocalDateTime minimumDate;  //Poceten datum
    private Integer capacityPerTopic;   //Maksimalen kapacitet na sekoja particija za site temi
    private Map<String, Topic> topicMap;
    public MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic) {
        this.minimumDate = minimumDate;
        this.capacityPerTopic = capacityPerTopic;
        this.topicMap = new HashMap<>();
    }

    public MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic, HashMap<String, Topic> topicHashSet) {

        this.minimumDate = minimumDate;
        this.capacityPerTopic = capacityPerTopic;
        this.topicMap = topicHashSet;
    }

    public void addTopic(String topicName, int partitionsCount) {
        topicMap.putIfAbsent(topicName, new Topic(topicName,partitionsCount));
    }

    public void addMessage(String topicName, Message message) throws PartitionDoesNotExistException{
        if (topicMap.containsKey(topicName))
            topicMap.get(topicName).addMessage(message);
    }

    public void changeTopicSettings(String topicName, int partitions) {
        if (topicMap.containsKey(topicName))
            topicMap.get(topicName).changeNumberOfPartitions(partitions);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Broker with  ").append(topicMap.size()).append(" topics:\r\n");
        topicMap.values().forEach(builder::append);
        return builder.toString();
    }
}
