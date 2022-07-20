package main;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime timestamp;
    private String message;
    private Integer partition;
    private String key;

    public Message(LocalDateTime timestamp, String message, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.partition = null;
        this.key = key;
        throw new UnsupportedOperationException();
    }

    public Message(LocalDateTime timestamp, String message, Integer partition, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.partition = partition;
        this.key = key;
        throw new UnsupportedOperationException();
    }

    public Message() {
        this.timestamp = LocalDateTime.now();
        this.message = "";
        this.partition = 1;
        this.key = "";
    }

    @Override
    public String toString() {
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
