package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class NotificationConsumer {

    private static Logger LOG = LoggerFactory.getLogger(NotificationConsumer.class);
    public void startConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "notification_group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList("notifications_topic"));

        // Poll for new messages
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: %s%n", record.value());
                    LOG.info("Received message: " + record.value());
//                    notificationService.sendNotification(record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
