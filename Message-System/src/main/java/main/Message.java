package main;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

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

}
