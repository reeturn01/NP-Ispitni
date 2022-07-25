package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Topic {
    private final int partitionsLimit;
    private Map<Integer, Partition> partitions;
    private String topicName;

    public int getPartitionsCount() {
        return partitionsCount;
    }

    private int partitionsCount;

    public Topic(String topicName,int partitionsLimit, int partitionsCount, Map<Integer, Partition> partitionedMessageCollection) {
        this.topicName = topicName;
        this.partitionsLimit = partitionsLimit;
        this.partitionsCount = partitionsCount;
        this.partitions = partitionedMessageCollection;
    }

    public Topic(String topicName, int partitionsLimit, int partitionsCount) {

        this.topicName = topicName;
        this.partitionsLimit = partitionsLimit;
        this.partitionsCount = partitionsCount;
        this.partitions = new HashMap<>();
        //this.partitions = new PartitionedMessageCollection(this.partitionsCount);
        IntStream.range(1, this.partitionsCount+1)
                .boxed()
                .forEachOrdered(i -> this.partitions.put(i, new Partition(i, partitionsLimit)));
    }



    public void addMessage(Message message) throws PartitionDoesNotExistException {
        if (message.getPartition() != null){
            if (message.getPartition() > partitionsCount)
                throw new PartitionDoesNotExistException(String.format("The topic %s does not have a partition with number %d",topicName, message.getPartition()));
            partitions.putIfAbsent(message.getPartition(), new Partition(message.getPartition(), partitionsLimit));
            partitions.get(message.getPartition()).addMessage(message);
        }else {
            Integer partitionIndexToAddMessageTo = PartitionAssigner.assignPartition(message, partitionsCount);
            partitions.putIfAbsent(partitionIndexToAddMessageTo, new Partition(partitionIndexToAddMessageTo, partitionsLimit));
            partitions.get(partitionIndexToAddMessageTo).addMessage(message);
        }
    }

    public void changeNumberOfPartitions(int newPartitionsCount) throws UnsupportedOperationException{
        if (newPartitionsCount < partitionsCount)
            throw new UnsupportedOperationException();
        partitionsCount = newPartitionsCount;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Topic: %10s Partitions: %5d%n", this.topicName, this.partitionsCount));
        builder.append(partitions.values()
                .stream()
                .map(Partition::toString)
                .collect(Collectors.joining("\r\n")));
        return builder.toString();
    }

}
