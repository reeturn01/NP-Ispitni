package main;

public class PartitionDoesNotExistException extends Exception{
    public PartitionDoesNotExistException(String message) {
        super(message);
    }
}
