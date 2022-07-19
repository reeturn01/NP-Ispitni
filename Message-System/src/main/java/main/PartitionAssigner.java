package main;

class PartitionAssigner {
    public static Integer assignPartition(Message message, int partitionsCount) {
        throw new UnsupportedOperationException();
        //return (Math.abs(message.key.hashCode()) % partitionsCount) + 1;
    }
}
