package com.td.notification_service.controller;

import com.td.notification_service.config.KafkaConfigs;
import com.td.notification_service.dto.NotificationDTO;
import com.td.notification_service.dto.UserDTO;
import com.td.notification_service.kafka.Dispatcher;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/kafkaproducer")
public class KafkaProducerController {

    private static final Logger logger = LogManager.getLogger();

    @PostMapping
    public ResponseEntity<String> sendKafkaProducer() throws ExecutionException, InterruptedException {

        Properties props = new Properties();

            /*InputStream inputStream = new FileInputStream(KafkaConfigs.kafkaConfigFileLocation);
            props.load(inputStream);*/
            props.put(ProducerConfig.CLIENT_ID_CONFIG,KafkaConfigs.applicationID);
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer,String> producer = new KafkaProducer<>(props);
        Thread[] dispatchers = new Thread[KafkaConfigs.eventFiles.length];
        logger.info("Starting Dispatcher threads...");
        for(int i=0;i<KafkaConfigs.eventFiles.length;i++){
            dispatchers[i]=new Thread(new Dispatcher(producer,KafkaConfigs.topicName1,KafkaConfigs.eventFiles[i]));
            dispatchers[i].start();
        }

        try {
            for (Thread t : dispatchers) t.join();
        }catch (InterruptedException e){
            logger.error("Main Thread Interrupted");
        }finally {
            producer.flush();
            producer.close();
            logger.info("Finished Dispatcher Demo");
        }
        return new ResponseEntity<>("Successful Dispatch to producer", HttpStatus.CREATED);

    }

}
