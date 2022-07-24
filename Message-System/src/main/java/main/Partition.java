package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Partition {
    private final int partitionsLimit;
    private int partitionIndex;

    private List<Message> messageList;

    public Partition(int partitionIndex, int partitionsLimit) {
        this(partitionIndex, partitionsLimit, new ArrayList<>());
    }

    public Partition(int partitionIndex, int partitionsLimit, List<Message> messageList) {

        this.partitionIndex = partitionIndex;
        this.partitionsLimit = partitionsLimit;
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        //1 : Count of messages:     4
        StringBuilder builder = new StringBuilder();
        builder.append(partitionIndex).append(" : Count of messages:     ").append(messageList.size()).append("\r\n");
        builder.append("Messages:").append("\r\n");
//        messageList.forEach(msg -> builder.append(msg).append("\r\n"));
        builder.append(messageList.stream()
                .map(Message::toString)
                .collect(Collectors.joining("\r\n")));
        return builder.toString();
    }

    public void addMessage(Message message) {
        if (partitionsLimit == messageList.size())
            messageList.set(0, message);
        else {
            messageList.add(message);
        }
        messageList.sort(Comparator.comparing(Message::getTimestamp));
    }

    public boolean contains(Message messageToBeAdded) {
        return messageList.contains(messageToBeAdded);
    }

}
