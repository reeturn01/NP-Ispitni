package IntegrationTest;

import HelperFunctions.HelperGenerationFunctions;
import main.Message;
import main.MessageBroker;
import main.PartitionDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MessageSystemTest {
    private final ByteArrayOutputStream stdOutputCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream stdInputCaptor = new ByteArrayInputStream(HelperGenerationFunctions.readSampleInput().getBytes());
    private final PrintStream stdOutput = System.out;
    private final InputStream stdInput= System.in;
    private final String expectedOutput = HelperGenerationFunctions.readSampleOutput();



    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(stdOutputCaptor));
        System.setIn(stdInputCaptor);
    }

    @AfterEach
    void tearDown(){
        System.setOut(stdOutput);
        System.setIn(stdInput);
    }

    @Test
    void testing_main(){
        Scanner sc = new Scanner(System.in);

        String date = sc.nextLine();
        LocalDateTime localDateTime =LocalDateTime.parse(date);
        Integer partitionsLimit = Integer.parseInt(sc.nextLine());
        MessageBroker broker = new MessageBroker(localDateTime, partitionsLimit);
        int topicsCount = Integer.parseInt(sc.nextLine());

        //Adding topics
        for (int i=0;i<topicsCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topicName = parts[0];
            int partitionsCount = Integer.parseInt(parts[1]);
            broker.addTopic(topicName, partitionsCount);
        }

        //Reading messages
        int messagesCount = Integer.parseInt(sc.nextLine());

        System.out.println("===ADDING MESSAGES TO TOPICS===");
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER ADDITION OF MESSAGES===");
        System.out.println(broker);

        System.out.println("===CHANGE OF TOPICS CONFIGURATION===");
        //topics changes
        int changesCount = Integer.parseInt(sc.nextLine());
        for (int i=0;i<changesCount;i++){
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topicName = parts[0];
            Integer partitions = Integer.parseInt(parts[1]);
            try {
                broker.changeTopicSettings(topicName, partitions);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("===ADDING NEW MESSAGES TO TOPICS===");
        messagesCount = Integer.parseInt(sc.nextLine());
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER CONFIGURATION CHANGE===");
        System.out.println(broker);

        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
    }

    //
//    @ParameterizedTest
//    @MethodSource("HelperFunctions.HelperGenerationFunctions#inputAndOutputGeneratorForAcceptanceTest")
//    void mainMethodIntegrationTest(String userInput, String expectedOutput){
//        stdInputCaptor = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(stdInputCaptor);
//
//        Main.main(null);
//        Assertions.assertEquals(expectedOutput, stdOutputCaptor.toString().trim());
//    }
}
