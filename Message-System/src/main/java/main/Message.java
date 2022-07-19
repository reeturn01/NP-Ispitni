package main;

import java.time.LocalDateTime;

public class Message {
    public Message(LocalDateTime timestamp, String message, String key) {
        throw new UnsupportedOperationException();
    }

    public Message(LocalDateTime timestamp, String message, Integer partition, String key) {
        throw new UnsupportedOperationException();
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
//    @Override
//    public String toString() {
//        return "Message{" +
//                "timestamp=" + timestamp +
//                ", message='" + message + '\'' +
//                '}';
//    }
//
//    public Integer getPartition() {
//        return partition;
//    }
}
