package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Topic {
    private String topicName;
    private int partitionsCount;

    private Map<Integer, List<Message>> messageCollection;
    public Topic(String topicName, int partitionsCount){

        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        messageCollection = new HashMap<>();
        IntStream.range(1,partitionsCount+1).boxed().forEachOrdered(v -> messageCollection.put(v, new ArrayList<>()));
    }

    public Topic(String topicName, int partitionsCount, HashMap<Integer, List<Message>> messageCollection) {
        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        this.messageCollection = messageCollection;
    }

    public void addMessage(Message newMessage) {
        if (messageCollection.containsKey(newMessage.getPartition())){
            List<Message> col = messageCollection.get(newMessage.getPartition());
                    col.add(newMessage);
        }
    }
}
