package main;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MessageBroker {
    private LocalDateTime minimumDate;  //Poceten datum
    private Integer partitionsLimit;   //Maksimalen kapacitet na sekoja particija za site temi
    private Map<String, Topic> topicMap;
    public MessageBroker(LocalDateTime minimumDate, Integer partitionsLimit) {
        this.minimumDate = minimumDate;
        this.partitionsLimit = partitionsLimit;
        this.topicMap = new TreeMap<>();
    }

    public MessageBroker(LocalDateTime minimumDate, Integer partitionsLimit, TreeMap<String, Topic> topicHashSet) {

        this.minimumDate = minimumDate;
        this.partitionsLimit = partitionsLimit;
        this.topicMap = topicHashSet;
    }

    public void addTopic(String topicName, int partitionsCount) {
        topicMap.putIfAbsent(topicName, new Topic(topicName, partitionsLimit,partitionsCount));
    }

    public void addMessage(String topicName, Message message) throws PartitionDoesNotExistException{
        if (!topicMap.containsKey(topicName) || message.getTimestamp().isBefore(minimumDate))
            return;
        topicMap.get(topicName).addMessage(message);
    }

    public void changeTopicSettings(String topicName, int partitions) {
        if (topicMap.containsKey(topicName))
            topicMap.get(topicName).changeNumberOfPartitions(partitions);
    }

    public boolean containsTopic(String topicName){
        return topicMap.containsKey(topicName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Broker with  ").append(topicMap.size()).append(" topics:\r\n");
        builder.append(topicMap.values().stream()
                .map(Topic::toString)
                .collect(Collectors.joining("\r\n")));
        return builder.toString();
    }
}
