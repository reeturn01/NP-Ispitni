package main;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private LocalDateTime timestamp;
    private String message;
    private Integer partition;
    public String key;

    public Message(LocalDateTime timestamp, String message, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.partition = null;
        this.key = key;
    }

    public Message(LocalDateTime timestamp, String message, Integer partition, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.partition = partition;
        this.key = key;
    }

    public Message() {
        this.timestamp = LocalDateTime.now();
        this.message = "";
        this.partition = 1;
        this.key = "";
    }
    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getPartition() {
        return partition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return timestamp.equals(message1.timestamp) && message.equals(message1.message) && Objects.equals(partition, message1.partition) && key.equals(message1.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, partition, key);
    }
    //    private LocalDateTime timestamp;
//    private String message;
//    private Integer partition;
//    private String key;
//
//    public Message(LocalDateTime timestamp, String message, String key) {
//        this.timestamp = timestamp;
//        this.message = message;
//        this.key = key;
//    }
//
//    public Message(LocalDateTime timestamp, String message, Integer partition, String key) {
//        this.timestamp = timestamp;
//        this.message = message;
//        this.partition = partition;
//        this.key = key;
//    }
//
//
//    public Integer getPartition() {
//        return partition;
//    }
}
