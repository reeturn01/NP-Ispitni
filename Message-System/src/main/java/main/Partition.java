package main;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    private int partitionIndex;

    private List<Message> messageList;

    public Partition(int partitionIndex) {
        this(partitionIndex, new ArrayList<>());
    }

    public Partition(int partitionIndex, List<Message> messageList) {

        this.partitionIndex = partitionIndex;

        this.messageList = messageList;
    }

    @Override
    public String toString() {
        //1 : Count of messages:     4
        StringBuilder builder = new StringBuilder();
        builder.append(partitionIndex).append(" : Count of messages:     4\r\n");
        messageList.forEach(builder::append);
        return builder.toString();
    }

    public int getPartitionIndex() {
        return partitionIndex;
    }

    public void addMessage(Message message) {
        throw new NotImplementedException();
    }
}
