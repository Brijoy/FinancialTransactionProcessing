//package consumer;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Properties;
//
//public class NotificationConsumer {
//
//    public void startConsumer() {
//        // Set up properties for the consumer
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("group.id", "notification_group");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        // Create the consumer
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//
//        // Subscribe to the topic
//        consumer.subscribe(Collections.singletonList("notifications_topic"));
//
//        // Poll for new messages
//        try {
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//                for (ConsumerRecord<String, String> record : records) {
//                    System.out.printf("Received message: %s%n", record.value());
//                    // Add your message processing logic here
//                }
//            }
//        } finally {
//            consumer.close();
//        }
//    }
//}
