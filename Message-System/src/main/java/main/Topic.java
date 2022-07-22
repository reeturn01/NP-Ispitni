package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Topic {
    private Map<Integer, Partition> partitions;
    private String topicName;
    private int partitionsCount;

    public Topic(String topicName, int partitionsCount, Map<Integer, Partition> partitionedMessageCollection) {
        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        this.partitions = partitionedMessageCollection;
    }

    public Topic(String topicName, int partitionsCount) {

        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        this.partitions = new HashMap<>();
        //this.partitions = new PartitionedMessageCollection(this.partitionsCount);
        IntStream.range(1, this.partitionsCount+1)
                .boxed()
                .forEachOrdered(i -> this.partitions.put(i, new Partition(i)));
    }
    public int getPartitionsCount() {
        return partitionsCount;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Topic: %10s Partitions: %5d%n", this.topicName, this.partitionsCount));
        partitions.values()
                        .forEach(builder::append);
//        partitions.keySet()
//                .forEach(key -> {
//                    builder.append(String.format("%d : Count of messages:%5d%n", key, partitions.get(key).size()));
//                    builder.append("Messages:\r\n");
//                    partitions.get(key).forEach(m -> builder.append(m.toString()));
//                });
        return builder.toString();
    }

    public void addMessage(Message message) throws PartitionDoesNotExistException {
        if (message.getPartition() != null){
            if (message.getPartition() > partitionsCount+1)
                throw new PartitionDoesNotExistException();
            partitions.putIfAbsent(message.getPartition(), new Partition(message.getPartition()));
            partitions.get(message.getPartition()).addMessage(message);
        }else {
            Integer partitionIndexToAddMessageTo = PartitionAssigner.assignPartition(message, partitionsCount);
            partitions.putIfAbsent(partitionIndexToAddMessageTo, new Partition(partitionIndexToAddMessageTo));
            partitions.get(partitionIndexToAddMessageTo).addMessage(message);
        }
//        if (message.getPartition()<= partitionsCount+1){
//            partitions.putIfAbsent(message.getPartition(), new Partition(message.getPartition()))
//        }
    }

    public void changeNumberOfPartitions(int partitions) {
        throw new NotImplementedException();
    }

    public boolean containsMessage(Message message) {
        throw new NotImplementedException();
    }
}
