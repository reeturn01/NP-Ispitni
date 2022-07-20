package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Topic {
    private TreeMap<Integer, List<Message>> messagesByPartition;
    private String topicName;
    private int partitionsCount;

    public Topic(String topicName, int partitionsCount) {

        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        this.messagesByPartition = new TreeMap<Integer, List<Message>>();
        IntStream.range(1, this.partitionsCount+1).boxed().forEachOrdered(i -> messagesByPartition.put(i, new ArrayList<>()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Topic: %10s Partitions: %5d%n", this.topicName, this.partitionsCount));
        messagesByPartition.keySet()
                .forEach(key -> {
                    builder.append(String.format("%d : Count of messages:%5d%n", key, messagesByPartition.get(key).size()));
                    builder.append("Messages:\r\n");
                    messagesByPartition.get(key).forEach(m -> builder.append(m.toString()));
                });
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return topicName.equals(topic.topicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName);
    }
}
