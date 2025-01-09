package com.td.notification_service.controller;

import com.td.notification_service.config.KafkaConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api/kafkaconsumer")
public class KafkaConsumerConsumer {

    private static final Logger logger = LogManager.getLogger();

    @GetMapping("/{name}")
    public ResponseEntity<String> sendKafkaProducer(@PathVariable String name){

        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfigs.groupID);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<Integer, String> consumer =
                new KafkaConsumer<>(props);

        // Subscribe Consumer to Our Topics
        consumer.subscribe(List.of(name));

        // Poll the data
        while (true) {

            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<Integer, String> record : records) {
                logger.info("Key: " + record.key() +
                        " Value: " + record.value() +
                        " Partition: " + record.partition() +
                        " Offset: " + record.offset()
                );
            }
            return ResponseEntity.ok("Able to consume successfully");
        }

        //return ResponseEntity.ok("Able to consume successfully");

    }
}
