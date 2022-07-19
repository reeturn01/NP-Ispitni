package UnitTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TopicShould {
//    private Topic topic;
//
//    @DisplayName("addMessage() functions:")
//    @Nested
//    class AddMessageFunctions{
//
//        @DisplayName("Adding new message with partition, matching any in messageCollection")
//        @ParameterizedTest
//        @MethodSource("add_Message_with_partition_factory")
//        void add_Message_with_partition(int partitionsCount, Integer messagePartition, String messageText, String messageKey){
//
//            HashMap<Integer, List<Message>> messageCollection = new HashMap<>();
//            IntStream.range(1, partitionsCount +1).boxed().forEachOrdered(v -> messageCollection.put(v, new ArrayList<>()));
//            HashMap<Integer, List<Message>> messageCollectionSpy = Mockito.spy(messageCollection);
//
//            topic = new Topic("topic1", partitionsCount, messageCollection);
//            Message newMessage = new Message(LocalDateTime.of(2000,1,1,0,0), messageText, messagePartition, messageKey);
//
//            Assertions.assertTrue(messageCollectionSpy.containsKey(newMessage.getPartition()));
//            Assertions.assertFalse(messageCollectionSpy.get(newMessage.getPartition()).contains(newMessage));
//
//            topic.addMessage(newMessage);
//
//            Assertions.assertTrue(messageCollectionSpy.containsKey(newMessage.getPartition()));
//            Assertions.assertTrue(messageCollectionSpy.get(1).contains(newMessage));
//
//        }
//    }
//    public static Stream<Arguments> add_Message_with_partition_factory(){
//        List<String> argumentsInLines = getOnlyInputWithPartitionsFrom("src/test/resources/TestInputFor_addMessageFunctions.txt");
//        Stream.Builder<Arguments> builder = Stream.<Arguments>builder();
//
//        for (String line : argumentsInLines) {
//            String[] tokens = line.split(";");
//            builder.add(tokens[0], tokens[1], tokens[2], tokens[3])
//        }
//        return Stream.of(
//
//        );
//    }


}
